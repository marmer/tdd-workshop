package de.scag.tryouts.tdd.katas.minesweeper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Minesweeper {
    private final MiningFileService miningFileService;
    private final FieldProcessor fieldProcessor;

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
