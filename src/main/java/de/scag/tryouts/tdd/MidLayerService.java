package de.scag.tryouts.tdd;

public class MidLayerService {
    BottomLayerService bottomService;

    public MidLayerService(final BottomLayerService bottomService) {
        this.bottomService = bottomService;
    }

    public boolean isComplicatedFoo(final int val) {
        switch (val % 4) {
        case 0:
            return true;

        case 1:

            return !bottomService.anotherComplicatedLogic(val);
                // throw new NullPointerException("Fehler im genutzten Modul.");

        // return !bottomService.anotherComplicatedLogic(val + 3);

        case 2:
            return bottomService.anotherComplicatedLogic(val);

        default:
            return false;
        }
    }
}
