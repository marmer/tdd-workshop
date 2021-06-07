package io.github.marmer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        final var result = underTest.doFizzles(eingabe, eingabe);

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
        final var result = underTest.doFizzles(eingabe, eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    private static Stream<Arguments> onDurchFuenfTeilbarWirdBuzz() {
        return Stream.of(
            Arguments.of(5, "Buzz"),
            Arguments.of(10, "Buzz")
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Durch fünf teilbare Zahlen sollten zu 'Buzz' werden")
    @SneakyThrows
    @MethodSource("onDurchFuenfTeilbarWirdBuzz")
    void doFizzles_DurchFuenfTeilbarWirdBuzz(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe, eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    private static Stream<Arguments> onDurchSiebenTeilbarWirdPop() {
        return Stream.of(
            Arguments.of(7, "Pop"),
            Arguments.of(14, "Pop")
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Durch sieben teilbare Zahlen sollten zu 'Pop' werden")
    @SneakyThrows
    @MethodSource("onDurchSiebenTeilbarWirdPop")
    void doFizzles_DurchSiebenTeilbarWirdFizz(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe, eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    private static Stream<Arguments> onDurchDreiUNDFuenfTeilbarWirdFizzBuzz() {
        return Stream.of(
            Arguments.of(15, "FizzBuzz"),
            Arguments.of(35, "BuzzPop"),
            Arguments.of(21, "FizzPop"),
            Arguments.of(105, "FizzBuzzPop")
        );
    }

    @ParameterizedTest(name = "[{index}] Eingabe: {0} Erwartete Ausgabe: \"{1}\"")
    @DisplayName("Durch fünf teilbare Zahlen sollten zu 'FizzBuzz' werden")
    @SneakyThrows
    @MethodSource("onDurchDreiUNDFuenfTeilbarWirdFizzBuzz")
    void doFizzles_DurchDreiUNDFuenfTeilbarWirdFizzBuzz(final int eingabe, final String erwarteteAusgabe) {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(eingabe, eingabe);

        // Assertion
        assertThat(result, arrayContaining(erwarteteAusgabe));
    }

    @Test
    @DisplayName("Kann ganze Mengen konvertieren")
    @SneakyThrows
    void doFizzles_KannGanzeMengenKonvertieren() {
        // Preparation

        // Execution
        final var result = underTest.doFizzles(1, 20);

        // Assertion
        assertAll(
            () -> assertThat(result[0], is("1")),
            () -> assertThat(result[1], is("2")),
            () -> assertThat(result[19], is("Buzz")),
            () -> assertThat(result.length, is(20))
        );
    }

}
