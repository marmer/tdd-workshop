package io.github.marmer.morse;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.console.ConsolePrinter;
import io.github.marmer.morse.adapter.fs.FilePrinter;
import io.github.marmer.morse.domain.Printer;

public class PrinterFactory {

    private final CLIProcessor cliProcessor;

    public PrinterFactory(CLIProcessor cliProcessor) {
        this.cliProcessor = cliProcessor;
    }

    public Printer create(String[] args) {
        return cliProcessor.getOutFile(args)
            .<Printer>map(FilePrinter::new)
            .orElse(new ConsolePrinter());
    }
}
