package com.company.util;

import com.company.types.BlackjackErrorCode;
import com.company.types.BlackjackException;
import com.company.types.Card;
import com.company.types.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DeckFileReader {

    public Deck readInputFileFromDisk(final String filePath) throws Exception {
        final ArrayList<Card> cards = new ArrayList<>();

        final BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String row;
        while ((row = reader.readLine()) != null) {
            cards.addAll(
                    Arrays.stream(row.split(","))
                            .filter(s -> !s.isBlank())
                            .map(s -> Card.valueOf(s.trim()))
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }
        reader.close();

        if (cards.size() < 4) {
            throw new BlackjackException(BlackjackErrorCode.TOO_FEW_CARDS_TO_START_GAME.getValue());
        }

        return new Deck(cards);
    }
}
