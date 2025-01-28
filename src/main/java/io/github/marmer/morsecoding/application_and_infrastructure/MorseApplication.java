package io.github.marmer.morsecoding.application_and_infrastructure;

import io.github.marmer.morsecoding.domain.MorseTranslator;

class MorseApplication {

    private final OutputConsumer outputConsumer;
    private final CliArguments cliArguments;
    private MorseTranslator morseTranslator;

    public MorseApplication(CliArguments cliArguments, OutputConsumer outputConsumer) {
        this.outputConsumer = outputConsumer;
        this.cliArguments = cliArguments;
        this.morseTranslator = new MorseTranslator();
    }

    public static void main(String... args) {
        //allowed to be a reaaaaly disgusting place ... dirty ... ugly ... untested ...
        new MorseApplication(new CliArguments(args), System.out::println).run();
    }

    public void run() {
        cliArguments.inputFile().ifPresentOrElse(
                this::translateFromFile,
                this::translateFromString
        );
        
        outputConsumer.consume(
                morseTranslator.translate(cliArguments.inputString())
        );
    }


}
