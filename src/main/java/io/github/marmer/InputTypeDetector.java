package io.github.marmer;

public class InputTypeDetector {

    public InputType getTypeOf(String inputString) {
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
