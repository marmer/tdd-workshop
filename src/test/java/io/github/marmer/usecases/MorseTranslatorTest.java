package io.github.marmer.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.ResourceMorseDictionary;
import io.github.marmer.domain.InputTypeDetector;
import io.github.marmer.domain.MorseDecoder;
import io.github.marmer.domain.MorseEncoder;
import io.github.marmer.domain.Printer;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MorseTranslatorTest {

    private MorseTranslator underTest;
    private List<String> writtenLines;
    private String input;

    @BeforeEach
    void setUp() {
        writtenLines = new ArrayList<>();
        Printer printerMock = writtenLines::add;
        input = null;
        underTest = new MorseTranslator(
            printerMock,
            () -> input,
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
        input = "-.. . .-.   -- --- .--. ...";

        // Execution
        underTest.run();

        // Assertion
        assertEquals(List.of("DER MOPS"), writtenLines);
    }


    @Test
    @DisplayName("Sollte bei Aufruf mit einem Text Parameter Morsecode ausgeben")
    @SneakyThrows
    void run_SollteBeiAufrufMitEinemTextParameterMorsecodeAusgeben() {
        // Preparation
        input = "Der Mops";

        // Execution
        underTest.run();

        // Assertion
        assertEquals(List.of("-.. . .-.   -- --- .--. ..."), writtenLines);
    }

    @Test
    @DisplayName("Sollte Mehrzeilige Eingaben übersetzen können")
    @SneakyThrows
    void run_SollteMehrzeiligeEingabenUebersetzenKoennen() {
        // Preparation
        input = "Der\n" +
            "Der Mops";

        // Execution
        underTest.run();

        // Assertion
        assertEquals(List.of("-.. . .-.", "-.. . .-.   -- --- .--. ..."), writtenLines);
    }
}
