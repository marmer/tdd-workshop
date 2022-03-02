package io.github.marmer.morse.usecases;


import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class MorseCodeMapperTest {

    private final MorseCodeMapper underTest = new MorseCodeMapper();

//    Gegeben: Zeichenkette wird übersetzt in Morse-String
//    Grundannahme: Niemand übergibt Zeichenketten, die nicht mindestens ein Wort mit der Länge von zwei Zeichen beinhalten
//    Fertiges Dictinoary: siehe Branch „morse“ unter /src/main/resources/morse.dict
//    Unbekannte Zeichen werden zum „Fragezeichen-Morsezeichen“ übersetzt
//    Ein Morsecode baut sich wie folgt auf:
//    Ein „Buchstabe“ wird repräsentiert durch Punkte und Striche
//    Zwischen Buchstaben gibt es ein Leerzeichen (kleine Pause)
//    Zwischen Worten gibt es drei Leerzeichen (lange Pause)
//    Bsp.: "Der Mops" = "-.. . .-.   -- --- .--. …"


    @ParameterizedTest
    @CsvFileSource(resources = "/morse.dict", delimiterString = " ")
    @DisplayName("Should translate single symbol to morseX")
    @SneakyThrows
    void encode_ShouldTranslateSingleSymbolToMorseX(final String toEncode, final String expectedOutcome) {
        // Preparation

        // Execution
        final var result = underTest.encode(toEncode);

        // Assertion
        assertEquals(expectedOutcome, result);
    }

    @Test
    @DisplayName("Sollte unbekanntes Zeichen zu Fragezeichen-Repräsentation übersetzen")
    @SneakyThrows
    void encode_SollteUnbekanntesZeichenZuFragezeichenRepraesentationUebersetzen() {
        // Preparation

        // Execution
        final var result = underTest.encode("Ü");

        // Assertion
        assertEquals("..--..", result);
    }

    @Test
    @DisplayName("Sollte auch in der lage sein Kleinbuchstaben zu übersetzen")
    @SneakyThrows
    void encode_SollteAuchInDerLageSeinKleinbuchstabenZuUebersetzen() {
        // Preparation

        // Execution
        final var result = underTest.encode("b");

        // Assertion
        assertEquals("-...", result);
    }

    @Test
    @DisplayName("Sollte einzelnes Wort übersetzen können")
    @SneakyThrows
    void encode_SollteEinzelnesWortUebersetzenKoennen() {
        // Preparation

        // Execution
        final var result = underTest.encode("Hund");

        // Assertion
        assertEquals(".... ..- -. -..", result);
    }

    @Test
    @DisplayName("Sollte mehrere Wörter übersetzen können")
    @SneakyThrows
    void encode_SollteMehrereWoerterUebersetzenKoennen() {
        // Preparation

        // Execution
        final var result = underTest.encode("Der Hund.");

        // Assertion
        assertEquals("-.. . .-.   .... ..- -. -.. .-.-.-", result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/morse.dict", delimiterString = " ")
    @DisplayName("Sollte einzelnen Buchstbaen von Morse nach Lesbar übersetzen")
    @SneakyThrows
    void decode_SollteEinzelnenBuchstbaenVonMorseNachLesbarUebersetzen(final String expectedOutcome,
        final String toDecode) {
        // Preparation

        // Execution
        final var result = underTest.decode(toDecode);

        // Assertion
        assertEquals(expectedOutcome, result);
    }

    @Test
    @DisplayName("Sollte unbekannte Zeichen in Fragezeichen übersetzen")
    @SneakyThrows
    void decode_SollteUnbekannteZeichenInFragezeichenUebersetzen() {
        // Preparation

        // Execution
        final var result = underTest.decode(".-.-.-.-.-.-.--");

        // Assertion
        assertEquals("?", result);
    }

    @Test
    @DisplayName("Sollte Morse Wort in Wort übersetzen können")
    @SneakyThrows
    void decode_SollteMorseWortInWortUebersetzenKoennen() {
        // Preparation

        // Execution
        final var result = underTest.decode(".... ..- -. -..");

        // Assertion
        assertEquals("HUND", result);
    }

    @Test
    @DisplayName("Sollte mehrere Morse Wörter übersetzen können")
    @SneakyThrows
    void decode_SollteMehrereMorseWoerterUebersetzenKoennen() {
        // Preparation

        // Execution
        final var result = underTest.decode("-.. . .-.   .... ..- -. -..");

        // Assertion
        assertEquals("DER HUND", result);
    }
}
