package ostrowski.michal;

import java.util.*;

/*
ImprovedSearchEngine.java
Created: Michal Ostrowski, 2022-04-28
Purpose: Class implements Tokenizer interface and implements its methods
*/
public class BasicTokenizer implements Tokenizer{

    public Map<String, Double> tokenizeDocument(String document) {
        List<String> tokens = Arrays.asList(tokenize(document));
        Map<String, Double> tokensMap = new Hashtable<>();

        int numberOfWordsInDocument = tokens.size();

        for (String token : tokens) {
            int wordOccurrences = Collections.frequency(tokens, token);
            Double tf = (double) wordOccurrences / numberOfWordsInDocument;
            tokensMap.put(token, tf);
        }

        return tokensMap;
    }

    public String[] tokenize(String document) {
        // In this class we assume that all documents contain only those for special characters, space and tabulator
        String regex = "[,.?!;:«»…—]";
        document = document.replaceAll(regex, " ").trim().replaceAll(" +", " ").toLowerCase();
//        System.out.println(document);
        String[] tokens = document.split("\\s");

        return tokens;
    }
}
