package io.github.marmer.morse.domain;

public class InputTypeDetector {

    public InputType getTypeOf(final String inputString) {
        return inputString
            .replace(".", "")
            .replace("-", "")
            .replace(" ", "")
            .isEmpty() ?
            InputType.MORSE :
            InputType.TEXT;
    }

    public enum InputType {
        MORSE, TEXT
    }
}
