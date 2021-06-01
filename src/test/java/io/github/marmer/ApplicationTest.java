package io.github.marmer;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    @DisplayName("Hello Test")
    @SneakyThrows
    void someStaticJavaMethod_someStaticJavaMethod() {
        ApplicationKt.main(new String[]{"Hundekuchen", "Mettbrötchen"});
        HelloJava.someStaticJavaMethod("foo", "bar");
    }

}
