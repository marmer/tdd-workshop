package io.github.marmer.morsecoding.domain;

public record Message(String value) {

    public boolean isPlainText() {
        return !value.matches("^[ .-]*$");
    }

    public boolean isEmpty() {
        return value.isBlank();
    }
}
