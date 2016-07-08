package de.scag.tryouts.tdd.katas.minesweeper;

import org.springframework.stereotype.Service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.LinkedList;
import java.util.List;


@Service
public class MiningFileService {
    static final int FIELD_MINE = -1;
    static final int FIELD_NO_MINE = 0;

    static final char FILE_MINE = '*';
    static final char FILE_NO_MINE = '.';

    public int[][] readMineField(final String inpufPath) throws MinesweeperException {
        try {
            final List<String> lines = Files.readAllLines(toPath(inpufPath));

            return lines.stream().map(this::toFieldLine).toArray(size -> new int[size][]);
        } catch (IOException e) {
            // Simulation mit "defekter" Datei für Tests nötig oder zusätzliche Schicht, die das
            // Lesen kapselt
            throw new MinesweeperException("Fehler beim Einlesen der Datei: " + inpufPath);
        }
    }

    private Path toPath(final String inpufPath) throws MinesweeperException {
        final Path inputFile = Paths.get(inpufPath);

        if (!Files.exists(inputFile)) {
            throw new MinesweeperException("Datei nicht vorhanden: " + inpufPath);
        }

        return inputFile;
    }

    public void writeMineField(final int[][] processedField, final String outputPath) {
        final List<String> outputLines = new LinkedList<>();

        for (final int[] fieldLine : processedField) {
            outputLines.add(toFileLine(fieldLine));
        }

        try {
            Files.write(Paths.get(outputPath),
                outputLines,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Simulation mit "defekter" Datei für Tests nötig oder zusätzliche Schicht, die das
            // Lesen kapselt
            new MinesweeperException(
                "Fehler beim Schreiben des Ergebnisses in Datei: " + outputPath,
                e);
        }
    }

    private String toFileLine(final int[] fieldLine) {
        final StringBuffer lineBuffer = new StringBuffer();

        for (int i = 0; i < fieldLine.length; i++) {
            if (fieldLine[i] == FIELD_MINE) {
                lineBuffer.append(FILE_MINE);
            } else {
                lineBuffer.append(fieldLine[i]);
            }
        }

        return lineBuffer.toString();
    }

    private int[] toFieldLine(final String fileLine) {
        final char[] characters = fileLine.toCharArray();
        final int[] fieldLine = new int[characters.length];

        for (int i = 0; i < characters.length; i++) {
            fieldLine[i] = toFieldValue(characters[i]);
        }

        return fieldLine;
    }

    private int toFieldValue(final char fileValue) {
        switch (fileValue) {
        case FILE_MINE:
            return FIELD_MINE;

        default:
            return FIELD_NO_MINE;
        }
    }
}
