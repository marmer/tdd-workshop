package io.github.marmer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;

import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HelloJavaTest {

    private final HelloJava underTest = new HelloJava();

    private static Stream<Arguments> onZahlenBleibenZahlen() {
        return Stream.of(
            Arguments.of(1, "1"),
            Arguments.of(11, "11")
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Zahlen bleiben Zahlen")
    @SneakyThrows
    @MethodSource("onZahlenBleibenZahlen")
    void doFizzles_ZahlenBleibenZahlen(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    private static Stream<Arguments> onDurchDreiTeilbarWirdFizz() {
        return Stream.of(
            Arguments.of(3, "Fizz"),
            Arguments.of(9, "Fizz")
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Durch drei teilbare Zahlen sollten zu 'Fizz' werden")
    @SneakyThrows
    @MethodSource("onDurchDreiTeilbarWirdFizz")
    void doFizzles_DurchDreiTeilbarWirdFizz(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    private static Stream<Arguments> onDurchFuenfTeilbarWirdBuzz() {
        return Stream.of(
            Arguments.of(5, "Buzz")/*,
            Arguments.of(10, "Buzz")*/
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Durch fünf teilbare Zahlen sollten zu 'Buzz' werden")
    @SneakyThrows
    @MethodSource("onDurchFuenfTeilbarWirdBuzz")
    void doFizzles_DurchFuenfTeilbarWirdBuzz(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    // TODO: marmer 07.06.2021 Fall durch fünf fehlt
    // TODO: marmer 07.06.2021 FizzBuzz Fall fehlt
    // TODO: marmer 07.06.2021 Fall ganze Liste fehlt
    // TODO: marmer 07.06.2021 Wie Umgang mit negativen werten

}
