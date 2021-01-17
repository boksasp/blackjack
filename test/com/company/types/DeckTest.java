package com.company.types;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void UsesProvidedCardsAsDeck() {
        ArrayList<Card> cards = new ArrayList<>(List.of(Card.C2, Card.D5, Card.SQ));

        Deck deck = new Deck(cards);

        cards.remove(0);

        assertEquals(3, deck.getCards().size());
        assertEquals(Card.C2, deck.getCards().get(0));
        assertEquals(Card.D5, deck.getCards().get(1));
        assertEquals(Card.SQ, deck.getCards().get(2));
    }

    @Test
    void DrawsCardsInOrderProvided() {
        ArrayList<Card> cards = new ArrayList<>(List.of(Card.C2, Card.D5, Card.SQ));

        Deck deck = new Deck(cards);

        assertEquals(Card.C2, deck.draw().get());
        assertEquals(Card.D5, deck.draw().get());
        assertEquals(Card.SQ, deck.draw().get());
    }

    @Test
    void DrawsOptionalEmptyWhenInitializedWithNullValue() {
        Deck deck = new Deck(null);

        assertEquals(Optional.empty(), deck.draw());
    }

    @Test
    void DrawsOptionalEmptyWhenDeckIsEmpty() {
        ArrayList<Card> cards = new ArrayList<>(List.of(Card.C2));

        Deck deck = new Deck(cards);

        assertEquals(Card.C2, deck.draw().get());
        assertEquals(Optional.empty(), deck.draw());
    }

    @Test
    void GeneratesFullDeckWhenNoCardsProvided() {
        Deck deck = new Deck();
        ArrayList<Card> deckCards = deck.getCards();
        Collections.sort(deckCards);
        Card[] baseCards = Card.values();

        assertEquals(52, deckCards.size());
        assertArrayEquals(deckCards.toArray(), baseCards);
    }

    @Test
    void GeneratesEmptyDeckWhenEmptyArrayOfCardsProvided() {
        Deck deck = new Deck(new ArrayList<>());

        assertEquals(0, deck.getCards().size());
    }
}