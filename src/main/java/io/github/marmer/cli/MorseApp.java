package io.github.marmer.cli;

import io.github.marmer.ConsolePrinter;
import io.github.marmer.InputTypeDetector;
import io.github.marmer.InputTypeDetector.InputType;
import io.github.marmer.MorseTranslator;
import io.github.marmer.Printer;
import io.github.marmer.ResourceMorseDictionary;

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
        new MorseApp(
            new ConsolePrinter(),
            () -> args[args.length - 1],
            new MorseTranslator(new ResourceMorseDictionary("/morse.dict")),
            new InputTypeDetector())
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

