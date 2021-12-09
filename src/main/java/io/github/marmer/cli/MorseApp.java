package io.github.marmer.cli;

import io.github.marmer.ConsolePrinter;
import io.github.marmer.InputTypeDetector;
import io.github.marmer.InputTypeDetector.InputType;
import io.github.marmer.MorseTranslator;
import io.github.marmer.Printer;
import io.github.marmer.ResourceMorseDictionary;

public class MorseApp {

    private final Printer printer;
    private final MorseTranslator morseTranslator;
    private final InputTypeDetector inputTypeDetector;

    public MorseApp(Printer printer, MorseTranslator morseTranslator,
        InputTypeDetector inputTypeDetector) {
        this.printer = printer;
        this.morseTranslator = morseTranslator;
        this.inputTypeDetector = inputTypeDetector;
    }

    public static void main(String... args) {
        new MorseApp(
            new ConsolePrinter(),
            new MorseTranslator(new ResourceMorseDictionary("/morse.dict")),
            new InputTypeDetector())
            .run(args);
    }

    void run(String... args) {
        final String toTranslate = args[args.length - 1];

        if (inputTypeDetector.getTypeOf(toTranslate) == InputType.MORSE) {
            printer.print(morseTranslator.morseToText(toTranslate));
        } else {
            printer.print(morseTranslator.textToMorse(toTranslate));
        }
    }
}

