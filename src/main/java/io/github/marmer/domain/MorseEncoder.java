package io.github.marmer.domain;

import static java.util.stream.Collectors.joining;

import io.github.marmer.MorseDictionary;

public class MorseEncoder {

    public static final String TEXT_WORD_SEPARATOR = " ";
    private final MorseDictionary morseSymbolDictionary;

    public MorseEncoder(MorseDictionary morseSymbolDictionary) {
        this.morseSymbolDictionary = morseSymbolDictionary;
    }

    public String encode(String text) {
        return text.chars()
            .mapToObj(Character::toString)
            .map(morseSymbolDictionary::getMorseBySymbol)
            .collect(joining(TEXT_WORD_SEPARATOR));
    }
}
