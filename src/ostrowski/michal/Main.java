package ostrowski.michal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Main.java
Created: Michal Ostrowski, 2022-04-03
Purpose: Main class to test how the SearchEngine class works
*/
public class Main {

    public static void main(String[] args) {
        // Create simple list with documents
        List<String> sampleDocumentList = prepareSampleDocumentList();

        // Create an instance of SearchEngine object - constructor takes the list of strings
        SearchEngine searchEngine = new SearchEngine(sampleDocumentList);

        //run start method
        start(searchEngine);

    }

    /*
    Provide user interface
     */
    private static void start(SearchEngine engine)
    {
        int choice = -1;    //Invalid option

        while (choice != 0)
        {
            showMenu();

            choice = readChoice();

            switch (choice)
            {
                case 0:
                    // do nothing, exit the loop
                    break;
                case 1:
                    // Read the term user is looking for
                    String term = readTerm();
                    // Perform query operation - assign the query result to the list of Strings
                    List<String> foundDocuments = engine.performQuery(term);

                    // if the query returns the empty list - inform user that any document contains the term the user is looking for
                    if (foundDocuments.isEmpty())
                        System.out.println("\nThere are no documents containing the term: " + term + "\n");
                    else
                        foundDocuments.forEach(System.out::println);
                    break;
            }
        }
    }

    /*
    Display the program menu to the user
     */
    private static void showMenu()
    {
        System.out.println("\nWelcome to the search engine created by Michal Ostrowski");
        System.out.println("   Search documents by word:   1");
        System.out.println("   Exit the program:           0");
    }

    /*
    Read choice from the console
     */
    private static int readChoice()
    {
        boolean goodNumber = false;
        int choice = 0;

        do {
            System.out.print("   Your choice: ");
            String strChoice = new Scanner(System.in).nextLine().trim();
            try {
                choice = Integer.parseInt(strChoice);
                if (choice == 0 || choice == 1)
                    goodNumber = true;
                else
                    System.out.println("Wrong choice, please try again");
            } catch (NumberFormatException e)
            {
                System.out.println("Wrong choice, please try again");
            }
        } while (!goodNumber);
        return choice;
    }

    /*
    Read term from the console
     */
    private static String readTerm()
    {
        // Ask the user to provide the term he/she is looking for
        System.out.print("Type word you are looking for: ");
        return new Scanner(System.in).nextLine().trim();
    }

    /*
    Create sample list of documents (Strings) to test the program
     */
    private static List<String> prepareSampleDocumentList()
    {
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
//    private static List<String> prepareList() {
//        BufferedReader bufReader = null;
//        ArrayList<String> listOfLines = new ArrayList<>();
//        try {
//            bufReader = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\SimpleSearchEngine\\src\\ostrowski\\michal\\file.txt"));
//            String line = bufReader.readLine();
//            while (line != null)
//            {
//                listOfLines.add(line);
//                line = bufReader.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            bufReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return listOfLines;
//    }
}
