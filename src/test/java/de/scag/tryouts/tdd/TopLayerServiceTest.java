package de.scag.tryouts.tdd;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class TopLayerServiceTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private TopLayerService classUnderTest;

    @Mock
    private MidLayerService methodServiceMock;

    @Test
    public void testgetConverted_Wert0Gegeben_SollteFalseZurueckgeben() throws Exception {
        // Ausführung
        final boolean converted = classUnderTest.getConverted(0);

        // Prüfung
        assertFalse(converted);
    }

    @Test
    public void testgetConverted_Wert0Gegeben_SollteServiceNichtAufrufen() throws Exception {
        // Ausführung
        classUnderTest.getConverted(0);

        // Prüfung
        verify(methodServiceMock, never()).isComplicatedFoo(anyInt());
    }

    @Test
    public void testgetConverted_PositiverWertGegeben_SollteRueckgabeDesServiceEntsprechen()
        throws Exception {
        // Vorbereitung
        final int positiveValue = 1;
        final boolean serviceResult = true;
        when(methodServiceMock.isComplicatedFoo(positiveValue)).thenReturn(serviceResult);

        // Ausführung
        final boolean converted = classUnderTest.getConverted(positiveValue);

        // Prüfung
        assertThat(converted, is(serviceResult));
    }

    @Test
    public void testgetConverted_NegativerWertGegebenMitNegativerServiceRueckgabe_SollteGegenteiligenWertDesServiceZurueckgeben()
        throws Exception {
        // Vorbereitung
        final int negativeValue = -1;
        final boolean serviceResult = true;
        when(methodServiceMock.isComplicatedFoo(Math.abs(negativeValue))).thenReturn(serviceResult);

        // Ausführung
        final boolean converted = classUnderTest.getConverted(negativeValue);

        // Prüfung
        assertThat(converted, is(not(serviceResult)));
    }

    @Test
    public void testgetConverted_NegativerWertGegebenMitPositiverServiceRueckgabe_SollteGegenteiligenWertDesServiceZurueckgeben()
        throws Exception {
        // Vorbereitung
        final int negativeValue = -1;

        // Ausführung
        final boolean converted = classUnderTest.getConverted(negativeValue);

        // Prüfung
        assertThat(converted, is(not(false)));
    }
}
