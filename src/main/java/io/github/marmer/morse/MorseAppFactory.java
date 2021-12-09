package io.github.marmer.morse;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.fs.InputFileReader;

public class MorseAppFactory {

    public MorseApp create() {
        final CLIProcessor cliProcessor = new CLIProcessor();
        return new MorseApp(
            cliProcessor,
            new MorseTranslatorFactory(new PrinterFactory(cliProcessor)),
            new InputFileReader());
    }
}
