package io.github.marmer.morsecoding.domain;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MorseTranslator {
    private final Map<String, String> plainToMorseDict = new HashMap<>();
    private final Map<String, String> morseToPlainDict = new HashMap<>();

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

        morseToPlainDict.put(".-", "A");
        morseToPlainDict.put("-...", "B");
        morseToPlainDict.put("-.-.", "C");
        morseToPlainDict.put("-..", "D");
        morseToPlainDict.put(".", "E");
        morseToPlainDict.put("..-.", "F");
        morseToPlainDict.put("--.", "G");
        morseToPlainDict.put("....", "H");
        morseToPlainDict.put("..", "I");
        morseToPlainDict.put(".---", "J");
        morseToPlainDict.put("-.-", "K");
        morseToPlainDict.put(".-..", "L");
        morseToPlainDict.put("--", "M");
        morseToPlainDict.put("-.", "N");
        morseToPlainDict.put("---", "O");
        morseToPlainDict.put(".--.", "P");
        morseToPlainDict.put("--.-", "Q");
        morseToPlainDict.put(".-.", "R");
        morseToPlainDict.put("...", "S");
        morseToPlainDict.put("-", "T");
        morseToPlainDict.put("..-", "U");
        morseToPlainDict.put("...-", "V");
        morseToPlainDict.put(".--", "W");
        morseToPlainDict.put("-..-", "X");
        morseToPlainDict.put("-.--", "Y");
        morseToPlainDict.put("--..", "Z");
        morseToPlainDict.put("-----", "0");
        morseToPlainDict.put(".----", "1");
        morseToPlainDict.put("..---", "2");
        morseToPlainDict.put("...--", "3");
        morseToPlainDict.put("....-", "4");
        morseToPlainDict.put(".....", "5");
        morseToPlainDict.put("-....", "6");
        morseToPlainDict.put("--...", "7");
        morseToPlainDict.put("---..", "8");
        morseToPlainDict.put("----.", "9");
        morseToPlainDict.put(".-.-.-", ".");
        morseToPlainDict.put("--..--", ",");
        morseToPlainDict.put("..--..", "?");
        morseToPlainDict.put(".----.", "'");
        morseToPlainDict.put("-.-.--", "!");
        morseToPlainDict.put("-..-.", "/");
        morseToPlainDict.put("-.--.", "(");
        morseToPlainDict.put("-.--.-", ")");
        morseToPlainDict.put(".-...", "&");
        morseToPlainDict.put("---...", ":");
        morseToPlainDict.put("-.-.-.", ";");
        morseToPlainDict.put("-...-", "=");
        morseToPlainDict.put(".-.-.", "+");
        morseToPlainDict.put("-....-", "-");
        morseToPlainDict.put("..--.-", "_");
        morseToPlainDict.put("...-..-", "$");
        morseToPlainDict.put(".--.-.", "@");
    }


    public String translate(Message message) {
        if (message.isEmpty()) {
            return message.value();
        }

        if (message.isPlainText()) {
            return splitToPlainWords(message.value())
                    .map(this::encodeChar)
                    .collect(Collectors.joining(" "));
        } else {
            return Stream.of(message.value().split("   "))
                    .map(word -> Stream.of(word.split(" "))
                            .map(this::decodeChar)
                            .collect(Collectors.joining("")))
                    .collect(Collectors.joining(" "));
        }
    }

    private String decodeChar(String symbol) {
        return morseToPlainDict.getOrDefault(symbol, "?");
    }

    @NotNull
    private static Stream<Character> splitToPlainWords(String input) {
        return input.chars()
                .mapToObj(c -> ((char) c));
    }

    private String encodeChar(Character it) {
        return plainToMorseDict.getOrDefault(it.toString().toUpperCase(), plainToMorseDict.get("?"));
    }
}
