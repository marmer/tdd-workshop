package io.github.marmer.cli;

import io.github.marmer.AllMightyCLIFactory;
import io.github.marmer.InputTypeDetector;
import io.github.marmer.InputTypeDetector.InputType;
import io.github.marmer.MorseTranslator;
import io.github.marmer.Printer;

public class MorseApp {

    private final Printer printer;
    private final InputReader inputReader;
    private final MorseTranslator morseTranslator;
    private final InputTypeDetector inputTypeDetector;

    public MorseApp(Printer printer, InputReader inputReader, MorseTranslator morseTranslator,
        InputTypeDetector inputTypeDetector) {
        this.printer = printer;
        this.inputReader = inputReader;
        this.morseTranslator = morseTranslator;
        this.inputTypeDetector = inputTypeDetector;
    }

    public static void main(String... args) {
        var factory = new AllMightyCLIFactory(args);
        new MorseApp(
            factory.createPrinter(),
            factory.createInputReader(),
            factory.createMorseTranslator(),
            factory.createInputTypeDetector())
            .run();
    }

    void run() {
        final String toTranslate = inputReader.read();

        if (inputTypeDetector.getTypeOf(toTranslate) == InputType.MORSE) {
            printer.print(morseTranslator.morseToText(toTranslate));
        } else {
            printer.print(morseTranslator.textToMorse(toTranslate));
        }
    }
}

