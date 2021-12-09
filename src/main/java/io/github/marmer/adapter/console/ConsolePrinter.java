package io.github.marmer.adapter.console;

import io.github.marmer.domain.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
