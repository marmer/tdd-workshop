package io.github.marmer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class MorseTranslator {

    public static final String SYMBOL_DELIMITER = " ";
    public static final String WORD_DELIMITER = "   ";
    private final Map<String, String> textToMorseDict = new HashMap<>() {
        {
            put("A", ".-");
            put("B", "-...");
            put("C", "-.-.");
            put("D", "-..");
            put("E", ".");
            put("F", "..-.");
            put("G", "--.");
            put("H", "....");
            put("I", "..");
            put("J", ".---");
            put("K", "-.-");
            put("L", ".-..");
            put("M", "--");
            put("N", "-.");
            put("O", "---");
            put("P", ".--.");
            put("Q", "--.-");
            put("R", ".-.");
            put("S", "...");
            put("T", "-");
            put("U", "..-");
            put("V", "...-");
            put("W", ".--");
            put("X", "-..-");
            put("Y", "-.--");
            put("Z", "--..");
            put("0", "-----");
            put("1", ".----");
            put("2", "..---");
            put("3", "...--");
            put("4", "....-");
            put("5", ".....");
            put("6", "-....");
            put("7", "--...");
            put("8", "---..");
            put("9", "----.");
            put(".", ".-.-.-");
            put(",", "--..--");
            put("?", "..--..");
            put("'", ".----.");
            put("!", "-.-.--");
            put("/", "-..-.");
            put("(", "-.--.");
            put(")", "-.--.-");
            put("&", ".-...");
            put(":", "---...");
            put(";", "-.-.-.");
            put("=", "-...-");
            put("+", ".-.-.");
            put("-", "-....-");
            put("_", "..--.-");
            put("$", "...-..-");
            put("@", ".--.-.");
        }
    };

    private final Map<String, String> morseToTextDict = textToMorseDict
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getValue, Entry::getKey));


    public String toMorse(final String inputText) {
        return joinMorseWords(
            toWords(inputText)
                .map(this::toMorseWord));
    }

    @NotNull
    private static String joinMorseWords(final Stream<String> stringStream) {
        return stringStream
            .collect(Collectors.joining(WORD_DELIMITER));
    }

    @NotNull
    private String toMorseWord(final String word) {
        return toSymbolStream(word)
            .map(this::symbolToMorse)
            .collect(Collectors.joining(SYMBOL_DELIMITER));
    }

    @NotNull
    private static Stream<String> toWords(final String inputText) {
        return Arrays.stream(inputText.split(" "));
    }

    private String symbolToMorse(final String key) {
        return textToMorseDict.getOrDefault(key.toUpperCase(), "..--..");
    }

    @NotNull
    private static Stream<String> toSymbolStream(final String inputText) {
        return inputText.chars()
            .mapToObj(Character::toString);
    }

    public String fromMorse(final String eingabe) {
        return Arrays.stream(eingabe.split(WORD_DELIMITER))
            .map(this::toTextWord)
            .collect(Collectors.joining(" "));
    }

    @NotNull
    private String toTextWord(final String word) {
        return toMorseSymbol(word)
            .map(this::morseToSymbol)
            .collect(Collectors.joining());
    }

    @NotNull
    private static Stream<String> toMorseSymbol(final String eingabe) {
        return Arrays.stream(eingabe.split(SYMBOL_DELIMITER));
    }

    private String morseToSymbol(final String key) {
        return morseToTextDict.getOrDefault(key, "?");
    }
}
