package io.github.marmer.morse.usecases;

import io.github.marmer.morse.domain.InputTypeDetector;
import io.github.marmer.morse.domain.InputTypeDetector.InputType;
import io.github.marmer.morse.domain.MorseDecoder;
import io.github.marmer.morse.domain.MorseEncoder;
import io.github.marmer.morse.domain.Printer;
import java.util.stream.Collectors;
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
        printer.print(Stream.of(textToTranslate.split("[\r\n]+"))
            .map(this::translateLine)
            .collect(Collectors.joining("\n", "", "")));
    }

    private String translateLine(String lineToTranslate) {
        if (inputTypeDetector.getTypeOf(lineToTranslate) == InputType.MORSE) {
            return morseDecoder.decode(lineToTranslate);
        } else {
            return morseEncoder.encode(lineToTranslate);
        }
    }
}

