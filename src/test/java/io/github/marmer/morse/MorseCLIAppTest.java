package io.github.marmer.morse;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import io.github.marmer.morse.usecases.MorseCodeMapper;
import io.github.marmer.morse.usecases.Printer;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MorseCLIAppTest {

    private final Printer printer = mock(Printer.class);

    @Test
    @DisplayName("Sollte Klartext in Morsecode umwandeln und ausgeben")
    @SneakyThrows
    void run_SollteKlartextInMorsecodeUmwandelnUndAusgeben() {
        // Preparation
        final var underTest = newMorseCLIAppForParams("Der Hund.");

        // Execution
        underTest.run();

        // Assertion
        verify(printer).print("-.. . .-.   .... ..- -. -.. .-.-.-");
    }

    @Test
    @DisplayName("Sollte Morsecode in Klartext umwandeln und ausgeben")
    @SneakyThrows
    void run_SollteMorsecodeInKlartextUmwandelnUndAusgeben() {
        // Preparation
        final var underTest = newMorseCLIAppForParams("-.. . .-.   .... ..- -. -.. .-.-.-");

        // Execution
        underTest.run();

        // Assertion
        verify(printer).print(argThat(equalToIgnoringCase("Der Hund.")));
    }

    @NotNull
    private MorseCLIApp newMorseCLIAppForParams(final String args) {
        return new MorseCLIApp(() -> printer, new MorseCodeMapper(), args);
    }

}
