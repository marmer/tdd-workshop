package io.github.marmer.morsecoding.application_and_infrastructure;

@FunctionalInterface
interface OutputConsumer {
    void consume(String output);
}
