package ostrowski.michal;

import java.util.Map;

/*
Tokenizer.java
Created: Michal Ostrowski, 2022-04-28
Purpose: Interface provides to abstract methods to return
    map:
        key - token
        value - double value associated with the token
    String array with tokens
*/
public interface Tokenizer {
    Map<String, Double> tokenizeDocument(String document);
    String[] tokenize(String document);
}
