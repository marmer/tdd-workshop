package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MinesweeperTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TemporaryFolder f = new TemporaryFolder();

    @InjectMocks
    private Minesweeper classUnderTest;

    @Test
    public void testrun_KeineEingabedateiGegeben_ExceptionMitPassenderNachrichtGegeben()
        throws Exception {
        // Prüfung
        exception.expect(MinesweeperException.class);
        exception.expectMessage(is(equalTo("Keine Eingabedatei angegeben.")));

        // Ausführung
        classUnderTest.run();
    }
}
