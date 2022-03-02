package io.github.marmer.morse;

import io.github.marmer.morse.adapter.console.ConsolePrinter;
import io.github.marmer.morse.usecases.Printer;
import io.github.marmer.morse.usecases.PrinterFactory;

public class CommandLineArgsPrinterFactory implements PrinterFactory {

    private final String[] args;

    public CommandLineArgsPrinterFactory(final String... args) {
        this.args = args;
    }

    @Override
    public Printer createPrinter() {
        return new ConsolePrinter();
    }
}
