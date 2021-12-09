package io.github.marmer.morse;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.fs.InputFileReader;
import io.github.marmer.morse.usecases.MorseTranslator;

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
        final MorseTranslator morseTranslator = morseTranslatorFactory.create(args);

        cliProcessor.getInFile(args)
            .map(inputFileReaderMock::read)
            .or(() -> cliProcessor.getDefaultInput(args))
            .ifPresent(morseTranslator::translate);
    }


}
