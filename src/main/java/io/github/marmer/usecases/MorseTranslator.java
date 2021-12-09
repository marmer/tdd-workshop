package io.github.marmer.usecases;

import io.github.marmer.domain.InputReader;
import io.github.marmer.domain.InputTypeDetector;
import io.github.marmer.domain.InputTypeDetector.InputType;
import io.github.marmer.domain.MorseDecoder;
import io.github.marmer.domain.MorseEncoder;
import io.github.marmer.domain.Printer;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class MorseTranslator {

    private final Printer printer;
    private final InputReader inputReader;
    private final MorseEncoder morseEncoder;
    private final MorseDecoder morseDecoder;
    private final InputTypeDetector inputTypeDetector;

    public MorseTranslator(Printer printer, InputReader inputReader, MorseEncoder morseEncoder,
        MorseDecoder morseDecoder, InputTypeDetector inputTypeDetector) {
        this.printer = printer;
        this.inputReader = inputReader;
        this.morseEncoder = morseEncoder;
        this.morseDecoder = morseDecoder;
        this.inputTypeDetector = inputTypeDetector;
    }


    @SneakyThrows
    public void run() {
        Stream.of(inputReader.read().split("[\r\n]+"))
            .forEach(this::translate);
    }

    private void translate(String toTranslate) {
        if (inputTypeDetector.getTypeOf(toTranslate) == InputType.MORSE) {
            printer.print(morseDecoder.decode(toTranslate));
        } else {
            printer.print(morseEncoder.encode(toTranslate));
        }
    }
}

