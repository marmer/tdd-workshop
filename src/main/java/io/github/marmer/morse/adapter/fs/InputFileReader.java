package io.github.marmer.morse.adapter.fs;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;

public class InputFileReader {

    @SneakyThrows
    public String read(final Path file) {
        return Files.readString(file);
    }
}
