package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MinesweeperTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private Minesweeper classUnderTest;

    @Mock
    private MiningFileService iOMiningServiceMock;

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
        // Vorbereitung
        final String inputPath = "inputPath";
        final String outputPath = "outputPath";
        final int[][] inputFildinputFild = new int[0][0];

        // Ausführung
        classUnderTest.run(inputPath, outputPath);

        // Prüfung
        verify(iOMiningServiceMock).readMineField(inputPath);
    }
    // TODO lesen
    // TODO umwandeln
    // TODO rausschreiben
}
