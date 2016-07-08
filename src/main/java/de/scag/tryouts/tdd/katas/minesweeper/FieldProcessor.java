package de.scag.tryouts.tdd.katas.minesweeper;

import org.springframework.stereotype.Service;


@Service
public class FieldProcessor {
    public int[][] process(final int[][] unprocessedField) {
        final int[][] processedField = new int[unprocessedField.length][];

        for (int line = 0; line < unprocessedField.length; line++) {
            process(unprocessedField, processedField, line);
        }

        return processedField;
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
                if ((isMine(unprocessedField[line][col + 1]))) {
                    processedField[line][col]++;
                }

                if (!isTopBorder(line) && isMine(unprocessedField[line - 1][col + 1])) {
                    processedField[line][col]++;
                }
            }

            if (!leftBoarder(col)) {
                if ((isMine(unprocessedField[line][col - 1]))) {
                    processedField[line][col]++;
                }

                if (!isTopBorder(line) && isMine(unprocessedField[line - 1][col - 1])) {
                    processedField[line][col]++;
                }
            }

            if (!isBottomBoarder(unprocessedField, line)) {
                if ((isMine(unprocessedField[line + 1][col]))) {
                    processedField[line][col]++;
                }

                if (!leftBoarder(col) && isMine(unprocessedField[line + 1][col - 1])) {
                    processedField[line][col]++;
                }

                if (!isRightBoarder(unprocessedField, line, col) &&
                        isMine(unprocessedField[line + 1][col + 1])) {
                    processedField[line][col]++;
                }
            }

            if (!isTopBorder(line)) {
                if ((isMine(unprocessedField[line - 1][col]))) {
                    processedField[line][col]++;
                }
            }
        }
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
