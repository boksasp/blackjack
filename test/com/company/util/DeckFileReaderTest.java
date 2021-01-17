package com.company.util;

import com.company.types.BlackjackErrorCode;
import com.company.types.Card;
import com.company.types.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DeckFileReaderTest {
    private DeckFileReader deckFileReader;

    @BeforeEach
    void init() {
        deckFileReader = new DeckFileReader();
    }

    @Test
    void ReadValidInputFileFromDisk() throws Exception {
        Deck deck = deckFileReader.readInputFileFromDisk("test/resources/valid_5Cards.txt");

        assertEquals(5, deck.getCards().size());
        assertEquals(Card.CA, deck.getCards().get(0));
        assertEquals(Card.D5, deck.getCards().get(1));
        assertEquals(Card.H9, deck.getCards().get(2));
        assertEquals(Card.HQ, deck.getCards().get(3));
        assertEquals(Card.S8, deck.getCards().get(4));
    }

    @Test
    void HandleBlankValuesInDeckFile() throws Exception  {
        Deck deck = deckFileReader.readInputFileFromDisk("test/resources/valid_WithBlankValues.txt");

        assertEquals(4, deck.getCards().size());
        assertEquals(Card.D5, deck.getCards().get(0));
        assertEquals(Card.S8, deck.getCards().get(1));
        assertEquals(Card.SA, deck.getCards().get(2));
        assertEquals(Card.HQ, deck.getCards().get(3));
    }

    @Test
    void CanProcessFileWithoutExtension() throws Exception {
        Deck deck = deckFileReader.readInputFileFromDisk("test/resources/valid_4Cards");

        assertEquals(4, deck.getCards().size());
        assertEquals(Card.C2, deck.getCards().get(0));
        assertEquals(Card.H6, deck.getCards().get(1));
        assertEquals(Card.SA, deck.getCards().get(2));
        assertEquals(Card.HQ, deck.getCards().get(3));
    }

    @Test
    void ThrowsExceptionWhenInputFileHasLessThan4Cards(){
        try {
            deckFileReader.readInputFileFromDisk("test/resources/invalid_tooFewCards.txt");
            fail("Expected exception when deck is too small to start game");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.TOO_FEW_CARDS_TO_START_GAME.getValue(), ex.getMessage());
        }
    }

    @Test
    void ThrowsExceptionWhenFileDoesNotExist() {
        try {
            deckFileReader.readInputFileFromDisk("invalid_file_path");
            fail("Expected exception when input file doesn't exist");
        } catch (Exception ex) {
            assertEquals(FileNotFoundException.class, ex.getClass());
        }
    }

    @Test
    void ThrowsExceptionWhenInputPointsToFolder() {
        try {
            deckFileReader.readInputFileFromDisk("test/resources");
            fail("Expected exception when input path points to folder");
        } catch (Exception ex) {
            assertEquals(FileNotFoundException.class, ex.getClass());
        }
    }
}