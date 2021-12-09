package io.github.marmer;

import io.github.marmer.cli.InputReader;

public class AllMightyCLIFactory {

    private final String[] args;

    public AllMightyCLIFactory(String... args) {
        this.args = args;
    }

    public Printer createPrinter() {
        return new ConsolePrinter();
    }

    public InputReader createInputReader() {
        return () -> args[args.length - 1];
    }

    public MorseTranslator createMorseTranslator() {
        return new MorseTranslator(new ResourceMorseDictionary("/morse.dict"));
    }

    public InputTypeDetector createInputTypeDetector() {
        return new InputTypeDetector();
    }

}
