package io.github.marmer.morse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.fs.InputFileReader;
import io.github.marmer.morse.domain.Printer;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MorseAppTest {

    private MorseApp underTest;
    private PrinterFactory printerFactory;
    private Printer printerMock;
    private InputFileReader inputFileReaderMock;

    @BeforeEach
    void setUp() {
        printerMock = mock(Printer.class);
        printerFactory = spy(new PrinterFactory());
        inputFileReaderMock = mock(InputFileReader.class);
        underTest = new MorseApp(new CLIProcessor(), new MorseTranslatorFactory(printerFactory), inputFileReaderMock);
    }

    @Test
    @DisplayName("Standardübersetzung sollte funktionieren")
    @SneakyThrows
    void run_StandarduebersetzungSollteFunktionieren() {
        // Preparation
        when(printerFactory.create()).thenReturn(printerMock);

        // Execution
        underTest.run("-.. . .-.   -- --- .--. ...");

        // Assertion
        verify(printerMock).print("DER MOPS");
    }

    @Test
    @DisplayName("Übersetzung in Ausgabedatei sollte funktionieren")
    @SneakyThrows
    void run_UebersetzungAusEingabedateiSollteFunktionieren() {
        // Preparation
        when(printerFactory.create(Paths.get("outFilePath"))).thenReturn(printerMock);

        // Execution
        underTest.run("-o=outFilePath", "-.. . .-.   -- --- .--. ...");

        // Assertion
        verify(printerMock).print("DER MOPS");
    }

    @Test
    @DisplayName("Übersetzung mit Eingabedatei sollte funktionieren")
    @SneakyThrows
    void run_UebersetzungMitEingabedateiSollteFunktionieren() {
        // Preparation
        when(printerFactory.create()).thenReturn(printerMock);
        when(inputFileReaderMock.read(Paths.get("inputFilePath"))).thenReturn("-.. . .-.   -- --- .--. ...");

        // Execution
        underTest.run("-i=inputFilePath");

        // Assertion
        verify(printerMock).print("DER MOPS");
    }

    @Test
    @DisplayName("Übersetzung mit Eingabedatei und Ausgabedatei sollte funktionieren")
    @SneakyThrows
    void run_UebersetzungMitEingabedateiUndAusgabedateiSollteFunktionieren() {
        // Preparation
        when(printerFactory.create(Paths.get("outFilePath"))).thenReturn(printerMock);
        when(inputFileReaderMock.read(Paths.get("inputFilePath"))).thenReturn("-.. . .-.   -- --- .--. ...");

        // Execution
        underTest.run("-i=inputFilePath", "-o=outFilePath");

        // Assertion
        verify(printerMock).print("DER MOPS");
    }
}
