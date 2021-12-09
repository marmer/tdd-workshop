package io.github.marmer.morse;

import io.github.marmer.morse.domain.InputTypeDetector;
import io.github.marmer.morse.domain.MorseDecoder;
import io.github.marmer.morse.domain.MorseDictionary;
import io.github.marmer.morse.domain.MorseEncoder;
import io.github.marmer.morse.usecases.MorseTranslator;

public class MorseTranslatorFactory {

    private final PrinterFactory printerFactory;
    private final MorseDictionary dict = new ResourceMorseDictionary("/morse.dict");

    public MorseTranslatorFactory(PrinterFactory printerFactory) {

        this.printerFactory = printerFactory;
    }

    public MorseTranslator create(String[] args) {
        return new MorseTranslator(
            printerFactory.create(args),
            new MorseEncoder(dict),
            new MorseDecoder(dict),
            new InputTypeDetector());
    }
}
