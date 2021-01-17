package com.company.types;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;
    private int score;


    public Player(final String name) {
        this.name = name;
        this.score = 0;
        this.hand = new ArrayList<>();
    }

    public Player(final String name, final int score, final ArrayList<Card> cards) {
        this.name = name;
        this.score = score;
        this.hand = new ArrayList<>(cards);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public String getPrintableHand() {
        return String.format(
                "%s: %s",
                this.name,
                hand.stream().map(Card::toString).collect(Collectors.joining(", "))
        );
    }

    public int getScore() {
        return score;
    }


    public void addCard(final Card card) {
        if (card != null) {
            hand.add(card);
            score += card.getValue();
        }
    }

    public boolean hasBlackjack() {
        return hand.size() == 2 && score == 21;
    }

    public boolean isBust() {
        return score > 21;
    }
}
