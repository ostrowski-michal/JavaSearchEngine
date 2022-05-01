package ostrowski.michal;

import java.util.Map;

/*
Document.java
Created: Michal Ostrowski, 2022-04-28
Purpose: Class to store information about the document:
    content: document's content
    map:
        key -> token
        value -> tokens term frequency factor in document
*/
public class Document {
    private String document;
    private Map<String, Double> tokensMap;

    public Document(String document, Map<String, Double> tokensInfo) {
        this.document = document;
        this.tokensMap = tokensInfo;
    }

    public Map<String, Double> getTokensMap() {
        return tokensMap;
    }

    public String getDocument() {
        return document;
    }

    public double getTF(String token) {
        return tokensMap.get(token);
    }

    @Override
    public String toString() {
        return "Document{" +
                "document='" + document + '\'' +
                '}';
    }
}
