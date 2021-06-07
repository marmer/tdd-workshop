package io.github.marmer;


import static java.util.stream.IntStream.rangeClosed;

public class HelloJava {

    /**
     * Ausgabe einer Menge von Zahlen von 1 bis 20 Durch 3 teilbare Zahlen werden durch den String „Fizz“ ersetzt (Bsp.:
     * 6 => „Fizz“) Durch 5 teilbare Zahlen werden durch den String „Buzz“ ersetzt (Bsp.: 10 => „Buzz“) Zahlen auf denen
     * mehrere Regeln zutreffen erhalten alle Ersetzungen Concateniert (Bsp.: 15 => „FizzBuzz“
     *
     * @param startExclusive is klar, oder?
     * @param endExclusive   is klar, oder?
     */
    public String[] doFizzles(final int startExclusive, final int endExclusive) {
        return rangeClosed(startExclusive, endExclusive)
            .mapToObj(this::toFizzle)
            .toArray(String[]::new);
    }

    private String toFizzle(final int number) {
        String result = "";

        if (isFizzValue(number)) {
            result += "Fizz";
        }
        if (isBuzzValue(number)) {
            result += "Buzz";
        }
        if (isPopValue(number)) {
            result += "Pop";
        }
        return result.isEmpty() ?
            "" + number :
            result;
    }

    private boolean isBuzzValue(final int startValue) {
        return startValue % 5 == 0;
    }

    private boolean isPopValue(final int startValue) {
        return startValue % 7 == 0;
    }

    private boolean isFizzValue(final int startValue) {
        return startValue % 3 == 0;
    }
}
