package io.github.marmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Stern {

    private int anzahlEcken;
    private int lichtintensitaet;
    private int sonnenflecken;

    public int getAnzahlFehler() {
        return anzahlLichtintensitaetsFehler() +
            anzahlSonnenfleckenFehler() +
            anzahlEckenFehler();
    }

    private int anzahlEckenFehler() {
        return anzahlEcken != 5 && anzahlEcken % 3 != 0 ? 1 : 0;
    }

    private int anzahlSonnenfleckenFehler() {
        return sonnenflecken > anzahlEcken ? 1 : 0;
    }

    private int anzahlLichtintensitaetsFehler() {
        return lichtintensitaet < 60 ? 1 : 0;
    }
}
