package io.github.marmer.morsecoding.application_and_infrastructure;

import io.github.marmer.morsecoding.domain.MorseTranslator;

class MorseApplication {

    private final OutputConsumer outputConsumer;
    private final Arguments arguments;
    private MorseTranslator morseTranslator;

    public MorseApplication(Arguments arguments, OutputConsumer outputConsumer) {
        this.outputConsumer = outputConsumer;
        this.arguments = arguments;
        this.morseTranslator = new MorseTranslator();
    }

    public static void main(String... args) {
        //allowed to be a reaaaaly disgusting place ... dirty ... ugly ... untested ...
        new MorseApplication(new Arguments(args), System.out::println).run();
    }

    public void run() {
        String input = arguments.getInput().value();

        outputConsumer.consume(
                morseTranslator.translate(input)
        );
    }


}
