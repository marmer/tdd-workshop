package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.CoreMatchers.either;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


@RunWith(MockitoJUnitRunner.class)
public class MiningFileServiceTest {
    @InjectMocks
    private MiningFileService classUnderTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void testreadMineField_DateiExistiertNicht_SollteExceptionMitPassendenTextLiefern()
        throws Exception {
        // Vorbereitung
        final String notExistingPath = "Bestimm_nicht_existierende_datei";

        // Prüfung
        exception.expect(MinesweeperException.class);
        exception.expectMessage(is(equalTo("Datei nicht vorhanden: " + notExistingPath)));

        // Ausführung
        classUnderTest.readMineField(notExistingPath);
    }

    @Test
    public void testreadMineField_FeldMitEinerMineVorhanden_SollteNegativenWertFuerMineInZelleSetzen()
        throws Exception {
        // Vorbereitung
        final Path file = writeToFile(Character.toString(MiningFileService.FILE_MINE));

        // Ausführung
        final int[][] field = classUnderTest.readMineField(file.toString());

        // Prüfung
        assertThat(field, is(equalTo(new int[][] {
                        { MiningFileService.FIELD_MINE }
                    })));
    }

    @Test
    public void testreadMineField_FeldOhneMineVorhanden_SollteNullWertFuerMineInZelleSetzen()
        throws Exception {
        // Vorbereitung
        final Path file = writeToFile(Character.toString(MiningFileService.FILE_NO_MINE));

        // Ausführung
        final int[][] field = classUnderTest.readMineField(file.toString());

        // Prüfung
        assertThat(field, is(equalTo(new int[][] {
                        { MiningFileService.FIELD_NO_MINE }
                    })));
    }

    private Path writeToFile(final String output) throws IOException {
        final Path file = temp.newFile().toPath();
        Files.write(file, output.getBytes(), StandardOpenOption.WRITE);

        return file;
    }

    @Test
    public void testwriteMineField_MinenfeldMitMieneGegeben_SollteMieneInDateiGespeichertHaben()
        throws Exception {
        // Vorbereitung
        final File outputFile = temp.newFile();
        final int[][] processedField = new int[][] {
                { -1 }
            };

        // Ausführung
        classUnderTest.writeMineField(processedField, outputFile.toString());

        // Prüfung
        final String fileContent = new String(Files.readAllBytes(outputFile.toPath()));
        assertThat(fileContent,
            is(either(equalTo(stringOf(MiningFileService.FILE_MINE))).or(
                    equalTo(
                        stringOf(MiningFileService.FILE_MINE) +
                        System.getProperty("line.separator")))));
    }

    @Test
    public void testwriteMineField_MienenfeldMitEntfernungszahlGegeben_SollteEntfernungszahlInDateiGespeichertHaben()
        throws Exception {
        // Vorbereitung
        final File outputFile = temp.newFile();
        final int entfernungszahl = 8;
        final int[][] processedField = new int[][] {
                { entfernungszahl }
            };

        // Ausführung
        classUnderTest.writeMineField(processedField, outputFile.toString());

        // Prüfung
        final String fileContent = new String(Files.readAllBytes(outputFile.toPath()));
        assertThat(fileContent,
            is(either(equalTo(stringOf(entfernungszahl))).or(
                    equalTo(stringOf(entfernungszahl) + System.getProperty("line.separator")))));
    }

    private String stringOf(final int number) {
        return Integer.toString(number);
    }

    private String stringOf(final char character) {
        return Character.toString(character);
    }
}
