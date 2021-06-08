package io.github.marmer;


public class Sternchenwartung {

    private final VersandDienstleister versandDienstleister;
    private final Werkstatt werkstatt;
    private final StarShop24 starShop24;

    public Sternchenwartung(final VersandDienstleister versandDienstleister, final Werkstatt werkstatt,
        final StarShop24 starShop24) {
        this.versandDienstleister = versandDienstleister;
        this.werkstatt = werkstatt;
        this.starShop24 = starShop24;
    }

    public void warteSternBeiKoordinate(final String zielkoordinaten) {
        final var zuWartenderStern = versandDienstleister.holeSternVon(zielkoordinaten);
        final var gewarteterStern = fuehreWartungDurch(zielkoordinaten, zuWartenderStern);
        versandDienstleister.versende(gewarteterStern, zielkoordinaten);
    }

    private Stern fuehreWartungDurch(final String zielkoordinaten, final Stern stern) {
        switch (stern.getAnzahlFehler()) {
            case 0:
            case 1:
                return stern;
            case 2:
                return werkstatt.repariere(stern);
            default:
                return starShop24.kaufeSternFuerKoordinate(zielkoordinaten);
        }
    }
}
