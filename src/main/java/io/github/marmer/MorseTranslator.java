package io.github.marmer;

import static java.util.stream.Collectors.joining;

import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class MorseTranslator {

    public static final String MORSE_WORD_SEPERATOR = "   ";
    public static final String TEXT_WORD_SEPARATOR = " ";
    public static final String MORSE_SYMBOL_SEPERATOR = TEXT_WORD_SEPARATOR;
    private final MorseDictionary morseSymbolDictionary;

    public MorseTranslator(MorseDictionary morseSymbolDictionary) {
        this.morseSymbolDictionary = morseSymbolDictionary;
    }

    public String textToMorse(String text) {
        return text.chars()
            .mapToObj(Character::toString)
            .map(morseSymbolDictionary::getMorseBySymbol)
            .collect(joining(TEXT_WORD_SEPARATOR));
    }

    public String morseToText(String morse) {
        return Stream.of(wordsOf(morse))
            .map(this::morseWordToWord)
            .collect(joining(MORSE_SYMBOL_SEPERATOR));
    }

    @NotNull
    private String[] wordsOf(String morse) {
        return morse.split(MORSE_WORD_SEPERATOR);
    }

    @NotNull
    private String morseWordToWord(String word) {
        return Stream.of(word.split(MORSE_SYMBOL_SEPERATOR))
            .map(morseSymbolDictionary::getSymbolByMorse)
            .collect(joining(""));
    }
}
