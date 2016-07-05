package de.scag.tryouts.tdd;

public class TopLayerService {
    private MidLayerService service;

    public boolean getConverted(final int val) {
        if (val < 0) {
            return !service.isComplicatedFoo(val);
        } else if (val > 0) {
            return service.isComplicatedFoo(val);
        } else {
            return false;
        }
    }
}
