package io.github.marmer.morse.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.morse.adapter.fs.ResourceMorseDictionary;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class MorseDecoderTest {

    @ParameterizedTest
    @DisplayName("Einfacher Morse-Buchstabe sollte zu Buchstabe übersetzt werden können")
    @SneakyThrows
    @CsvFileSource(delimiter = ' ',
        resources = "/morse.dict"
    )
    void decode_EinfacherMorseBuchstbaeSollteZuBuchstabenUebersetztWerdenKoennen(final String expectedOutput,
        final String input) {
        // Preparation
        final var underTest = newMorseDecoder();

        // Execution
        final var result = underTest.decode(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @DisplayName("Kann auch MorseBuchstabenkombination zu Text übersetzen")
    @CsvSource(delimiter = ';',
        value = {
            "Ein Wort:;MOPS;-- --- .--. ...;",
            "Mehrere Wortet:;DER MOPS;-.. . .-.   -- --- .--. ...",
            "Unbekannte Zeichen:;A?B?C;.- ..--.-.-.-... -... ..-.-.-.-.. -.-.",
        })
    void decode_KannAuchMorseBuchstabenkombinationenZuTextUebersetzen(final String info, final String expectedOutput,
        final String input) {
        // Preparation
        final var underTest = newMorseDecoder();

        // Execution
        final var result = underTest.decode(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @NotNull
    private MorseDecoder newMorseDecoder() {
        return new MorseDecoder(new ResourceMorseDictionary("/morse.dict"));
    }
}
