package io.github.marmer;

public interface AllMigtyFactory {

    Printer createPrinter();

    MorseTranslator createMorseTranslator();

    InputTypeDetector createInputDetector();

}
