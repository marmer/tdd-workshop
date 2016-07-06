package de.scag.tryouts.tdd;

import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import static org.mockito.Matchers.anyInt;

import org.mockito.Mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TopLayerServiceTest {
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
        final boolean serviceResult = false;
        when(methodServiceMock.isComplicatedFoo(negativeValue)).thenReturn(serviceResult);

        // Ausführung
        final boolean converted = classUnderTest.getConverted(negativeValue);

        // Prüfung
        assertThat(converted, is(not(serviceResult)));
    }
}
