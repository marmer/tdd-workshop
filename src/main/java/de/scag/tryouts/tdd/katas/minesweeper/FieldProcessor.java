package de.scag.tryouts.tdd.katas.minesweeper;

import org.apache.commons.lang3.ArrayUtils;

import org.springframework.stereotype.Service;


@Service
public class FieldProcessor {
    public int[][] process(final int[][] unprocessedField) throws MinesweeperException {
        checkWohlgeformt(unprocessedField);

        final int[][] processedField = new int[unprocessedField.length][];

        for (int line = 0; line < unprocessedField.length; line++) {
            process(unprocessedField, processedField, line);
        }

        return processedField;
    }

    private void checkWohlgeformt(final int[][] unprocessedField) throws MinesweeperException {
        if (ArrayUtils.isEmpty(unprocessedField)) {
            throw new MinesweeperException("Feld sollte mindestens Platz für ein Element bieten");
        }

        Integer length = null;

        for (int i = 0; i < unprocessedField.length; i++) {
            if (length == null) {
                length = unprocessedField[i].length;
            } else if (ArrayUtils.getLength(unprocessedField[i]) != length) {
                throw new MinesweeperException("Das Mienenfeld hat unterschiedlich lange Zeilen.");
            }
        }
    }

    private void process(final int[][] unprocessedField,
        final int[][] processedField,
        final int line) {
        processedField[line] = new int[unprocessedField[line].length];

        for (int col = 0; col < unprocessedField[line].length; col++) {
            if (isMine(unprocessedField[line][col])) {
                processedField[line][col] = MiningFileService.FIELD_MINE;

                continue;
            } else {
                processedField[line][col] = 0;
            }

            if (!isRightBoarder(unprocessedField, line, col)) {
                if (hasRightMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }

                if (!isTopBorder(line) && hasTopRightMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }
            }

            if (!leftBoarder(col)) {
                if (hasLeftMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }

                if (!isTopBorder(line) && hasTopLeftMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }
            }

            if (!isBottomBoarder(unprocessedField, line)) {
                if (hasBottomMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }

                if (!leftBoarder(col) && hasBottomLeftMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }

                if (!isRightBoarder(unprocessedField, line, col) &&
                        hasBottomRightMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }
            }

            if (!isTopBorder(line)) {
                if (hasTopMine(unprocessedField, line, col)) {
                    processedField[line][col]++;
                }
            }
        }
    }

    private boolean hasTopMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line - 1][col]);
    }

    private boolean hasBottomRightMine(final int[][] unprocessedField,
        final int line,
        final int col) {
        return isMine(unprocessedField[line + 1][col + 1]);
    }

    private boolean hasBottomLeftMine(final int[][] unprocessedField,
        final int line,
        final int col) {
        return isMine(unprocessedField[line + 1][col - 1]);
    }

    private boolean hasBottomMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line + 1][col]);
    }

    private boolean hasTopLeftMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line - 1][col - 1]);
    }

    private boolean hasLeftMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line][col - 1]);
    }

    private boolean hasTopRightMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line - 1][col + 1]);
    }

    private boolean hasRightMine(final int[][] unprocessedField, final int line, final int col) {
        return isMine(unprocessedField[line][col + 1]);
    }

    private boolean isRightBoarder(final int[][] unprocessedField,
        final int lineNr,
        final int colNr) {
        return (colNr + 1) >= unprocessedField[lineNr].length;
    }

    private boolean isBottomBoarder(final int[][] unprocessedField, final int lineNr) {
        return (lineNr + 1) >= unprocessedField.length;
    }

    private boolean isTopBorder(final int lineNr) {
        return (lineNr - 1) < 0;
    }

    private boolean leftBoarder(final int colNr) {
        return (colNr - 1) < 0;
    }

    private boolean isMine(final int potentialMine) {
        return potentialMine == MiningFileService.FIELD_MINE;
    }
}
