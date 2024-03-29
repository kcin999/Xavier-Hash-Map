/*
 * This is the driver class. Note that it should only ever interact with the
 * MyHashTable class  - Not with the Record class.
 */
package myhashmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Katie Timmerman CS 180 - 02 Prof: Dr Timmerman Assignment:
 */
public class MyHashMapDriver {

    public static void main(String[] args) throws FileNotFoundException {
        debuggingFunctions();
        completeAnalysis();
        //readWriteData();
        completeAnalysisStringKey();
    }

    public static void debuggingFunctions() {
        MyHashTable<Integer> myTable = new MyHashTable<Integer>(7);//Can be changed to any prime number
        ArrayList<Integer> addedKeys = new ArrayList<Integer>();
        System.out.println("TESTING ADDING ELEMENTS");
        for (int i = 0; i < 5; i++) {
            int key_value = (int) (Math.random() * 1000000);
            if (myTable.insert(key_value, key_value)) {
                addedKeys.add(key_value);
            } else {
                i--; //Don't count the insert if key already in table
            }
        }
        System.out.println(myTable);
        System.out.println(addedKeys);
        System.out.println("\nTESTING FINDING ELEMENTS: Should be true");//All should be found and be true.
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING REMOVING ELEMENTS");//Looking for tombstones
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " removed: " + myTable.remove(key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING REMOVED ELEMENTS: Should be false");//all should be false
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING VALID ELEMENTS AFTER REMOVAL: Should be true");//Want these to be true
        for (int i = 1; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

    }
    
    public static void completeAnalysis() {
        ArrayList<Double> averageArrayList = new ArrayList<Double>();
        MyHashTable<Integer> myTable = new MyHashTable<Integer>(1009);
        for (int i = 0; i < 1009; i++) {
            int key_value = (int) (Math.random() * 1000000);
            if (i == 100 || i == 201 || i == 302 || i == 403 || i == 504 || i == 605 || i == 706 || i == 807 || i == 908 || i == 1007) {
                int totalCollisions = 0;
                for (int j = 0; j < 20; j++) {
                    // add the item
                    if (myTable.insert(key_value, key_value)) {
                        // collect the number of collisions.
                        totalCollisions = totalCollisions + myTable.collisionsForThisInsert;
                        //remove the item just added
                        myTable.remove(key_value);
                    } else {
                        j--;
                    }
                    key_value = (int) (Math.random() * 1000000);

                }
                //average the number of collisions that occured
                double average = totalCollisions / 20.000;
                averageArrayList.add(average);
            } else if (!myTable.insert(key_value, key_value)) {
                i--; //Don't count the insert if key already in table
            }

        }
        System.out.println(averageArrayList);
    }
    

    /**
     * This part is unnecessary for all levels other than A. It is a brief
     * example of how to read and write from a file.
     *
     * @throws FileNotFoundException
     */
    public static void readWriteData() throws FileNotFoundException {
//        //How to print to a file.  OVERWRITES
//        PrintWriter outFile = new PrintWriter("output.txt");
//        //outFile == System.out
//        System.out.println("Hello World");
//        outFile.println("Hello World");
//        outFile.close();

        //Reading Files
        File file = new File("billionaires.csv");
        Scanner input = new Scanner(file);
        input.nextLine();

        ArrayList<String> rowData = new ArrayList();
        while (input.hasNext()) {
            String[] line = input.nextLine().split(",");

            String names = line[0].trim();

            rowData.add(names);
        }
        System.out.println(rowData);
        input.close();
    }

    public static ArrayList getData(String fileName, int indexOfData) throws FileNotFoundException {

        //Reading Files
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        input.nextLine();

        ArrayList<String> rowData = new ArrayList();
        while (input.hasNext()) {
            String[] line = input.nextLine().split(",");

            String names = line[indexOfData].trim();

            rowData.add(names);
        }
        input.close();
        return rowData;
    }

    
    public static void completeAnalysisStringKey() throws FileNotFoundException {
        ArrayList<String> fileData = getData("billionaires.csv",0);
        ArrayList<Double> averageArrayList = new ArrayList<Double>();
        MyHashTable<String> myTable = new MyHashTable<String>(2609);
        
        for (int i = 0; i < fileData.size(); i++) {
            int index_value = (int) (Math.random() * fileData.size());
            String key_value = fileData.get(index_value);
            if (i == 521 || i == 1042 || i == 1563 || i == 2084 || i == 2605) {
                int totalCollisions = 0;
                for (int j = 0; j < 20; j++) {
                    // add the item
                    if (myTable.insert(key_value, key_value)) {
                        // collect the number of collisions.
                        totalCollisions = totalCollisions + myTable.collisionsForThisInsert;
                        //remove the item just added
                        myTable.remove(key_value);
                    } else {
                        j--;
                    }
                    index_value = (int) (Math.random() * fileData.size());
                    key_value = fileData.get(index_value);
                }
                //average the number of collisions that occured
                double average = totalCollisions / 20.000;
                averageArrayList.add(average);
            } else if (!myTable.insert(key_value, key_value)) {
                i--; //Don't count the insert if key already in table
            }

        }
        System.out.println(averageArrayList);
        
        
    }
}
