package io.github.marmer.morse;

import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


class MorseAppIT {

    private MorseApp underTest;

    @BeforeEach
    void setUp() {
        underTest = new MorseAppFactory().create();
    }

    @Test
    @DisplayName("Übersetzung mit Eingabedatei und Ausgabedatei sollte funktionieren")
    @SneakyThrows
    void run_UebersetzungMitEingabedateiUndAusgabedateiSollteFunktionieren(@TempDir Path tempDir) {
        // Preparation
        var inputFilePath = tempDir.resolve("inputFilePath");
        writeString(inputFilePath, "Der Mops\n-- --- .--. ...   -.. . .-.");

        var outputFilePath = tempDir.resolve("outputFilePath");

        // Execution
        underTest.run("-i=" + inputFilePath, "-o=" + outputFilePath);

        // Assertion
        assertEquals("-.. . .-.   -- --- .--. ...\nMOPS DER", readString(outputFilePath));
    }
}
