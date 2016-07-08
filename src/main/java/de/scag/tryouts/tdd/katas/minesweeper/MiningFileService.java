package de.scag.tryouts.tdd.katas.minesweeper;

import org.springframework.stereotype.Service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Implementierung fehlt");
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
