package io.github.marmer;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CliOutputSinkFactoryTest {

    @Test
    @DisplayName("Sollte Terminal output liefern, wenn kein Datei Eingabeparameter vorhanden")
    @SneakyThrows
    void getOutputSink_SollteTerminalOutputLiefernWennKeinDateiEingabeparameterVorhanden() {
        // Preparation
        final var underTest = new CliOutputSinkFactory("2347856378904");

        // Execution
        final var result = underTest.getOutputSink();

        // Assertion
        Assertions.assertThat(result).isInstanceOf(ConsoleOutputSink.class);
    }

    @Test
    @DisplayName("Sollte Datei output liefern, wenn Datei Eingabeparameter vorhanden")
    @SneakyThrows
    void getOutputSink_SollteDateiOutputLiefernWennKeinEingabeparameterVorhanden() {
        // Preparation
        final var underTest = new CliOutputSinkFactory("-o=2347856378904");

        // Execution
        final var result = underTest.getOutputSink();

        // Assertion
        Assertions.assertThat(result).isInstanceOf(FileOutputSink.class);
    }
}
