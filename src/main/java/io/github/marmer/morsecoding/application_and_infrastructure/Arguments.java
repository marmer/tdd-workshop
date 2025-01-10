package io.github.marmer.morsecoding.application_and_infrastructure;

class Arguments {
    private final Input input;

    public Arguments(String[] arguments) {
        this.input = new Input(arguments[0]);
    }

    public Input getInput() {
        return this.input;
    }


    record Input(String value) {

    }
}
