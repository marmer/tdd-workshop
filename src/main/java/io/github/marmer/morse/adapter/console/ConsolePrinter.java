package io.github.marmer.morse.adapter.console;

import io.github.marmer.morse.domain.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(final String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
