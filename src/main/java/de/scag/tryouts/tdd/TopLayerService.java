package de.scag.tryouts.tdd;

public class TopLayerService {
    private MidLayerService midLayerService;

    public TopLayerService(final MidLayerService midLayerService) {
        this.midLayerService = midLayerService;
    }

    public boolean getConverted(final int val) {
        if (val < 0) {
            return !midLayerService.isComplicatedFoo(Math.abs(val));
        } else if (val > 0) {
            return midLayerService.isComplicatedFoo(val);
        } else {
            return false;
        }
    }
}
