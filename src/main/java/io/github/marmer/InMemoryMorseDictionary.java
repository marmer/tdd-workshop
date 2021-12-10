package io.github.marmer;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.Map;

public class InMemoryMorseDictionary implements MorseDictionary {

    private final Map<String, String> morseBySymbol = ofEntries(
        Map.entry("A", ".-"),
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
        entry("@", ".--.-."),
        entry(" ", " ")
    );

    @Override
    public String toMorse(final String text) {
        return morseBySymbol.getOrDefault(text.toUpperCase(), "..--..");
    }
}
