package io.github.marmer.morse;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.fs.InputFileReader;

public class MorseApp {

    private final CLIProcessor cliProcessor;
    private final MorseTranslatorFactory morseTranslatorFactory;
    private final InputFileReader inputFileReaderMock;

    public MorseApp(CLIProcessor cliProcessor,
        MorseTranslatorFactory morseTranslatorFactory,
        InputFileReader inputFileReaderMock) {
        this.cliProcessor = cliProcessor;
        this.morseTranslatorFactory = morseTranslatorFactory;
        this.inputFileReaderMock = inputFileReaderMock;
    }

    public static void main(String... args) {
        new MorseAppFactory().create().run(args);
    }

    public void run(String... args) {
        cliProcessor.getInFile(args).map(inputFileReaderMock::read)
            .or(() -> cliProcessor.getDefaultInput(args))
            .ifPresent(defaultInput ->
                cliProcessor.getOutFile(args).ifPresentOrElse(outFile ->
                        morseTranslatorFactory.createWithOutFile(outFile).translate(defaultInput),
                    () -> morseTranslatorFactory.create().translate(defaultInput))
            );
    }


}
