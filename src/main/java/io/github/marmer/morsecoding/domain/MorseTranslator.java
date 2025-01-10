package io.github.marmer.morsecoding.domain;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class MorseTranslator {
    private final Map<String, String> plainToMorseDict = new HashMap<>();

    public MorseTranslator() {
        plainToMorseDict.put("A", ".-");
        plainToMorseDict.put("B", "-...");
        plainToMorseDict.put("C", "-.-.");
        plainToMorseDict.put("D", "-..");
        plainToMorseDict.put("E", ".");
        plainToMorseDict.put("F", "..-.");
        plainToMorseDict.put("G", "--.");
        plainToMorseDict.put("H", "....");
        plainToMorseDict.put("I", "..");
        plainToMorseDict.put("J", ".---");
        plainToMorseDict.put("K", "-.-");
        plainToMorseDict.put("L", ".-..");
        plainToMorseDict.put("M", "--");
        plainToMorseDict.put("N", "-.");
        plainToMorseDict.put("O", "---");
        plainToMorseDict.put("P", ".--.");
        plainToMorseDict.put("Q", "--.-");
        plainToMorseDict.put("R", ".-.");
        plainToMorseDict.put("S", "...");
        plainToMorseDict.put("T", "-");
        plainToMorseDict.put("U", "..-");
        plainToMorseDict.put("V", "...-");
        plainToMorseDict.put("W", ".--");
        plainToMorseDict.put("X", "-..-");
        plainToMorseDict.put("Y", "-.--");
        plainToMorseDict.put("Z", "--..");
        plainToMorseDict.put("0", "-----");
        plainToMorseDict.put("1", ".----");
        plainToMorseDict.put("2", "..---");
        plainToMorseDict.put("3", "...--");
        plainToMorseDict.put("4", "....-");
        plainToMorseDict.put("5", ".....");
        plainToMorseDict.put("6", "-....");
        plainToMorseDict.put("7", "--...");
        plainToMorseDict.put("8", "---..");
        plainToMorseDict.put("9", "----.");
        plainToMorseDict.put(".", ".-.-.-");
        plainToMorseDict.put(",", "--..--");
        plainToMorseDict.put("?", "..--..");
        plainToMorseDict.put("'", ".----.");
        plainToMorseDict.put("!", "-.-.--");
        plainToMorseDict.put("/", "-..-.");
        plainToMorseDict.put("(", "-.--.");
        plainToMorseDict.put(")", "-.--.-");
        plainToMorseDict.put("&", ".-...");
        plainToMorseDict.put(":", "---...");
        plainToMorseDict.put(";", "-.-.-.");
        plainToMorseDict.put("=", "-...-");
        plainToMorseDict.put("+", ".-.-.");
        plainToMorseDict.put("-", "-....-");
        plainToMorseDict.put("_", "..--.-");
        plainToMorseDict.put("$", "...-..-");
        plainToMorseDict.put("@", ".--.-.");
        plainToMorseDict.put(" ", " "); // <- This is a hack ... but it works
    }

    public String translate(String input) {
        requireNonNull(input, "There can not be no input");

        return toWords(input)
                .map(this::encodeChar)
                .collect(Collectors.joining(" "));
    }

    @NotNull
    private static Stream<Character> toWords(String input) {
        return input.chars()
                .mapToObj(c -> ((char) c));
    }

    private String encodeChar(Character it) {
        return plainToMorseDict.getOrDefault(it.toString().toUpperCase(), plainToMorseDict.get("?"));
    }
}
