package de.scag.tryouts.tdd;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BottomServiceTest {
    @InjectMocks
    private BottomLayerService classUnderTest;

    final int DURCH_DREI_TEILBAR = 3;

    @Test
    public void testAnotherComplicatedLogic_WertIstGlattDurchDreiTeilbar_ErwarteFalse()
        throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DURCH_DREI_TEILBAR);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testAnotherComplicatedLogic_WertDurchDreiIstRest1_ErwarteTrue() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DURCH_DREI_TEILBAR + 1);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testAnotherComplicatedLogic_WertDurchDreiIstRest2_ErwarteFalse() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DURCH_DREI_TEILBAR + 2);

        // Prüfung
        assertThat(result, is(false));
    }
}
