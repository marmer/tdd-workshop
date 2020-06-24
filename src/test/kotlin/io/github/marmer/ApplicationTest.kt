package io.github.marmer

import org.junit.jupiter.api.Test

class ApplicationTest {

    @Test
    fun helloTest() {
        main(arrayOf("Hundekuchen", "Mettbrötchen"))
        HelloJava.someStaticJavaMethod("foo", "bar")
    }
}
