package com.company.game;

import com.company.types.Card;
import com.company.types.Deck;
import com.company.types.Player;
import com.company.types.BlackjackErrorCode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void TableThrowsExceptionWhenStartingGameFromEmptyDeck() {
        Table table = new Table(createDeck());

        try {
            table.runGameAndGetWinner();
            fail("Expected exception when drawing from empty deck.");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS.getValue(), ex.getMessage());
        }
    }

    @Test
    void TableThrowsExceptionWhenStartingGameFromNullDeck() {
        Table table = new Table(null);

        Player player = table.getDealer();

        player.addCard(Card.C2);

        try {
            table.runGameAndGetWinner();
            fail("Expected exception when drawing from null deck.");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS.getValue(), ex.getMessage());
        }
    }

    @Test
    void InitialDraw_GivesEachPlayerTwoCards() throws Exception {
        Deck deck = createDeck(Card.C2, Card.C3, Card.C4, Card.C5, Card.C6);
        Table table = new Table(deck);

        table.initialDraw();

        assertArrayEquals(
                new ArrayList<>(List.of(Card.C2, Card.C4)).toArray(),
                table.getPlayer().getHand().toArray()
        );
        assertArrayEquals(
                new ArrayList<>(List.of(Card.C3, Card.C5)).toArray(),
                table.getDealer().getHand().toArray()
        );
    }

    @Test
    void InitialDraw_ThrowsExceptionWhenDeckIsEmptyOnDraw() {
        Deck deck = createDeck(Card.C2, Card.C3, Card.C4);
        Table table = new Table(deck);

        try {
            table.initialDraw();
            fail("Expected exception when drawing from empty deck.");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS.getValue(), ex.getMessage());
        }

    }

    @Test
    void PlayerDraw_PlayerStopsDrawingWhenScoreIs17() throws Exception {
        Deck deck = createDeck(Card.C7, Card.C10, Card.C4, Card.C5, Card.C6);
        Table table = new Table(deck);

        table.playerDraw();

        assertEquals(17, table.getPlayer().getScore());
    }

    @Test
    void PlayerDraw_ThrowsExceptionWhenDeckIsEmptyOnDraw() {
        Deck deck = createDeck(Card.C7);
        Table table = new Table(deck);

        try {
            table.playerDraw();
            fail("Expected exception when drawing from empty deck.");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS.getValue(), ex.getMessage());
        }

    }

    @Test
    void DealerDraw_DealerStopsDrawingWhenScoreIsHigherThanPlayers() throws Exception {
        Deck deck = createDeck(Card.C7, Card.C10, Card.C5, Card.CK, Card.C3);
        Table table = new Table(deck);

        table.playerDraw();
        table.dealerDraw();

        assertEquals(18, table.getDealer().getScore());
    }

    @Test
    void DealerDraw_ThrowsExceptionWhenDeckIsEmptyOnDraw() {
        Deck deck = createDeck();
        Table table = new Table(deck);

        try {
            table.dealerDraw();
            fail("Expected exception when drawing from empty deck.");
        } catch (Exception ex) {
            assertEquals(BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS.getValue(), ex.getMessage());
        }

    }

    @Test
    void PlayerWinsWhenBothHaveBlackjackOnInitialHand() throws Exception {
        Deck deck = createDeck(Card.CK, Card.CQ, Card.CA, Card.SA);
        Table table = new Table(deck);

        Player winner = table.runGameAndGetWinner();

        assertTrue(table.getPlayer().hasBlackjack());
        assertTrue(table.getDealer().hasBlackjack());
        assertEquals("sam", winner.getName());
    }

    @Test
    void DealerWinsWhenTheyAloneHaveBlackjack() throws Exception {
        Deck deck = createDeck(Card.C4, Card.CQ, Card.C5, Card.SA);
        Table table = new Table(deck);

        Player winner = table.runGameAndGetWinner();

        assertFalse(table.getPlayer().hasBlackjack());
        assertTrue(table.getDealer().hasBlackjack());
        assertEquals("dealer", winner.getName());
    }

    @Test
    void DealerWinsWhenPlayerIsBust() throws Exception {
        Deck deck = createDeck(Card.C10, Card.CK, Card.C6, Card.C2, Card.CA, Card.C3);
        Table table = new Table(deck);

        Player winner = table.runGameAndGetWinner();

        assertTrue(table.getPlayer().isBust());
        assertEquals(12, table.getDealer().getScore());
        assertEquals("dealer", winner.getName());
    }

    @Test
    void PlayerWinsWhenDealerIsBust() throws Exception {
        Deck deck = createDeck(Card.C10, Card.CK, Card.C3, Card.C7, Card.C5, Card.CA);
        Table table = new Table(deck);

        Player winner = table.runGameAndGetWinner();

        assertFalse(table.getPlayer().isBust());
        assertTrue(table.getDealer().isBust());
        assertEquals("sam", winner.getName());
    }

    @Test
    void DealerWinsWhenDrawsAreDoneAndNoOneIsBust() throws Exception {
        Deck deck = createDeck(Card.C10, Card.CK, Card.C7, Card.C3, Card.S2, Card.D2, Card.H2);
        Table table = new Table(deck);

        Player winner = table.runGameAndGetWinner();

        assertFalse(table.getPlayer().isBust());
        assertFalse(table.getDealer().isBust());
        assertEquals("dealer", winner.getName());
    }

    private Deck createDeck(Card... cards) {
        ArrayList<Card> listOfCards = new ArrayList<>(List.of(cards));

        return new Deck(listOfCards);
    }

}