package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.After;

import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;

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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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
}
