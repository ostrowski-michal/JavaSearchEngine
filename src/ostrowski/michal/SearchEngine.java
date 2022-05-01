package ostrowski.michal;

import java.util.Collection;

public interface SearchEngine<T> {
    void setTokenizer(Tokenizer tokenizer);
    void createIndexStructure();
    Collection<Document> performQuery(String term);
}
