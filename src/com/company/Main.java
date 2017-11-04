package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    //Input file
    public static final String inputFile = "input.txt";
    //Scanner for input
    public static Scanner readInput;
    //Matrix
    public static LinkedList<Node[]> matrix = new LinkedList<>();
    //Char array of labels
    public static char[] labels;
    //sorted array
    public static int[] wieght;

    public static void main(String[] args) {
        getInput();
        printMatrix();
    }

    //Gets the matrix input from the specified file name inputFile
    public static void getInput() {
        //Finds file and assigns scanner to the file
        try{
            File input = new File(inputFile);
            readInput = new Scanner(input);
        } catch(FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        //Gets first line of values, and reads them into a char array
        String temp = readInput.nextLine();
        labels = temp.toCharArray();
        //The size of the array
        int size = labels.length;
        //Reads through entire file
        while(readInput.hasNextLine()) {
            //Creates an array of nodes with the correct length
            Node[] toAdd = new Node[size];
            //Gets the next line
            String line = readInput.nextLine();
            //String tokenizer to cut spaces and make my life easier
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            //Throws out the first value as it is a known label
            String garbage = tokenizer.nextToken();
            //Runs once for each value in the row, assumed that rows are all same size
            for(int i = 0; i < size; i++) {
                //Creates the node
                Node addMe = new Node(tokenizer.nextToken());
                //Adds it to the temp array
                toAdd[i] = addMe;
            }
            //Adds the array to the matrix
            matrix.add(toAdd);
        }
        readInput.close();
    }

    //Prints the matrix
    public static void printMatrix() {
        for(Node[] nA: matrix) {
            for(Node n: nA) {
                System.out.printf("%4s", n.getValue()[1]);
            }
            System.out.println();
        }
    }

    public static void kruskals(){
        String[] answer;

        //find smallest edge, check for loop, if good add to list
    }

    public static void sortByWieght(){
        int i = 0;
        for (Node[] nA: matrix){
            for(Node n: nA) {
                if (n.getValue()[0].equals("int") && !n.getValue()[1].equals("0")){

                    i++;
                }
                ;
            }
        }
    }
}
