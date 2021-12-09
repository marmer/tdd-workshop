package io.github.marmer.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.InputTypeDetector;
import io.github.marmer.MorseTranslator;
import io.github.marmer.Printer;
import io.github.marmer.ResourceMorseDictionary;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MorseAppIT {

    private MorseApp underTest;
    private List<String> writtenLines;

    @BeforeEach
    void setUp() {
        writtenLines = new ArrayList<>();
        Printer printerMock = writtenLines::add;
        underTest = new MorseApp(
            printerMock,
            new MorseTranslator(new ResourceMorseDictionary("/morse.dict")),
            new InputTypeDetector()
        );
    }

    @Test
    @DisplayName("Sollte bei Aufruf mit einem Morsecode Parameter Text ausgeben")
    @SneakyThrows
    void run_SollteBeiAufrufMitEinemMorsecodeParameterTextAusgeben() {
        // Preparation

        // Execution
        underTest.run("-.. . .-.   -- --- .--. ...");

        // Assertion
        assertEquals(List.of("DER MOPS"), writtenLines);
    }


    @Test
    @DisplayName("Sollte bei Aufruf mit einem Text Parameter Morsecode ausgeben")
    @SneakyThrows
    void run_SollteBeiAufrufMitEinemTextParameterMorsecodeAusgeben() {
        // Preparation

        // Execution
        underTest.run("Der Mops");

        // Assertion
        assertEquals(List.of("-.. . .-.   -- --- .--. ..."), writtenLines);
    }
}
