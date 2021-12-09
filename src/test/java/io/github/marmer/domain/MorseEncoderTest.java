package io.github.marmer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.ResourceMorseDictionary;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class MorseEncoderTest {

    @ParameterizedTest
    @DisplayName("Einfacher Buchstabe sollte zu Morse übersetzt werden können")
    @SneakyThrows
    @CsvFileSource(delimiter = ' ',
        resources = "/morse.dict"
    )
    void encode_EinfacherBuchstbaeSollteZuMorseUebersetztWerdenKoennen(String input, String expectedOutput) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.encode(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @DisplayName("Kann auch Buchstabenkombinationen zu Morse übersetzen")
    @CsvSource(delimiter = ';',
        value = {
            "Ein Wort:;Mops;-- --- .--. ...;",
            "Mehrere Wortet:;Der Mops;-.. . .-.   -- --- .--. ...",
            "Unbekannte Zeichen:;A*B#C;.- ..--.. -... ..--.. -.-.",
        })
    void encode_KannAuchBuchstabenkombinationenZuMorseUebersetzen(String info, String input,
        String expectedOutput) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.encode(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @NotNull
    private MorseEncoder newMorseTranslator() {
        return new MorseEncoder(new ResourceMorseDictionary("/morse.dict"));
    }
}
