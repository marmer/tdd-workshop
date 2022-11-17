package io.github.marmer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MorseTest {

    private static Object[][] textToMorseTestData() {
        return new Object[][]{
            {"AA", ".- .-"},
            {"AB", ".- -..."},
            {"A B", ".-   -..."},
            {"§ B", "..--..   -..."},
            {"DER MOPS", "-.. . .-.   -- --- .--. ..."},
            {"Der Mops", "-.. . .-.   -- --- .--. ..."},
        };
    }


    private static Object[][] morseToTextTestData() {
        return new Object[][]{
            {".- .-", "AA"},
            {".- -...", "AB"},
            {".-   -...", "A B"},
            {"..--...-.-.-.   -...", "? B"},
            {"-.. . .-.   -- --- .--. ...", "DER MOPS"}
        };
    }

    @ParameterizedTest
    @MethodSource("textToMorseTestData")
    @DisplayName("Sollte Text nach Morse übersetzen können")
    @SneakyThrows
    void toMorse_SollteTextNachMorseUebersetzenKoennen(final String eingabe, final String erwarteteAusgabe) {
        // Preparation
        final var underTest = new Morse();

        // Execution
        final var result = underTest.toMorse(eingabe);

        // Assertion
        assertEquals(erwarteteAusgabe, result);
    }

    @ParameterizedTest
    @MethodSource("morseToTextTestData")
    @DisplayName("Sollte Morse nach Text übersetzen können")
    @SneakyThrows
    void toMorse_SollteMorseNachTextUebersetzenKoennen(final String eingabe, final String erwarteteAusgabe) {
        // Preparation
        final var underTest = new Morse();

        // Execution
        final var result = underTest.fromMorse(eingabe);

        // Assertion
        assertEquals(erwarteteAusgabe, result);
    }

}
