package io.github.marmer;


import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class Ohce {

    private final Supplier<String> inputSupplier;
    private final Consumer<String> outputConsumer;

    public Ohce(final Supplier<String> inputSupplier, final Consumer<String> outputConsumer) {
        this.inputSupplier = inputSupplier;
        this.outputConsumer = outputConsumer;
    }

    public void start(@NotNull final String username) {

    }
}
