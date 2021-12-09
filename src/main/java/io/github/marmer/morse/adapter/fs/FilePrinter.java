package io.github.marmer.morse.adapter.fs;

import static java.nio.file.Files.writeString;

import io.github.marmer.morse.domain.Printer;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import lombok.SneakyThrows;

public class FilePrinter implements Printer {

    private final Path outputFile;

    public FilePrinter(Path outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    @SneakyThrows
    public void print(String stringToPrint) {
        writeString(outputFile, stringToPrint, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
