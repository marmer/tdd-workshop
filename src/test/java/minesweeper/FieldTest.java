package minesweeper;

import de.scag.tryouts.tdd.katas.minesweeper.Field;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;


public class FieldTest {
    private Field classUnderTest;

    @Test
    public void toString_KomplexeFeldInitialisierung_ExpectedFieldRepresentation()
        throws Exception {
        // Vorbereitung
        final String BASE_SETUP =
            "*.....\n" + ".**...\n" + "..*...\n" + "..*...\n" + "....*.\n" + "......\n";
        this.classUnderTest = new Field(BASE_SETUP);

        // Ausführung
        final String retVal = classUnderTest.toString();
        System.out.println(retVal);

        // Prüfung
        final String BASE_SETUP_RESULT =
            "*32100\n" + "2**200\n" + "14*300\n" + "02*311\n" + "0112*1\n" + "000111\n";
        assertThat(retVal, is(equalTo(BASE_SETUP_RESULT)));
    }
}
