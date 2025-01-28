package io.github.marmer.morsecoding.application_and_infrastructure;

import io.github.marmer.morsecoding.domain.Message;

import java.nio.file.Path;
import java.util.Optional;

class CliArguments {
    private final Message message;

    public CliArguments(String[] arguments) {
        this.message = new Message(arguments[0]);

        //figuree out input file argument
//        looking up for -i= ... and setting it to a field
    }

    public Message inputString() {
        return this.message;
    }

    public Optional<Path> inputFile() {
        return inputFilePath;
    }


}
