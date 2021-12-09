package io.github.marmer.morse.domain;

import static java.util.stream.Collectors.joining;

public class MorseEncoder {

    public static final String TEXT_WORD_SEPARATOR = " ";
    private final MorseDictionary morseSymbolDictionary;

    public MorseEncoder(final MorseDictionary morseSymbolDictionary) {
        this.morseSymbolDictionary = morseSymbolDictionary;
    }

    public String encode(final String text) {
        return text.chars()
            .mapToObj(Character::toString)
            .map(morseSymbolDictionary::getMorseBySymbol)
            .collect(joining(TEXT_WORD_SEPARATOR));
    }
}
