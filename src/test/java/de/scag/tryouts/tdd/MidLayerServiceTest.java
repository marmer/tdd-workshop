package de.scag.tryouts.tdd;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


public class MidLayerServiceTest {
    private static final int DURCH_VIER_TEILBARE_ZAHL = 4;
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private MidLayerService classUnderTest;

    @Mock
    private BottomLayerService bottomLayerServiceMock;

    @Test
    public void testIsComplicatedFoo_GivenValueIsDividableByFour_ShuoldReturnTrue() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest3_ShouldReturnFalse() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 3);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest1_ShouldReturnResultOfNextService()
        throws Exception {
        // Vorbereitung
        when(bottomLayerServiceMock.anotherComplicatedLogic(DURCH_VIER_TEILBARE_ZAHL + 1)).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 1);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest2_ShouldReturnOppositOfTheNextService()
        throws Exception {
        // Vorbereitung
        when(bottomLayerServiceMock.anotherComplicatedLogic(DURCH_VIER_TEILBARE_ZAHL + 2)).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 2);

        // Prüfung
        assertThat(result, is(true));
    }
}
