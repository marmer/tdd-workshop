package io.github.marmer.cli;

import io.github.marmer.AllMightyCLIFactory;
import io.github.marmer.InputTypeDetector;
import io.github.marmer.InputTypeDetector.InputType;
import io.github.marmer.MorseTranslator;
import io.github.marmer.Printer;
import java.io.BufferedReader;
import java.io.StringReader;
import lombok.SneakyThrows;

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

    @SneakyThrows
    void run() {
        try (var reader = new BufferedReader(new StringReader(inputReader.read()))) {
            reader.lines()
                .forEach(this::translate);
        }
    }

    private void translate(String toTranslate) {
        if (inputTypeDetector.getTypeOf(toTranslate) == InputType.MORSE) {
            printer.print(morseTranslator.morseToText(toTranslate));
        } else {
            printer.print(morseTranslator.textToMorse(toTranslate));
        }
    }
}

