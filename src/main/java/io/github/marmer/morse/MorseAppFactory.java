package io.github.marmer.morse;

import io.github.marmer.morse.adapter.cli.CLIProcessor;
import io.github.marmer.morse.adapter.fs.InputFileReader;

public class MorseAppFactory {

    public MorseApp create() {
        return new MorseApp(
            new CLIProcessor(),
            new MorseTranslatorFactory(new PrinterFactory()),
            new InputFileReader());
    }
}
