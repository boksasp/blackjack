package com.company;

import com.company.game.Table;
import com.company.util.DeckFileReader;
import com.company.util.ErrorHandler;

public class Blackjack {
    public static void main(String[] args) {
        try {
            Table tab;
            if (args.length > 0) {
                tab = new Table(new DeckFileReader().readInputFileFromDisk(args[0]));
            } else {
                tab = new Table();
            }
            tab.startGame();
        } catch (Exception ex) {
            ErrorHandler errorHandler = new ErrorHandler();
            errorHandler.EndProgramWithMessage(ex.getMessage());
        }
    }
}
