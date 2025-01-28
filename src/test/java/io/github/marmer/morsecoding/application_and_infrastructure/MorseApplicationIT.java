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
        CliArguments cliArguments = new CliArguments(new String[]{"The Pug"});
        var underTest = new MorseApplication(cliArguments, outputContainer::add);

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
        CliArguments cliArguments = new CliArguments(new String[]{"- .... .   .--. ..- --."});
        var underTest = new MorseApplication(
                cliArguments, outputContainer::add);

        // Act
        underTest.run();

        // Assert
        assertThat(outputContainer).contains("THE PUG");
    }

    @Test
    @DisplayName("Should translate plain text from a file to morse code and print it at the console")
    @SneakyThrows
    void run_ShouldTranslatePlainTextFromAFileToMorseCodeAndPrintItAtTheConsole() {
        // Arrange
        CliArguments cliArguments = new CliArguments(new String[]{"-i=src/test/resources/plainText.txt"});
        var underTest = new MorseApplication(cliArguments, outputContainer::add);

        // Act
        underTest.run();

        // Assert
        assertThat(outputContainer).contains("- .... .   .--. ..- --.");
        // TODO: marmer 2025-01-10 Multiline support
    }

    // TODO: marmer 2025-01-10 output file
    // TODO: marmer 2025-01-10 input at the end of the arguments
}
