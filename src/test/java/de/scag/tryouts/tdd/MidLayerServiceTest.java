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
    public void testIsComplicatedFoo_DurchVierTeilbareZahlGegeben_TrueErwartet() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testIsComplicatedFoo_WertDurchVierIstRest3_FalseErwartet() throws Exception {
        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 3);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testIsComplicatedFoo_WertDurchVierIstRest1_ServiceErgebnisErwartet()
        throws Exception {
        // Vorbereitung
        when(bottomLayerServiceMock.anotherComplicatedLogic(anyInt())).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 1);

        // Prüfung
        assertThat(result, is(false));
    }

    @Test
    public void testIsComplicatedFoo_WertDurchVierIstRest1_ParameterWirdAnServiceWeitergereicht()
        throws Exception {
        // Vorbereitung
        final int parameterwert = DURCH_VIER_TEILBARE_ZAHL + 1;

        // Ausführung
        classUnderTest.isComplicatedFoo(parameterwert);

        // Prüfung
        verify(bottomLayerServiceMock).anotherComplicatedLogic(parameterwert);
    }

    @Test
    public void testIsComplicatedFoo_WertDurchVierIstRest2_GegenteilDesServiceErgebnisErwartet()
        throws Exception {
        // Vorbereitung
        when(bottomLayerServiceMock.anotherComplicatedLogic(anyInt())).thenReturn(true);

        // Ausführung
        final boolean result = classUnderTest.isComplicatedFoo(DURCH_VIER_TEILBARE_ZAHL + 2);

        // Prüfung
        assertThat(result, is(true));
    }

    @Test
    public void testIsComplicatedFoo_WertDurchVierIstRest2_ParameterWirdAnServiceWeitergereicht()
        throws Exception {
        // Vorbereitung
        final int parameterwert = DURCH_VIER_TEILBARE_ZAHL + 2;

        // Ausführung
        classUnderTest.isComplicatedFoo(parameterwert);

        // Prüfung
        verify(bottomLayerServiceMock).anotherComplicatedLogic(parameterwert);
    }
}
