package io.github.marmer.morse.adapter.cli;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class CLIProcessor {

    public Optional<String> getDefaultInput(String[] args) {
        return Optional.of(args[args.length - 1]);
    }

    public Optional<Path> getOutFile(String[] args) {
        return getParameterValueFor("-o=", args);
    }

    public Optional<Path> getInFile(String[] args) {
        return getParameterValueFor("-i=", args);
    }

    @NotNull
    private Optional<Path> getParameterValueFor(String prefix, String[] args) {
        return Stream.of(args)
            .filter(it -> it.startsWith(prefix))
            .map(it -> it.replace(prefix, ""))
            .map(Paths::get)
            .findAny();
    }
}
