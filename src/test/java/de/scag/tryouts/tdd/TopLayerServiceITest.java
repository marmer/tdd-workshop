package de.scag.tryouts.tdd;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class TopLayerServiceITest {
    private TopLayerService classUnderTest;

    @Before
    public void setUp() throws Exception {
        // Darum kümmert sich üblicherweise Spring und co.
        classUnderTest = new TopLayerService(new MidLayerService(new BottomLayerService()));
    }

    /**
     * Der Methodenname würde bei einem Integrationstest üblicherweise einer
     * allgemeineren fachlichen Schreibweise entsprechen statt einer so
     * technischen wie hier beschrieben.
     */
    @Test
    public void testGetConverted_WertUnterNullDerModuloVierRestEinsUndRestlosDurchDreiTeilbarIst_SollteTrueZurueckgeben()
        throws Exception {
        // Ausführung
        final boolean converted = classUnderTest.getConverted(-13);

        // Prüfung
        assertThat(converted, is(true));
    }
}
