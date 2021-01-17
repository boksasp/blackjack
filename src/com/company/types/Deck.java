package com.company.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = generateDeck();
    }

    public Deck(final ArrayList<Card> deck) {
        if (deck != null) {
            this.cards = new ArrayList<>(deck);
        } else {
            this.cards = new ArrayList<>();
        }
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public Optional<Card> draw() {
        if (cards == null) {
            return Optional.empty();
        }
        if (cards.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(cards.remove(0));
    }

    private ArrayList<Card> generateDeck() {
        final ArrayList<Card> sortedDeck = new ArrayList<>(Arrays.asList(Card.values()));
        final ArrayList<Card> shuffledDeck = new ArrayList<>();

        final Random randomGenerator = new Random();

        while (sortedDeck.size() > 0) {
            shuffledDeck.add(sortedDeck.remove(randomGenerator.nextInt(sortedDeck.size())));
        }

        return shuffledDeck;
    }
}
