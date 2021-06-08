package io.github.marmer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SternchenwartungTest {

    @InjectMocks
    private Sternchenwartung underTest;
    @Mock
    private VersandDienstleister versandDienstleister;
    @Mock
    private Werkstatt werkstatt;
    @Mock
    private StarShop24 starShop24;

    @Test
    @DisplayName("Sterne ohne Fehler sollten an den Himmel gehangen werden")
    @SneakyThrows
    void warteSternBeiKoordinate_SterneOhneFehlerSolltenAnDenHimmelGehangenWerden() {
        // Preparation
        final var zielkoordinaten = "x120";
        final var stern = newFehlerfreierStern();
        when(versandDienstleister.holeSternVon(zielkoordinaten)).thenReturn(stern);

        // Execution
        underTest.warteSternBeiKoordinate(zielkoordinaten);

        // Assertion
        verify(versandDienstleister).versende(stern, zielkoordinaten);
    }


    @Test
    @DisplayName("Stern mit einem Fehler sollte ausgebessert werden")
    @SneakyThrows
    void warteSternBeiKoordinate_SternMitEinemFehlerSollteAusgebessertWerden() {
        final var zielkoordinaten = "x120";
        final var zuWartenderStern = newSternMitEinemFehler();
        final var reparierterStern = newFehlerfreierStern();
        when(versandDienstleister.holeSternVon(zielkoordinaten)).thenReturn(zuWartenderStern);
        when(werkstatt.repariere(zuWartenderStern)).thenReturn(reparierterStern);

        // Execution
        underTest.warteSternBeiKoordinate(zielkoordinaten);

        // Assertion
        verify(versandDienstleister).versende(reparierterStern, zielkoordinaten);
    }

    @Test
    @DisplayName("Stern mit mehr als einem Fehler sollte ausgetauscht werden")
    @SneakyThrows
    void warteSternBeiKoordinate_SternMitMehrAlsEinemFehlerSollteAusgetauschtWerden() {
        final var zielkoordinaten = "x120";
        final var zuWartenderStern = newSternMitZweiFehler();
        final var neuerStern = newFehlerfreierStern();
        when(versandDienstleister.holeSternVon(zielkoordinaten)).thenReturn(zuWartenderStern);
        when(starShop24.kaufeSternFuerKoordinate(zielkoordinaten)).thenReturn(neuerStern);

        // Execution
        underTest.warteSternBeiKoordinate(zielkoordinaten);

        // Assertion
        verify(versandDienstleister).versende(neuerStern, zielkoordinaten);
    }

    @NotNull
    private Stern newSternMitZweiFehler() {
        return new Stern(5, 59, 6);
    }

    @NotNull
    private Stern newSternMitEinemFehler() {
        return new Stern(5, 59, 5);
    }

    @NotNull
    private Stern newFehlerfreierStern() {
        return new Stern(5, 60, 5);
    }

}
