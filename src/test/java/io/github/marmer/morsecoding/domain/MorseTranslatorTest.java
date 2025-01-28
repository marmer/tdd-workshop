package io.github.marmer.morsecoding.domain;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MorseTranslatorTest {
    private MorseTranslator underTest = new MorseTranslator();

    @Test
    @DisplayName("should translate a given plain text symbol A to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolAToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("a"));

        // Assert
        assertThat(result).isEqualTo(".-");
    }

    @Test
    @DisplayName("should translate a given plain text symbol B to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolBToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("b"));

        // Assert
        assertThat(result).isEqualTo("-...");
    }

    @Test
    @DisplayName("should translate a given upper case plain text symbol B to morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenUpperCasePlainTextSymbolBToMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("B"));

        // Assert
        assertThat(result).isEqualTo("-...");
    }


    @Test
    @DisplayName("should translate unkown charts to question marks equivalent")
    @SneakyThrows
    void translate_ShouldTranslateUnkownChartsToQuestionMarksEquivalent() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("#"));

        // Assert
        assertThat(result).isEqualTo("..--..");
    }

    @Test
    @DisplayName("should translate a given plain text word intomorse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextWordIntomorseCode() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("The"));

        // Assert
        assertThat(result).isEqualTo("- .... .");
    }

    @Test
    @DisplayName("should translate a given plain text sentence into Morse code")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSentenceIntoMorseCode() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("The pug"));

        // Assert
        assertThat(result).isEqualTo("- .... .   .--. ..- --.");
    }


    @Test
    @DisplayName("empty strings should stay emtpy strings")
    @SneakyThrows
    void translate_EmptyStringsShouldStayEmtpyStrings() {
        // Arrange

        // Act
        var result = underTest.translate(new Message(""));

        // Assert
        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("should translate a given plain text symbol A to morse code ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolAToMorseCodeTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message(".-"));

        // Assert
        assertThat(result).isEqualTo("A");
    }

    @Test
    @DisplayName("should translate a given plain text symbol B to morse code ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSymbolBToMorseCodeTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("-..."));

        // Assert
        assertThat(result).isEqualTo("B");
    }

    @Test
    @DisplayName("should translate a given upper case plain text symbol B to morse code ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateAGivenUpperCasePlainTextSymbolBToMorseCodeTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("-..."));

        // Assert
        assertThat(result).isEqualTo("B");
    }


    @Test
    @DisplayName("should translate unkown charts to question marks equivalent ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateUnkownChartsToQuestionMarksEquivalentTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message(".-.-.-.-.-."));

        // Assert
        assertThat(result).isEqualTo("?");
    }

    @Test
    @DisplayName("should translate a given plain text word intomorse code ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextWordIntomorseCodeTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("- .... ."));

        // Assert
        assertThat(result).isEqualTo("THE");
    }

    @Test
    @DisplayName("should translate a given plain text sentence into Morse code ... the other way around")
    @SneakyThrows
    void translate_ShouldTranslateAGivenPlainTextSentenceIntoMorseCodeTheOtherWayAround() {
        // Arrange

        // Act
        var result = underTest.translate(new Message("- .... .   .--. ..- --."));

        // Assert
        assertThat(result).isEqualTo("THE PUG");
    }
}
