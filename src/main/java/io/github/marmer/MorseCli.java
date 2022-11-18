package io.github.marmer;

public class MorseCli {


    private final OutputSink outputSink;
    private final MorseTranslator morseTranslator;


    public MorseCli(final MorseTranslator morseTranslator,
        final OutputSinkFactory outputSinkFactory) {
        outputSink = outputSinkFactory.getOutputSink();
        this.morseTranslator = morseTranslator;
    }

    public static void main(final String... args) {
        new MorseCli(new MorseTranslator(), new CliOutputSinkFactory(args)).run(args);
    }

    public void run(final String... args) {
        outputSink.processOutput(translate(testInputTextFrom(args)));
    }

    private static String testInputTextFrom(final String[] args) {
        return args[0];
    }

    private String translate(final String inputText) {
        if (isMorse(inputText)) {
            return morseTranslator.fromMorse(inputText);
        } else {
            return morseTranslator.toMorse(inputText);
        }
    }

    private static boolean isMorse(final String inputText) {
        return inputText.matches("^[ .-]+$");
    }
}
