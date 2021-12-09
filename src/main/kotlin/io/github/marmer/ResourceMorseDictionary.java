package io.github.marmer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class ResourceMorseDictionary implements MorseDictionary {

    private final Map<String, String> morseBySymbol;
    private final Map<String, String> symbolByMorse;

    @SneakyThrows
    public ResourceMorseDictionary(String resourceFilePath) {
        try (final var reader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(resourceFilePath))))) {
            morseBySymbol = reader.lines()
                .filter(it -> !it.isBlank())
                .map(it -> it.split(" "))
                .collect(Collectors.toMap(it -> it[0], it -> it[1]));

            symbolByMorse = morseBySymbol.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
        }

        morseBySymbol.put(" ", " ");
    }

    public String getMorseBySymbol(String symbol) {
        return morseBySymbol.getOrDefault(symbol.toUpperCase(), "..--..");
    }

    @Override
    public String getSymbolByMorse(String morse) {
        return symbolByMorse.getOrDefault(morse, "?");
    }
}