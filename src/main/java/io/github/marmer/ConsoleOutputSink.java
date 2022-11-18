package io.github.marmer;

public class ConsoleOutputSink implements OutputSink {

    @Override
    public void processOutput(final String output) {
        System.out.println(output);
    }
}
