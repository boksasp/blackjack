package com.company.types;

public enum BlackjackErrorCode {
    DECK_RAN_OUT_OF_CARDS("Deck has run out of cards before the game could finish."),
    TOO_FEW_CARDS_TO_START_GAME("Too few cards in the deck to start the game.\nDeck must contain a minimum of 4 cards.");

    private final String value;

    BlackjackErrorCode(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
