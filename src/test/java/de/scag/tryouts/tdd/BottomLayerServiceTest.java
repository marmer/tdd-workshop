package de.scag.tryouts.tdd;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BottomLayerServiceTest {
    final int DIVIDABLE_BY_THREE = 3;
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private BottomLayerService classUnderTest;

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
