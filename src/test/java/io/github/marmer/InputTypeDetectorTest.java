package io.github.marmer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.domain.InputTypeDetector;
import io.github.marmer.domain.InputTypeDetector.InputType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputTypeDetectorTest {

    @Test
    @DisplayName("Should be able to recognize Text")
    @SneakyThrows
    void getTypeOf_ShouldBeAbleToRecognizeText() {
        // Preparation
        var underTest = new InputTypeDetector();

        // Execution
        var result = underTest.getTypeOf("Der Mops");

        // Assertion
        assertEquals(InputType.TEXT, result);
    }

    @Test
    @DisplayName("Should be able to recognize MorseCode")
    @SneakyThrows
    void getTypeOf_ShouldBeAbleToRecognizeMorseCodeText() {
        // Preparation
        var underTest = new InputTypeDetector();

        // Execution
        var result = underTest.getTypeOf("-.. . .-.   -- --- .--. ...");

        // Assertion
        assertEquals(InputType.MORSE, result);
    }

}
