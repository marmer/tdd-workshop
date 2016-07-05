package de.scag.tryouts.tdd;

/**
 * Ausweg: Powermock. Macht aber mehr kaputt als ganz. Umfangreiches Refactoring
 * mit Delelegate bringt nur etwas, wenn sich nirgends auf den Typ hier
 * verlassen werden muss (instanceof oder class)
 *
 * @author mertinat
 * @since  05.07.2016
 */
public class EvilFinalMethodTrapService {
    public final boolean isComplicatedFoo(final int fooable) {
        final long base = System.currentTimeMillis();

        if ((fooable % 4) == 0) {
            return (nThSqrt(base, 5) % 2) == 0;
        } else if ((fooable % 3) == 0) {
            return (Math.random() % 2) == 1;
        } else {
            final long someVal = (base / fooable) % 3;

            return (someVal == 1) || (someVal == 2);
        }
    }

    private static double nThSqrt(final long b, final long i) {
        return Math.pow(b, 1.0 / b);
    }
}
