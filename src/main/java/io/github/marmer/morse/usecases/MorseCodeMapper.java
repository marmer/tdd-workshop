package io.github.marmer.morse.usecases;

import static java.util.Map.entry;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class MorseCodeMapper {

    public static final String CLEARTEXT_WORD_SEPARATOR = " ";
    public static final String MORSE_WORD_SEPARATOR = "   ";
    public static final String MORSE_SYMBOL_SEPARATOR = " ";
    private final Map<String, String> symbolToMorseDict = Map.ofEntries(
        entry("A", ".-"),
        entry("B", "-..."),
        entry("C", "-.-."),
        entry("D", "-.."),
        entry("E", "."),
        entry("F", "..-."),
        entry("G", "--."),
        entry("H", "...."),
        entry("I", ".."),
        entry("J", ".---"),
        entry("K", "-.-"),
        entry("L", ".-.."),
        entry("M", "--"),
        entry("N", "-."),
        entry("O", "---"),
        entry("P", ".--."),
        entry("Q", "--.-"),
        entry("R", ".-."),
        entry("S", "..."),
        entry("T", "-"),
        entry("U", "..-"),
        entry("V", "...-"),
        entry("W", ".--"),
        entry("X", "-..-"),
        entry("Y", "-.--"),
        entry("Z", "--.."),
        entry("0", "-----"),
        entry("1", ".----"),
        entry("2", "..---"),
        entry("3", "...--"),
        entry("4", "....-"),
        entry("5", "....."),
        entry("6", "-...."),
        entry("7", "--..."),
        entry("8", "---.."),
        entry("9", "----."),
        entry(".", ".-.-.-"),
        entry(",", "--..--"),
        entry("?", "..--.."),
        entry("'", ".----."),
        entry("!", "-.-.--"),
        entry("/", "-..-."),
        entry("(", "-.--."),
        entry(")", "-.--.-"),
        entry("&", ".-..."),
        entry(":", "---..."),
        entry(";", "-.-.-."),
        entry("=", "-...-"),
        entry("+", ".-.-."),
        entry("-", "-....-"),
        entry("_", "..--.-"),
        entry("$", "...-..-"),
        entry("@", ".--.-.")
    );

    private final Map<String, String> morseToSymbolDict = transpose(symbolToMorseDict);

    @NotNull
    private Map<String, String> transpose(final Map<String, String> symbolToMorseDict) {
        return symbolToMorseDict.entrySet().stream().collect(
            Collectors.toMap(Entry::getValue,
                Entry::getKey));
    }

    public String encode(final String clearTextString) {
        return toEncodedWordStream(clearTextString)
            .collect(Collectors.joining(MORSE_WORD_SEPARATOR));
    }

    @NotNull
    private Stream<String> toEncodedWordStream(final String clearTextString) {
        return toWordStream(clearTextString)
            .map(this::encodeWord);
    }

    @NotNull
    private Stream<String> toWordStream(final String clearTextString) {
        return Stream.of(clearTextString.split(CLEARTEXT_WORD_SEPARATOR));
    }

    @NotNull
    private String encodeWord(final String word) {
        return concatToWord(
            toSymbolStream(word)
                .map(this::toMorseSymbol));
    }

    @NotNull
    private String concatToWord(final Stream<String> morseSymbolStream) {
        return morseSymbolStream
            .collect(Collectors.joining(CLEARTEXT_WORD_SEPARATOR));
    }

    @NotNull
    private Stream<String> toSymbolStream(final String word) {
        return word.chars()
            .mapToObj(Character::toString);
    }

    private String toMorseSymbol(final String symbol) {
        return symbolToMorseDict.getOrDefault(symbol.toUpperCase(), symbolToMorseDict.get("?"));
    }

    public String decode(final String morsecode) {
        return toMorseWordsStream(morsecode)
            .map(this::fromMorseWord)
            .collect(Collectors.joining(" "));
    }

    @NotNull
    private String fromMorseWord(final String morseWord) {
        return toMorseWordStream(morseWord)
            .map(this::fromMorse)
            .collect(Collectors.joining(""));
    }

    @NotNull
    private Stream<String> toMorseWordStream(final String morseWord) {
        return Stream.of(morseWord.split(MORSE_SYMBOL_SEPARATOR));
    }

    @NotNull
    private Stream<String> toMorseWordsStream(final String morsecode) {
        return Stream.of(morsecode.split(MORSE_WORD_SEPARATOR));
    }

    private String fromMorse(final String symbol) {
        return morseToSymbolDict.getOrDefault(symbol, "?");
    }
}
