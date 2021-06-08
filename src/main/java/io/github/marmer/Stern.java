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
    private int temperatur;

    public int getAnzahlFehler() {
        return anzahlLichtintensitaetsFehler() +
            anzahlTemperaturFehler() +
            anzahlEckenFehler();
    }

    private int anzahlTemperaturFehler() {
        return temperatur < 5778 ? 1 : 0;
    }

    private int anzahlEckenFehler() {
        return anzahlEcken != 5 && anzahlEcken % 3 != 0 ? 1 : 0;
    }


    private int anzahlLichtintensitaetsFehler() {
        return lichtintensitaet < 40 ? 1 : 0;
    }
}
