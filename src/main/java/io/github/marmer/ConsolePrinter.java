package io.github.marmer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
