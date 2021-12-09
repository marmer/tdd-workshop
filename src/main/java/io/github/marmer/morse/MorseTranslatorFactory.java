package io.github.marmer.morse;

import io.github.marmer.morse.domain.InputTypeDetector;
import io.github.marmer.morse.domain.MorseDecoder;
import io.github.marmer.morse.domain.MorseDictionary;
import io.github.marmer.morse.domain.MorseEncoder;
import io.github.marmer.morse.usecases.MorseTranslator;
import java.nio.file.Path;

public class MorseTranslatorFactory {

    private final PrinterFactory printerFactory;
    private final MorseDictionary dict = new ResourceMorseDictionary("/morse.dict");

    public MorseTranslatorFactory(PrinterFactory printerFactory) {

        this.printerFactory = printerFactory;
    }

    public MorseTranslator create() {
        return new MorseTranslator(
            printerFactory.create(),
            new MorseEncoder(dict),
            new MorseDecoder(dict),
            new InputTypeDetector());
    }

    public MorseTranslator createWithOutFile(Path outFile) {
        return new MorseTranslator(
            printerFactory.create(outFile),
            new MorseEncoder(dict),
            new MorseDecoder(dict),
            new InputTypeDetector());
    }
}
