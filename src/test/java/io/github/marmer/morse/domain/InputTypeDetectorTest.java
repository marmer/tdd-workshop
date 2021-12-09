package io.github.marmer.morse.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.marmer.morse.domain.InputTypeDetector.InputType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputTypeDetectorTest {

    @Test
    @DisplayName("Should be able to recognize Text")
    @SneakyThrows
    void getTypeOf_ShouldBeAbleToRecognizeText() {
        // Preparation
        final var underTest = new InputTypeDetector();

        // Execution
        final var result = underTest.getTypeOf("Der Mops");

        // Assertion
        Assertions.assertEquals(InputType.TEXT, result);
    }

    @Test
    @DisplayName("Should be able to recognize MorseCode")
    @SneakyThrows
    void getTypeOf_ShouldBeAbleToRecognizeMorseCodeText() {
        // Preparation
        final var underTest = new InputTypeDetector();

        // Execution
        final var result = underTest.getTypeOf("-.. . .-.   -- --- .--. ...");

        // Assertion
        assertEquals(InputType.MORSE, result);
    }

}
