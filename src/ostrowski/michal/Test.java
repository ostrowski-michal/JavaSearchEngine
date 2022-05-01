package ostrowski.michal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/*
Test.java
Created: Michal Ostrowski, 2022-04-29
Purpose: Class is created to compare two solutions (old one and improved) by measuring time
for creating index and performing query
Test is performed on the file file.txt from the files folder
*/
public class Test {
    public static void main(String[] args) {
        List<String> sample = prepareList();     // list contains almost 10.000 strings
        Instant start;
        Instant finish;
        long timeElapsed;
        String result = "Done in %d ms\n";
        String query = "pan";
        int queries = 1000000;
//        String document = readFileToString();
//        System.out.println(document);



        // Testing ImprovedSearchEngine
        System.out.println("Testing ImprovedSearchEngine using List:\n");

            // Indexing
        System.out.print("\tIndexing documents...\t");
        start = Instant.now();
        Tokenizer tokenizer2 = new BasicTokenizer();
        SearchEngine engine2 = new ImprovedSearchEngine(sample);
        engine2.setTokenizer(tokenizer2);
        engine2.createIndexStructure();
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf((result) + "%n", timeElapsed);

            // Querying - simulate querying n times
        System.out.printf("\tPerforming %d queries\t", queries);
        start = Instant.now();
        for (int i = 0; i < queries; i++)
            engine2.performQuery(query);
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf((result) + "%n", timeElapsed);

        // Testing BasicSearchEngine (that was my first version)
        System.out.println("Testing BasicSearchEngine:\n");

            // Indexing
        System.out.print("\tIndexing documents...\t");
        start = Instant.now();
        BasicSearchEngine basicSearchEngine = new BasicSearchEngine(sample);
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf((result) + "%n", timeElapsed);

            // Querying - simulate querying 1000 times
        System.out.printf("\tPerforming %d queries\t", queries);
        start = Instant.now();
        for (int i = 0; i < queries; i++)
            basicSearchEngine.performQuery(query);
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf((result) + "%n", timeElapsed);
    }

    private static List<String> prepareSample() {
        String document1 = "the brown fox jumped over the brown dog";
        String document2 = "the lazy brown dog sat in the corner";
        String document3 = "the red fox bit the lazy dog";

        List<String> documents = new ArrayList<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);

        return documents;
    }

    /*
    Test method to add more documents (Strings) from file
     */
    private static List<String> prepareList() {
        BufferedReader bufReader = null;
        ArrayList<String> listOfLines = new ArrayList<>();
        try {
            bufReader = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\JavaSearchEngine\\files\\file.txt"));
            String line = bufReader.readLine();
            while (line != null)
            {
                listOfLines.add(line);
                line = bufReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }

    private static String readFileToString() {
        BufferedReader bufReader = null;
        StringBuilder document = new StringBuilder("\n");
        try {
            bufReader = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\JavaSearchEngine\\files\\file.txt"));
            String line = bufReader.readLine();
            while (line != null)
            {
                document.append(line).append(" ");
                line = bufReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.toString();
    }
}
