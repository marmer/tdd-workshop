package io.github.marmer;


import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class HelloJava {
    public static void someStaticJavaMethod(final String... args) {
        Stream.of(args)
                .map(HelloJava::helloWorldString)
                .forEach(System.out::println);
    }

    @NotNull
    private static String helloWorldString(final String arg) {
        return "Hello world from Java: " + arg;
    }
}
