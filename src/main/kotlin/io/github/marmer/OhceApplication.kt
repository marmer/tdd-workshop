package io.github.marmer

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.function.Consumer
import java.util.function.Supplier


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val ohce = Ohce(
        Supplier { reader.readLine() },
        Consumer(::println)
    )
    ohce.start(args[0])
}
