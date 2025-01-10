package io.github.marmer.morsecoding.domain;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MorseTranslatorTest {
    private MorseTranslator underTest = new MorseTranslator();

    @Test
    @DisplayName("should translate a given plain text symbol A to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolAToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate("a");

        // Assert
        assertThat(result).isEqualTo(".-");
    }

    @Test
    @DisplayName("should translate a given plain text symbol B to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolBToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate("b");

        // Assert
        assertThat(result).isEqualTo("-...");
    }

    @Test
    @DisplayName("should translate a given upper case plain text symbol B to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenUpperCasePlainTextSymbolBToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate("B");

        // Assert
        assertThat(result).isEqualTo("-...");
    }


    @Test
    @DisplayName("should translate unkown charts to question marks equivalent")
    @SneakyThrows
    void translate_ShouldTranslateUnkownChartsToQuestionMarksEquivalent() {
        // Arrange

        // Act
        var result = underTest.translate("#");

        // Assert
        assertThat(result).isEqualTo("..--..");
    }

    @Test
    @DisplayName("should translate a given plain text word intomorse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextWordIntomorseCode() {
        // Arrange

        // Act
        var result = underTest.translate("The");

        // Assert
        assertThat(result).isEqualTo("- .... .");
    }

    @Test
    @DisplayName("should translate a given plain text sentence into Morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSentenceIntoMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate("The pug");

        // Assert
        assertThat(result).isEqualTo("- .... .   .--. ..- --.");
    }


    @Test
    @DisplayName("empty strings should stay emtpy strings")
    @SneakyThrows
    void translate_EmptyStringsShouldStayEmtpyStrings() {
        // Arrange

        // Act
        var result = underTest.translate("");

        // Assert
        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("empty strings should stay null strings")
    @SneakyThrows
    void translate_EmptyStringsShouldStayNullStrings() {
        // Arrange

        // Assert
        var cause = assertThrows(NullPointerException.class,

                // Act
                () -> underTest.translate(null));

        assertThat(cause.getMessage()).isEqualTo("There can not be no input");
    }


}
