package io.github.marmer;

import static java.util.stream.Collectors.joining;

/**
 * Gegeben: Zeichenkette wird übersetzt in Morse-String
 * <p>
 * Grundannahme: Niemand übergibt Zeichenketten, die nicht mindestens ein Wort mit der Länge von zwei Zeichen
 * beinhalten
 * <p>
 * Fertiges Dictinoary: siehe Branch „morse“ unter /src/main/resources/morse.dict
 * <p>
 * Unbekannte Zeichen werden zum „Fragezeichen-Morsezeichen“ übersetzt
 * <p>
 * Ein Morsecode baut sich wie folgt auf:
 * <p>
 * Ein „Buchstabe“ wird repräsentiert durch Punkte und Striche
 * <p>
 * Zwischen Buchstaben gibt es ein Leerzeichen (kleine Pause)
 * <p>
 * Zwischen Worten gibt es drei Leerzeichen (lange Pause)
 * <p>
 * Bsp.: "Der Mops" = "-.. . .-.   -- --- .--. ..."
 */
public class MorseEncoder {

    private final MorseDictionary morseBySymbol;

    public MorseEncoder(final MorseDictionary morseBySymbol) {
        this.morseBySymbol = morseBySymbol;
    }

    public String encode(final String text) {
        if (text.isBlank()) {
            return "";
        }

        return text.chars()
            .mapToObj(Character::toString)
            .map(morseBySymbol::toMorse)
            .collect(joining(" "));
    }
}
