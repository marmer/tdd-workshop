package io.github.marmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MorseCliTest {

    private List<String> outputs;

    private final OutputSinkFactory outputSinkFactory = mock(OutputSinkFactory.class);

    private MorseCli underTest;


    @BeforeEach
    void setUp() {
        outputs = new ArrayList<>();
        when(outputSinkFactory.getOutputSink()).thenReturn(it -> outputs.add(it));
        underTest = new MorseCli(new MorseTranslator(), outputSinkFactory);
    }

    @Test
    @DisplayName("Sollte einzelne Morsezeichen erkennen und nach Text übersetzen2")
    @SneakyThrows
    void run_SollteEinzelneMorsezeichenErkennenUndNachTextUebersetzen2() {
        // Preparation

        // Execution
        underTest.run("-.. . .-.   -- --- .--. ...");

        // Assertion
        assertThat(outputs).containsExactly("DER MOPS");
    }


    @Test
    @DisplayName("Sollte einzelnes Textzeichen erkennen und nach MorseTranslator übersetzen")
    @SneakyThrows
    void run_SollteEinzelnesTextzeichenErkennenUndNachMorseUebersetzen() {
        // Preparation

        // Execution
        underTest.run("Der Mops");

        // Assertion
        assertThat(outputs).containsExactly("-.. . .-.   -- --- .--. ...");
    }

    // TODO: marmer 17.11.2022 Output File only
    // TODO: marmer 17.11.2022 Input File Only
    // TODO: marmer 17.11.2022 Input File und Output File
    // TODO: marmer 17.11.2022 Output File und Input File (Reihenfolge der Parameter vertauscht)
    // TODO: marmer 17.11.2022 Input File und Input String
    // TODO: marmer 17.11.2022 Input File mit mehreren Zeilen
    // TODO: marmer 17.11.2022 Nicht existierendes Input File
    // TODO: marmer 17.11.2022 Leeres Input File
    // TODO: marmer 17.11.2022 Bereits existierendes Output File
}
