package ostrowski.michal;

import java.util.*;

/*
ImprovedSearchEngine.java
Created: Michal Ostrowski, 2022-04-29
Purpose: Search engine class which contains instance variable Map<String, List<String>> - key, value pairs where all the information about
documents will be stored as an implementation of inverted index
*/
public class ImprovedSearchEngine implements SearchEngine<Document> {
    private Tokenizer tokenizer;
    private List<String> documents;
    private Map<String, List<Document>> index;

    private int numOfDocuments;


    public ImprovedSearchEngine(List<String> documents) {
        index = new Hashtable<>();
        this.documents = documents;

    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void createIndexStructure() {
        mapToIndex();
        sortDocumentsByTFIDF();
//        System.out.println(numOfDocuments);
//        for (Map.Entry<String, List<Document>> documentEntry : index.entrySet())
//            System.out.println( documentEntry.getKey() + " => " + documentEntry.getValue().toString());
    }


    public Collection<Document> performQuery(String term) {
        return index.getOrDefault(term.toLowerCase(), Collections.emptyList());
    }


    private void mapToIndex() {
        // Check if the list is null - Inform the user
        if (documents == null)
            System.out.println("Cannot build an inverted index from null object.");
            // Check if the list is empty - Inform the user
        else if (documents.isEmpty())
        {
            numOfDocuments = 0;
            System.out.println("List of documents is empty.");
        }
            // If everything is ok save the total number of documents in the list to instance variable
            // and map terms and documents to inverted index
        else
        {
            for (String document : documents)
                // Check if the document is not null, if it is null -> skip
                if (document != null) {
                    numOfDocuments++;
                    mapToIndex(document);
                }
        }
    }

    private void mapToIndex(String document) {
        Map<String, Double> tokensMap = tokenizer.tokenizeDocument(document);

        Document doc = new Document(document, tokensMap);

        for (Map.Entry<String, Double> token : tokensMap.entrySet() ){
            if (index.containsKey(token.getKey())) {
                index.get(token.getKey()).add(doc);
            }
            else {
                List<Document> documents = new ArrayList<>(){
                    {add(doc);}
                };
                index.put(token.getKey(), documents);
            }
        }
    }

    private void sortDocumentsByTFIDF() {
        DocumentsComparator comparator = new DocumentsComparator();

        for (Map.Entry<String, List<Document>> documentEntry : index.entrySet()) {
            comparator.setToken(documentEntry.getKey());

            Collections.sort(documentEntry.getValue(), comparator);
        }
    }





    //TODO
        /*
        Add Document class to store info about document and its tokens
        Sort the list of documents by tfidf
        Compare documents by tfidf
        We need to store tfidf (for searching by more than one term)
        idf is same for all docs
        tf = nofWordOccurrences / nofWords
        sort(term)
            doc_tfidf = count(term) / tokens size


        time measurement for the new solution
         */
}
