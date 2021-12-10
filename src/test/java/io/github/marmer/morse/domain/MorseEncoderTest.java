package io.github.marmer.morse.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.morse.adapter.fs.ResourceMorseDictionary;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class MorseEncoderTest {

    @ParameterizedTest
    @DisplayName("Sollte Buchstabe in Morsecode übersetzen könnenXX")
    @SneakyThrows
    @CsvFileSource(
        resources = "/morse.dict",
        delimiter = ' ')
    void encode_SollteBuchstabeInMorsecodeUebersetzenKoennenX(
        final String input,
        final String expectedOutput) {
        // Preparation
        final var underTest = newMorseDictionary();

        // Execution
        final var result = underTest.encode(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @Test
    @DisplayName("Sollte mit leerem String klarkommen")
    @SneakyThrows
    void encode_SollteMitLeeremStringKlarkommen() {
        // Preparation
        final var underTest = newMorseDictionary();

        // Execution
        final var result = underTest.encode("");

        // Assertion
        assertEquals("", result);
    }

    @Test
    @DisplayName("Sollte mit unbekannten Zeichen klarkommen")
    @SneakyThrows
    void encode_SollteMitUnbekanntemZeichenKlarkommen() {
        // Preparation
        final var underTest = newMorseDictionary();

        // Execution
        final var result = underTest.encode("#");

        // Assertion
        assertEquals("..--..", result);
    }

    @Test
    @DisplayName("Sollte ganzes Wort übersetzen könnenn")
    @SneakyThrows
    void encode_SollteGanzesWortUebersetzenKoennenn() {
        // Preparation
        final var underTest = newMorseDictionary();

        // Execution
        final var result = underTest.encode("Mo");

        // Assertion
        assertEquals("-- ---", result);
    }

    @Test
    @DisplayName("Sollte mehrere Worte übersetzen können")
    @SneakyThrows
    void encode_SollteMehrereWorteUebersetzenKoennen() {
        // Preparation
        final var underTest = newMorseDictionary();

        // Execution
        final var result = underTest.encode("Der Mops");

        // Assertion
        assertEquals("-.. . .-.   -- --- .--. ...", result);
    }

    @NotNull
    private MorseEncoder newMorseDictionary() {
        return new MorseEncoder(new ResourceMorseDictionary("/morse.dict"));
    }
}
