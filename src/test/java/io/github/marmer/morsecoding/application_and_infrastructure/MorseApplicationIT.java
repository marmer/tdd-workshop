package io.github.marmer.morsecoding.application_and_infrastructure;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MorseApplicationIT {

    private List<String> outputContainer = new ArrayList<>();

    @Test
    @DisplayName("should be able to translate a given plain text to morse code and print it to the console")
    @SneakyThrows
    void run_ShouldBeAbleToTranslateAGivenPlainTextToMorseCodeAndPrintItToTheConsole() {
        // Arrange
        Arguments arguments = new Arguments(new String[]{"The Pug"});
        var underTest = new MorseApplication(arguments, outputContainer::add);

        // Act
        underTest.run();

        // Assert
        assertThat(outputContainer).contains("- .... .   .--. ..- --.");
    }

    @Test
    @DisplayName("should be able to translate a given morse test to plain test and print it out to console")
    @SneakyThrows
    void run_ShouldBeAbleToTranslateAGivenMorseTestToPlainTestAndPrintItOutToConsole() {
        // Arrange
        Arguments arguments = new Arguments(new String[]{"- .... .   .--. ..- --."});
        var underTest = new MorseApplication(
                arguments, outputContainer::add);

        // Act
        underTest.run();

        // Assert
        assertThat(outputContainer).contains("The Pug");
    }

    // TODO: marmer 2025-01-10 no arguments passed
    // TODO: marmer 2025-01-10 too many arguments passed
}
