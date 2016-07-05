package de.scag.tryouts.tdd;

public class BottomLayerService {
    public boolean anotherComplicatedLogic(final int val) {
        switch (val % 3) {
        case 0:
            return false;

        case 1:
            return true;

        default:
            return false;
        }
    }
}
