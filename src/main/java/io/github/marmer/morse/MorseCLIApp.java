package io.github.marmer.morse;

import io.github.marmer.morse.usecases.MorseCodeMapper;
import io.github.marmer.morse.usecases.Printer;
import io.github.marmer.morse.usecases.PrinterFactory;

public class MorseCLIApp {

    private final Printer printer;
    private final MorseCodeMapper morseCodeMapper;
    private final String input;

    public static void main(final String... args) {
        new MorseCLIApp(new CommandLineArgsPrinterFactory(args), new MorseCodeMapper(), args).run();
    }

    public MorseCLIApp(final PrinterFactory printerFactory, final MorseCodeMapper morseCodeMapper,
        final String... args) {
        printer = printerFactory.createPrinter();
        this.morseCodeMapper = morseCodeMapper;
        input = args[0];
    }

    void run() {
        printer.print(isRegex() ?
            morseCodeMapper.decode(input) :
            morseCodeMapper.encode(input));
    }

    private boolean isRegex() {
        return input.matches("^[-. ]+$");
    }
}
