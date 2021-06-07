package io.github.marmer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OhceTest {

    @InjectMocks
    private Ohce underTest;

    @Mock
    private Supplier<String> inputSupplier;

    @Mock
    private Consumer<String> outputSupplier;


    @Test
    @DisplayName("Die Anwendung sollte sich durch ein End-Kommando beenden")
    @SneakyThrows
    void start_DieAnwendungSollteSichDurchEinEndKommandoBeenden() {
        // Preparation
        when(inputSupplier.get()).thenReturn("Stop!");

        // Execution
        underTest.start("Frank");

        // Assertion
        verify(outputSupplier).accept("Adios Frank");
    }
}
