package de.scag.tryouts.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class TopLayerServiceITest {
    private TopLayerService classUnderTest;

    @Before
    public void setUp() throws Exception {
        // This setup may be done by any DI Framework
        classUnderTest = new TopLayerService(new MidLayerService(new BottomLayerService()));
    }

    /**
     * Typically the name of an integrationtest should be pretty general, about a business case or business requirement
     * and avoid technical details. So it's easier to get the intention of the origin developer. In addition it helps
     * the origin developer itself to think about what's important now.
     */
    @Test
    public void testGetConverted_ValueBelowZeroWhichIsModulo4RestOneAndModulo3Rest0_ShouldReturnTrue()
        throws Exception {
        // Ausführung
        final boolean converted = classUnderTest.getConverted(-13);

        // Prüfung
        assertThat(converted, is(true));
    }
}
