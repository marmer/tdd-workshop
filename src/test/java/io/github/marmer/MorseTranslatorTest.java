package io.github.marmer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class MorseTranslatorTest {

    @ParameterizedTest
    @DisplayName("Einfacher Buchstabe sollte zu Morse übersetzt werden können")
    @SneakyThrows
    @CsvFileSource(delimiter = ' ',
        resources = "/morse.dict"
    )
    void textToMorse_EinfacherBuchstbaeSollteZuMorseUebersetztWerdenKoennen(String input, String expectedOutput) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.textToMorse(input);

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
    void textToMorse_KannAuchBuchstabenkombinationenZuMorseUebersetzen(String info, String input,
        String expectedOutput) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.textToMorse(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @DisplayName("Einfacher Morse-Buchstabe sollte zu Buchstabe übersetzt werden können")
    @SneakyThrows
    @CsvFileSource(delimiter = ' ',
        resources = "/morse.dict"
    )
    void morseToText_EinfacherMorseBuchstbaeSollteZuBuchstabenUebersetztWerdenKoennen(String expectedOutput,
        String input) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.morseToText(input);

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
    void morseToText_KannAuchMorseBuchstabenkombinationenZuTextUebersetzen(String info, String expectedOutput,
        String input) {
        // Preparation
        var underTest = newMorseTranslator();

        // Execution
        var result = underTest.morseToText(input);

        // Assertion
        assertEquals(expectedOutput, result);
    }

    @NotNull
    private MorseTranslator newMorseTranslator() {
        return new MorseTranslator(new ResourceMorseDictionary("/morse.dict"));
    }
}
