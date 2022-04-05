package ostrowski.michal;

import java.util.*;

/*
SearchEngine.java
Created: Michal Ostrowski, 2022-04-03
Purpose: Class contains instance variable Map<String, List<String>> - key, value pairs where all the information about
documents will be stored as an implementation of inverted index
*/
public class SearchEngine
{
    private final Map<String, List<String>> indexMap = new Hashtable<>();   // Inverted index data structure
    private int numOfDocuments;                                             // Total number of documents in the list

    // Public constructor of SearchEngine object takes the List of documents (Strings)
    public SearchEngine(List<String> documents)
    {
        // Check if the list is null - Inform the user
        if (documents == null)
            System.out.println("Cannot build an inverted index from null object.");
            // Check if the list is empty - Inform the user
        else if (documents.isEmpty())
            System.out.println("List of documents is empty.");
            // If everything is ok save the total number of documents in the list to instance variable
            // and map terms and documents to inverted index
        else
        {
            numOfDocuments = documents.size();
            for (String document : documents)
                // Check if the document is not null, if it is null -> skip
                if (document != null)
                    mapToInvertedIndex(document);
        }
    }

    /*
    Find and return the list of documents which contain the term the user is looking for
     */
    public List<String> performQuery(String term)
    {
        // Get the List of documents from inverted index where term is a key
        List<String> relatedDocuments = indexMap.getOrDefault(term.toLowerCase(), Collections.emptyList());
        // Sort the list by TermFrequency-InverseDocumentFrequency
        sortByTFIDF(relatedDocuments, term);

        // Return the sorted list
        return relatedDocuments;
    }

    /*
    Map every term and related document to inverted index hashtable
     */
    private void mapToInvertedIndex(String document)
    {
        document = document.trim()                      // trim white spaces from the beginning and end of the string
                .toLowerCase()                          // normalize to lower case
                .replaceAll(",", "")    // skip all commas
                .replaceAll("\\.", ""); // skip all dots
        // Tokenize a string to single words (terms)
        String[] terms = document.split(" ");

        // loop through the terms found in document
        for (String term : terms)
        {
            // if the term already exists in inverted index
            if (indexMap.containsKey(term))
            {
                // Retrieve the actual list of documents and add new document to the list
                indexMap.get(term).add(document);
            }
            // if the term doesn't exist in inverted index
            else
            {
                List<String> documents = new ArrayList<>();     // Create new list of documents for the term
                documents.add(document);                        // Add document to the list
                indexMap.put(term, documents);                  // Add the term and connected list of documents to the inverted index
            }
        }
    }

    /*
    Sort list of documents by TermFrequency-InverseDocumentFrequency factor
     */
    private void sortByTFIDF(List<String> documents, String term)
    {
        // Create the map to store documents and related tfidf factor
        Map<String, Double> unsortedMap = new HashMap<>();

        // Calculate idf factor, common for all documents
        double idf = calculateIDF(documents.size());

        // for every document calculate tfidf factor and put it to the map
        for (String document : documents)
            unsortedMap.put(document, calculateTFIDF(term, document, idf));

        // Create an instance of Map which will contain documents sorted by tfidf value (in descending order)
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        unsortedMap.entrySet()                          // Get the entry
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))      // Sort map by values (tfidf factor) in descending order
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));      // Put key, value pairs in the map in descending value order

        documents.clear();                              // Clear the list with documents
        documents.addAll(sortedMap.keySet());           // Fill the list with documents sorted by tfidf
    }

    /*
    Calculate tfidf factor for the specific term and specific document
     */
    private Double calculateTFIDF(String term, String document, double idf) // idf factor is common for all documents
    {
        // Calculate tf - specific for every document
        double tf = calculateTF(term, document);
        // Calculate tfidf factor
        double tfidf = tf * idf;
        return tfidf;
    }

    /*
     Calculate TermFrequency in specific document
     */
    private double calculateTF(String term, String document)
    {
        // Tokenize a string to single words
        String[] words = document.trim().split(" ");

        double counter = 0.0;
        // Count the appearances of the word in the document
        for (String word : words) {
            if (term.equalsIgnoreCase(word))
                counter++;
        }
        int nofWordsInDocument = words.length;
        // Calculate TermFrequency = num of appearances of the word in the document  /  total num of words in the document
        double tf = counter / nofWordsInDocument;

        return tf;
    }

    /*
    Calculate idf factor - it is common for all documents containing the term
     */
    private double calculateIDF(double nofDocumentsContainingTerm)
    {
        int totalNumOfDocuments = numOfDocuments;
        if (nofDocumentsContainingTerm == 0.0)
            nofDocumentsContainingTerm++; // Add 1.0 to avoid division-by-zero

        double idf = Math.log(totalNumOfDocuments / nofDocumentsContainingTerm);
        return idf;
    }
}
