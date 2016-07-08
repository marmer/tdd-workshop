package de.scag.tryouts.tdd.katas.minesweeper;

import org.springframework.stereotype.Service;


@Service
public class FieldProcessor {
    public int[][] process(final int[][] unprocessedField) {
        final int[][] processedField = new int[unprocessedField.length][];

        for (int y = 0; y < unprocessedField.length; y++) {
            processedField[y] = new int[unprocessedField[y].length];

            for (int x = 0; x < unprocessedField[y].length; x++) {
                if (unprocessedField[y][x] == MiningFileService.FIELD_MINE) {
                    processedField[y][x] = MiningFileService.FIELD_MINE;

                    continue;
                }

                if ((unprocessedField[y][x + 1] == MiningFileService.FIELD_MINE) &&
                        ((x + 1) <= unprocessedField.length)) {
                    processedField[y][x] = 1;
                }
            }
        }

        return processedField;
    }
}
