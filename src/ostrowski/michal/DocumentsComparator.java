package ostrowski.michal;

import java.util.Comparator;

/*
DocumentsComparator.java
Created: Michal Ostrowski, 2022-04-28
Purpose: User-defined comparator to compare documents by tfidf factor for a specified token
*/
public class DocumentsComparator implements Comparator<Document> {


    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int compare(Document o1, Document o2) {
        return Double.compare(o2.getTF(token), o1.getTF(token));
    }
}
