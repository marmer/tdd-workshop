package io.github.marmer.usecases;

import io.github.marmer.domain.InputTypeDetector;
import io.github.marmer.domain.InputTypeDetector.InputType;
import io.github.marmer.domain.MorseDecoder;
import io.github.marmer.domain.MorseEncoder;
import io.github.marmer.domain.Printer;
import java.util.stream.Stream;

public class MorseTranslator {

    private final Printer printer;
    private final MorseEncoder morseEncoder;
    private final MorseDecoder morseDecoder;
    private final InputTypeDetector inputTypeDetector;

    public MorseTranslator(Printer printer, MorseEncoder morseEncoder,
        MorseDecoder morseDecoder, InputTypeDetector inputTypeDetector) {
        this.printer = printer;
        this.morseEncoder = morseEncoder;
        this.morseDecoder = morseDecoder;
        this.inputTypeDetector = inputTypeDetector;
    }


    public void translate(String textToTranslate) {
        Stream.of(textToTranslate.split("[\r\n]+"))
            .forEach(this::translateLine);
    }

    private void translateLine(String lineToTranslate) {
        if (inputTypeDetector.getTypeOf(lineToTranslate) == InputType.MORSE) {
            printer.print(morseDecoder.decode(lineToTranslate));
        } else {
            printer.print(morseEncoder.encode(lineToTranslate));
        }
    }
}

