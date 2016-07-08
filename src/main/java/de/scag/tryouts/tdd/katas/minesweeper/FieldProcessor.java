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
            }

            if (((col + 1) <= unprocessedField.length) &&
                    (isMine(unprocessedField[line][col + 1]))) {
                processedField[line][col] = 1;
            }

            if (((col - 1) >= 0) && (isMine(unprocessedField[line][col - 1]))) {
                processedField[line][col] = 1;
            }
        }
    }

    private boolean isMine(final int potentialMine) {
        return potentialMine == MiningFileService.FIELD_MINE;
    }
}
