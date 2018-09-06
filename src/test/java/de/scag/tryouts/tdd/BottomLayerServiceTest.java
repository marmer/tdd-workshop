package de.scag.tryouts.tdd;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BottomLayerServiceTest {
    @InjectMocks
    private BottomLayerService classUnderTest;

    final int DIVIDABLE_BY_THREE = 3;

    @Test
    public void testAnotherComplicatedLogic_ValueIsDividableByThree_ShouldReturnFalse()
        throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DIVIDABLE_BY_THREE);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testAnotherComplicatedLogic_ValueDividedByThreeIsRest1_ShouldReturnTrue() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DIVIDABLE_BY_THREE + 1);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testAnotherComplicatedLogic_ValueDividedByThreeIs2_ShouldReturnFalse() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.anotherComplicatedLogic(DIVIDABLE_BY_THREE + 2);

        // Prüfung
        assertThat(result, is(false));
    }
}
