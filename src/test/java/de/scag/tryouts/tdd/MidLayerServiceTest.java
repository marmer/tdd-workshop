package de.scag.tryouts.tdd;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import static org.mockito.Matchers.anyInt;

import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MidLayerServiceTest {
    private static final int DURCH_VIER_TEILBARE_ZAHL = 4;

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
        when(bottomLayerServiceMock.anotherComplicatedLogic(anyInt())).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 1);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest1_ValueShouldBeGivenToNextService()
        throws Exception {
        // Vorbereitung
        final int paramValue = DURCH_VIER_TEILBARE_ZAHL + 1;

        // Ausführung
        classUnderTest.isComplicatedFoo(paramValue);

        // Prüfung
        verify(bottomLayerServiceMock).anotherComplicatedLogic(paramValue);
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest2_ShouldReturnOppositOfTheNextService()
        throws Exception {
        // Vorbereitung
        when(bottomLayerServiceMock.anotherComplicatedLogic(anyInt())).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 2);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testIsComplicatedFoo_ValueDividedByFourIsRest2_ShouldCallServiceWithTheGivenValue()
        throws Exception {
        // Vorbereitung
        final int paramValue = DURCH_VIER_TEILBARE_ZAHL + 2;

        // Ausführung
        classUnderTest.isComplicatedFoo(paramValue);

        // Prüfung
        verify(bottomLayerServiceMock).anotherComplicatedLogic(paramValue);
    }
}
