package io.github.marmer.morsecoding.application_and_infrastructure;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

class MorseApplicationE2eIT {
    ByteArrayOutputStream result;
    private PrintStream oldOut;

    @BeforeEach
    void setUp() {
        result = new ByteArrayOutputStream();
        oldOut = System.out;
        System.setOut(new PrintStream(result));
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    @DisplayName("Should print morse code to a given natural language text to console")
    @SneakyThrows
    void shouldPrintMorseCodeToAGivenNaturalLanguageTextToConsole() {
        // Arrange

        // Act
        MorseApplication.main("The Pug");

        // Assert
        assertThat(getOutPutStringOf(result)).contains("- .... .   .--. ..- --.");

    }


    private static String getOutPutStringOf(ByteArrayOutputStream result) {
        return result.toString(Charset.defaultCharset());
    }
}
