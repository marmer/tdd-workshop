package de.scag.tryouts.tdd;

public class MidLayerService {
    BottomLayerService bottomService;

    public boolean isComplicatedFoo(final int val) {
        switch (val % 4) {
        case 0:
            return true;

        case 1:
            return !bottomService.anotherComplicatedLogic(val);
                // throw new NullPointerException("Fehler im genutzten Modul.");

        case 2:
            return bottomService.anotherComplicatedLogic(val);

        default:
            return false;
        }
    }
}
