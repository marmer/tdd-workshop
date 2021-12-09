package io.github.marmer.morse.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.morse.ResourceMorseDictionary;
import io.github.marmer.morse.domain.InputTypeDetector;
import io.github.marmer.morse.domain.MorseDecoder;
import io.github.marmer.morse.domain.MorseEncoder;
import io.github.marmer.morse.domain.Printer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MorseTranslatorTest {

    private MorseTranslator underTest;
    private String writtenLines;

    @BeforeEach
    void setUp() {
        writtenLines = null;
        final Printer printerMock = it -> writtenLines = it;
        underTest = new MorseTranslator(
            printerMock,
            new MorseEncoder(new ResourceMorseDictionary("/morse.dict")),
            new MorseDecoder(new ResourceMorseDictionary("/morse.dict")),
            new InputTypeDetector()
        );
    }

    @Test
    @DisplayName("Sollte bei Aufruf mit einem Morsecode Parameter Text ausgeben")
    @SneakyThrows
    void run_SollteBeiAufrufMitEinemMorsecodeParameterTextAusgeben() {
        // Preparation

        // Execution
        underTest.translate("-.. . .-.   -- --- .--. ...");

        // Assertion
        assertEquals(("DER MOPS"), writtenLines);
    }


    @Test
    @DisplayName("Sollte bei Aufruf mit einem Text Parameter Morsecode ausgeben")
    @SneakyThrows
    void run_SollteBeiAufrufMitEinemTextParameterMorsecodeAusgeben() {
        // Preparation

        // Execution
        underTest.translate("Der Mops");

        // Assertion
        assertEquals(("-.. . .-.   -- --- .--. ..."), writtenLines);
    }

    @Test
    @DisplayName("Sollte Mehrzeilige Eingaben übersetzen können")
    @SneakyThrows
    void run_SollteMehrzeiligeEingabenUebersetzenKoennen() {
        // Preparation

        // Execution
        underTest.translate("Der\n" +
            "Der Mops");

        // Assertion
        assertEquals(("-.. . .-.\n-.. . .-.   -- --- .--. ..."), writtenLines);
    }
}
