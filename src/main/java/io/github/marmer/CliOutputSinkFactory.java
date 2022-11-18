package io.github.marmer;

import java.util.Optional;
import java.util.stream.Stream;

public class CliOutputSinkFactory implements OutputSinkFactory {

    private final OutputSink outputSink;

    public CliOutputSinkFactory(final String... args) {
        final Optional<FileOutputSink> optionalOutputSink = Stream.of(args)
            .filter(it -> it.startsWith("-o="))
            .findFirst()
            .map(it -> new FileOutputSink(it.replaceFirst("-o=", "")));

        if (optionalOutputSink.isPresent()) {
            outputSink = optionalOutputSink.get();
        } else {
            outputSink = new ConsoleOutputSink();
        }
    }

    @Override
    public OutputSink getOutputSink() {
        return outputSink;
    }
}
