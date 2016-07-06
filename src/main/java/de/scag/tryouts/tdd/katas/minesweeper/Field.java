package de.scag.tryouts.tdd.katas.minesweeper;

import java.io.BufferedReader;
import java.io.StringReader;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Minesweeper Feld Repräsentation.
 *
 * @author mertinat
 * @since  06.07.2016
 */
public class Field {
    private static final char MINE_CHAR = '*';

    private static final String NEWLINE_CHAR = "\n";

    private static final String EMPTY_CHAR = "";

    /** Character für Treffer. */
    private char[][] setup;

    public Field(final String setup) {
        this.setup = convertWholeField(setup);
        populateNeighbours();
    }

    private void populateNeighbours() {
        for (int line = 0; line < setup.length; line++) {
            for (int column = 0; column < setup[line].length; column++) {
                setValueFor(line, column);
            }
        }
    }

    private void setValueFor(final int line, final int column) {
        if (MINE_CHAR == setup[line][column]) {
            setup[line][column] = '*';
        } else {
            setup[line][column] = toChar(valueSumFor(line, column));
        }
    }

    private int valueSumFor(final int line, final int column) {
        int value = 0;

        value += valueFor(line + 1, column + 1);
        value += valueFor(line + 1, column - 1);
        value += valueFor(line + 1, column);
        value += valueFor(line - 1, column + 1);
        value += valueFor(line - 1, column - 1);
        value += valueFor(line - 1, column);
        value += valueFor(line, column + 1);
        value += valueFor(line, column - 1);

        return value;
    }

    private int valueFor(final int line, final int column) {
        return hasValue(line, column) ? 1 : 0;
    }

    private boolean hasValue(final int line, final int column) {
        return isValid(line, column) && (setup[line][column] == MINE_CHAR);
    }

    private boolean isValid(final int line, final int column) {
        return (0 <= line) && (line < setup.length) && (0 <= column) &&
            (column < setup[line].length);
    }

    private char toChar(final int value) {
        final String string = Integer.toString(value);

        return string.charAt(0);
    }

    private char[][] convertWholeField(final String setup) {
        return new BufferedReader(new StringReader(setup)).lines().map(String::toCharArray).toArray(
                char[][]::new);
    }

    @Override
    public String toString() {
        return Arrays.stream(setup).map(String::new).collect(
                Collectors.reducing(EMPTY_CHAR, (s1, s2) -> s1 + s2 + NEWLINE_CHAR));
    }
}
