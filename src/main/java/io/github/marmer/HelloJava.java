package io.github.marmer;


public class HelloJava {

    /**
     * Ausgabe einer Menge von Zahlen von 1 bis 20 Durch 3 teilbare Zahlen werden durch den String „Fizz“ ersetzt (Bsp.:
     * 6 => „Fizz“) Durch 5 teilbare Zahlen werden durch den String „Buzz“ ersetzt (Bsp.: 10 => „Buzz“) Zahlen auf denen
     * mehrere Regeln zutreffen erhalten alle Ersetzungen Concateniert (Bsp.: 15 => „FizzBuzz“
     *
     * @param startValue is klar, oder?
     */
    public String[] doFizzles(final int startValue) {
        if (isFizzValue(startValue)) {
            return new String[]{"Fizz"};
        }
        if (isBuzzValue(startValue)) {
            return new String[]{"Buzz"};
        }
        return new String[]{"" + startValue};
    }

    private boolean isBuzzValue(final int startValue) {
        return startValue % 5 == 0;
    }

    private boolean isFizzValue(final int startValue) {
        return startValue % 3 == 0;
    }
}
