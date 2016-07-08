package de.scag.tryouts.tdd.katas.minesweeper;

import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


/**
 * Spezifikation grob: Ein aus einer Datei gelesenes Mienenfeld soll an allen
 * minenfreien Stellen mit Informationen (Zahl) versehen werden, wie viele Minen
 * die Benachbarten Felder haben und anschließend in einer Datei anderen Datei
 * gespeichert werden.
 *
 * <p>Weitere Details:</p>
 *
 * <ol>
 *   <li>Ein Feld mit einer Miene wird in der Quell und der Zieldatei mit
 *     folgendem Symbol dargestellt: *</li>
 *   <li>Ein leeres Feld wird in der Eingabedatei durch einen Punkt
 *     dargestellt</li>
 *   <li>Ein leeres Feld in der Ausgabedatei wird mit einer Ziffer dargestellt
 *   </li>
 *   <li>Die Ziffer eines leeren Felds in der Ausgabedatei gibt an, wieviele
 *     Mienen in den umliegenden Feldern vorhanden sind</li>
 *   <li>Als umliegendes Feld gelten ebenso horizontale wie vertikale Nachbarn
 *   </li>
 *   <li>Ein Mienenfeld ist stets quadratisch.</li>
 *   <li>Alle Zeilen eines Mienenfelds sind gleich lang</li>
 *   <li>Alle Spalten eines Mienenfelds sind gleich lang</li>
 *   <li>Die Größe eines Minenfeldes ist beliebig</li>
 *   <li>Bei Problemen mit den Eingabedaten soll das Programm mit einer
 *     angemessenen Fehlerbeschreibung enden.</li>
 *   <li>XXX Der erste Parameter der Main Methode beschreibt die
 *     Eingabedatei</li>
 *   <li>XXX Der zweite Parameter der Main Methode beschreibt die
 *     Ausgabedatei</li>
 *   <li>XXX Beide Parameter sind pflicht</li>
 * </ol>
 *
 * @author mertinat
 * @since  07.07.2016
 */
@Service
public class Minesweeper {
    @Autowired
    private MiningFileService miningFileService;

    @Autowired
    private FieldProcessor fieldProcessor;

    public void run(final String... args) throws MinesweeperException {
        final String inputFile = getInputFile(args);
        final String outpufFile = getOutputFile(args);

        final int[][] mineField = read(inputFile);
        final int[][] processedField = process(mineField);

        write(outpufFile, processedField);
    }

    private void write(final String outpufFile, final int[][] processedField) {
        miningFileService.writeMineField(processedField, outpufFile);
    }

    private int[][] process(final int[][] mineField) {
        return fieldProcessor.process(mineField);
    }

    private int[][] read(final String inputFile) throws MinesweeperException {
        return miningFileService.readMineField(inputFile);
    }

    private String getOutputFile(final String... args) throws MinesweeperException {
        if (ArrayUtils.getLength(args) < 2) {
            throw new MinesweeperException("Keine Ausgabedatei angegeben.");
        }

        return args[1];
    }

    private String getInputFile(final String... args) throws MinesweeperException {
        if (ArrayUtils.getLength(args) < 1) {
            throw new MinesweeperException("Keine Eingabedatei angegeben.");
        }

        return args[0];
    }
}
