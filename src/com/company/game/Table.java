package com.company.game;

import com.company.types.Deck;
import com.company.types.Player;
import com.company.types.BlackjackException;
import com.company.types.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.types.BlackjackErrorCode.DECK_RAN_OUT_OF_CARDS;

public class Table {
    private final Player player = new Player("sam");
    private final Player dealer = new Player("dealer");
    private final Deck deck;

    public Table(final Deck deck) {
        if (deck != null) {
            this.deck = new Deck(deck.getCards());
        } else {
            this.deck = new Deck(new ArrayList<>());
        }

    }

    public Table() {
        this.deck = new Deck();
    }

    Player getPlayer() {
        return new Player(player.getName(), player.getScore(), player.getHand());
    }

    Player getDealer() {
        return new Player(dealer.getName(), dealer.getScore(), dealer.getHand());
    }

    public void startGame() throws Exception {
        Player winner = runGameAndGetWinner();

        System.out.println(winner.getName());
        System.out.println(player.getPrintableHand());
        System.out.println(dealer.getPrintableHand());
    }

    public Player runGameAndGetWinner() throws Exception {
        initialDraw();

        if (player.hasBlackjack()) {
            return player;
        } else if (dealer.hasBlackjack()) {
            return dealer;
        } else {
            playerDraw();

            if (player.isBust()) {
                return dealer;
            } else {
                dealerDraw();
            }
        }

        return dealer.isBust() ? player : getPlayerWithHighestScore();
    }

    void initialDraw() throws Exception {
        List<Player> players = List.of(player, dealer);
        for (int j = 0; j < 2; j++) {
            for (final Player p : players) {
                drawCardFromDeck(p);
            }
        }
    }

    void playerDraw() throws Exception {
        while (player.getScore() < 17) {
            drawCardFromDeck(player);
        }
    }

    void dealerDraw() throws Exception {
        while (dealer.getScore() <= player.getScore()) {
            drawCardFromDeck(dealer);
        }
    }

    void drawCardFromDeck(final Player p) throws Exception {
        final Optional<Card> card = deck.draw();

        if (card.isPresent()) {
            p.addCard(card.get());
        } else {
            throw new BlackjackException(DECK_RAN_OUT_OF_CARDS.getValue());
        }
    }

    private Player getPlayerWithHighestScore() {
        return dealer.getScore() >= player.getScore() ? dealer : player;
    }
}
