package com.company.util;

public class ErrorHandler {
    private final String HELP_MESSAGE = "\nMake sure that:\n" +
            "\t1) The file exists\n" +
            "\t2) The file is readable\n" +
            "\t3) The contents are comma-separated values on the format [C|D|H|S][2-10|J|Q|K|A]\n" +
            "\t\tExample: DA, H2, H10, SQ, D4\n\n" +
            "You can also start the program without an input file. A shuffled deck is used instead.";

    public void EndProgramWithMessage(final String message) {
        System.out.println(message);
        System.out.println(HELP_MESSAGE);

        System.exit(1);
    }
}
