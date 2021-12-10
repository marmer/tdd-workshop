package io.github.marmer.morse.domain;

public interface MorseDictionary {

    String getMorseBySymbol(String symbol);

    String getSymbolByMorse(String morse);
}
