package com.company.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void init() {
        player = new Player("testplayer");
    }

    @Test
    void ReturnsNamedPlayerWithEmptyHandAndScoreOfZero() {
        assertEquals("testplayer", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(0, player.getHand().size());
    }

    @Test
    void CanAddCardToHand() {
        player.addCard(Card.C2);

        assertEquals(1, player.getHand().size());
        assertEquals(Card.C2, player.getHand().get(0));
    }

    @Test
    void AddsCardValueToScore() {
        player.addCard(Card.CK);
        player.addCard(Card.H7);

        assertEquals(17, player.getScore());
    }

    @Test
    void HasBlackjackOnTwoCardsAndScoreOf21() {
        player.addCard(Card.CK);
        player.addCard(Card.CA);

        assertTrue(player.hasBlackjack());
    }

    @Test
    void NotBlackjackOnMoreThanTwoCardsAndScoreOf21() {
        player.addCard(Card.C5);
        player.addCard(Card.D5);
        player.addCard(Card.SA);

        assertFalse(player.hasBlackjack());
        assertEquals(3, player.getHand().size());
        assertEquals(21, player.getScore());
    }

    @Test
    void IsBustWhenScoreIsAbove21() {
        player.addCard(Card.CA);
        player.addCard(Card.SA);

        assertTrue(player.isBust());
        assertEquals(22, player.getScore());

        player.addCard(Card.H2);

        assertTrue(player.isBust());
        assertEquals(24, player.getScore());
    }

    @Test
    void IsNotBustWhenScoreBelow22() {
        player.addCard(Card.CA);
        player.addCard(Card.D6);

        assertFalse(player.isBust());
        assertEquals(17, player.getScore());

        player.addCard(Card.H4);

        assertFalse(player.isBust());
        assertEquals(21, player.getScore());
    }

    @Test
    void FormattedHandIsDisplayedCorrectly() {
        player.addCard(Card.C2);
        player.addCard(Card.DK);
        player.addCard(null);
        player.addCard(Card.SQ);

        assertEquals("testplayer: C2, DK, SQ", player.getPrintableHand());
    }

    @Test
    void HandleNullValueWhenAddingCard() {
        player.addCard(null);

        assertEquals(0, player.getHand().size());
        assertEquals(0, player.getScore());
    }

}