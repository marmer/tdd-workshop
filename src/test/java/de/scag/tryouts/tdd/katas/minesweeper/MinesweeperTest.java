package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MinesweeperTest {
    private static final int[][] UNPROCESSED_FIELD = new int[0][0];
    private static final int[][] PROCESSED_FIELD = new int[0][0];

    private static final String OUTPUT_PATH = "outputPath";

    private static final String INPUT_PATH = "inputPath";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private Minesweeper classUnderTest;

    @Mock
    private MiningFileService iOMiningService;

    @Mock
    private FieldProcessor minefieldProcessor;

    @Before
    public void setUp() throws Exception {
        initIOMiningService();
        initMinefieldProcessor();
    }

    private void initMinefieldProcessor() {
        when(minefieldProcessor.process(UNPROCESSED_FIELD)).thenReturn(PROCESSED_FIELD);
    }

    private void initIOMiningService() throws Exception {
        when(iOMiningService.readMineField(INPUT_PATH)).thenReturn(UNPROCESSED_FIELD);
    }

    @Test
    public void testrun_KeineEingabedateiGegeben_SollteExceptionMitPassenderNachrichtWerfen()
        throws Exception {
        // Prüfung
        exception.expect(MinesweeperException.class);
        exception.expectMessage(is(equalTo("Keine Eingabedatei angegeben.")));

        // Ausführung
        classUnderTest.run();
    }

    @Test
    public void testrun_KeineAusgabedateiGegeben_SollgeExceptionMitPassenderNachrichtWerfen()
        throws Exception {
        // Prüfung
        exception.expect(MinesweeperException.class);
        exception.expectMessage(is(equalTo("Keine Ausgabedatei angegeben.")));

        // Ausführung
        classUnderTest.run("Pfad zur Eingabedatei");
    }

    @Test
    public void testrun_EinUndAusgabedateiGegeben_EingabedateiWirdEingelesen() throws Exception {
        // Ausführung
        classUnderTest.run(INPUT_PATH, OUTPUT_PATH);

        // Prüfung
        verify(iOMiningService).readMineField(INPUT_PATH);
    }

    @Test
    public void testrun_MinenfeldEingelesen_MinenfeldWirdVerarbeitet() throws Exception {
        // Vorbereitung
        when(iOMiningService.readMineField(INPUT_PATH)).thenReturn(UNPROCESSED_FIELD);

        // Ausführung
        classUnderTest.run(INPUT_PATH, OUTPUT_PATH);

        // Prüfung
        verify(minefieldProcessor).process(UNPROCESSED_FIELD);
    }

    @Test
    public void testrun_MinenfeldVerarbeitet_MinenfeldWirdHerausgeschrieben() throws Exception {
        // Vorbereitung
        when(minefieldProcessor.process(UNPROCESSED_FIELD)).thenReturn(PROCESSED_FIELD);

        // Ausführung
        classUnderTest.run(INPUT_PATH, OUTPUT_PATH);

        // Prüfung
        verify(iOMiningService).writeMineField(PROCESSED_FIELD, OUTPUT_PATH);
    }
}
