package io.github.marmer.morse.adapter.console;

import io.github.marmer.morse.usecases.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(final String toPrint) {
        System.out.println(toPrint);
    }
}
