package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FieldProcessorTest {
    @InjectMocks
    private FieldProcessor classUnderTest;

    @Test
    public void testprocess_FeldMitMieneGegeben_FeldMitMieneSollteUnveraendertBleiben()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { MiningFileService.FIELD_MINE }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[0][0], is(MiningFileService.FIELD_MINE));
    }

    @Test
    public void testprocess_FeldMitLeerplatzUndMieneRechtsDavonGegeben_LeerplatzSollteBenachbarteMieneAnzeigen()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { 0, MiningFileService.FIELD_MINE }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[0][0], is(1));
    }

    @Test
    public void testprocess_FeldMitLeerplatzUndMieneLinksDavonGegeben_LeerplatzSollteBenachbarteMieneAnzeigen()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { MiningFileService.FIELD_MINE, 0 }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[0][1], is(1));
    }

    @Test
    public void testprocess_FeldMitLeerplatzUndMieneDarueberGegeben_LeerplatzSollteBenachbarteMieneAnzeigen()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { MiningFileService.FIELD_MINE },
                { 0 }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[1][0], is(equalTo(1)));
    }

    @Test
    public void testprocess_FeldMitLeerplatzUndMieneDarunterGegeben_LeerplatzSollteBenachbarteMieneAnzeigen()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { 0 },
                { MiningFileService.FIELD_MINE }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[0][0], is(1));
    }

    @Test
    public void testprocess_FeldMitLeerplatzUndLinksDarueberGegeben_LeerplatzSollteBenachbarteMieneAnzeigen()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { MiningFileService.FIELD_MINE, 0 },
                { 0, 0 }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[1][1], is(1));
    }

    @Test
    public void testprocess_LeerfeldOhneMienenNachbarnGegeben_LeerplatzSollteAnzahlKeineBenachbartenMienenEnthalten()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[1][1], is(0));
    }

    @Test
    @Ignore
    public void testprocess_LeerfeldKomplettMitMienenUmgeben_LeerplatzSollteAnzahlAllerBenachbartenMienenEnthalten()
        throws Exception {
        // Vorbereitung
        final int[][] field = {
                {
                    MiningFileService.FIELD_MINE, MiningFileService.FIELD_MINE,
                    MiningFileService.FIELD_MINE
                },
                { MiningFileService.FIELD_MINE, 0, MiningFileService.FIELD_MINE },
                {
                    MiningFileService.FIELD_MINE, MiningFileService.FIELD_MINE,
                    MiningFileService.FIELD_MINE
                }
            };

        // Ausführung
        final int[][] processed = classUnderTest.process(field);

        // Prüfung
        assertThat(processed[1][1], is(8));
    }
}
