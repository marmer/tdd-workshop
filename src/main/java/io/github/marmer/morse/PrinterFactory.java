package io.github.marmer.morse;

import io.github.marmer.morse.adapter.console.ConsolePrinter;
import io.github.marmer.morse.adapter.fs.FilePrinter;
import io.github.marmer.morse.domain.Printer;
import java.nio.file.Path;

public class PrinterFactory {

    public Printer create() {
        return new ConsolePrinter();
    }

    public Printer create(Path outputFile) {
        return new FilePrinter(outputFile);
    }
}
