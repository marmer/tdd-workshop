package io.github.marmer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SternTest {

    @Test
    @DisplayName("Stern an Fehlergrenzen weist keine Fehler auf")
    @SneakyThrows
    void getAnzahlFehler_SternAnFehlergrenzenWeistKeineFehlerAuf() {
        // Preparation
        final Stern stern = newFehlerFreierStern();

        // Execution
        final var result = stern.getAnzahlFehler();

        // Assertion
        assertThat("Anzahl Fehler", result, is(0));
    }

    @NotNull
    private Stern newFehlerFreierStern() {
        return new Stern(5, 40, 5, 5778);
    }

    @Test
    @DisplayName("Stern mit zu gereinger Lichintensität gilt als fehlerhaft")
    @SneakyThrows
    void getAnzahlFehler_SternMitZuGereingerLichintensitaetGiltAlsFehlerhaft() {
        // Preparation
        final Stern stern = newFehlerFreierStern()
            .setLichtintensitaet(39);

        // Execution
        final var result = stern.getAnzahlFehler();

        // Assertion
        assertThat("Anzahl Fehler", result, is(1));
    }

    @Test
    @DisplayName("Zu geringe Temperatur gilt als Fehler")
    @SneakyThrows
    void getAnzahlFehler_ZuGeringeTemperaturGiltAlsFehler() {
        // Preparation
        final Stern stern = newFehlerFreierStern()
            .setTemperatur(5777);

        // Execution
        final var result = stern.getAnzahlFehler();

        // Assertion
        assertThat("Anzahl Fehler", result, is(1));
    }

    @Test
    @DisplayName("Wenn Anzahl der Ecken nicht durch drei oder fuenf teilbar ist, gilt stern als Fehlerhaft")
    @SneakyThrows
    void getAnzahlFehler_AnzahlDerEckenMussDurchDreiTeilbarSein() {
        // Preparation
        final Stern stern = newFehlerFreierStern()
            .setAnzahlEcken(7);

        // Execution
        final var result = stern.getAnzahlFehler();

        // Assertion
        assertThat("Anzahl Fehler", result, is(1));
    }

}
