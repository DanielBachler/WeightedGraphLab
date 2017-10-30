package com.company;

import com.sun.deploy.util.ArrayUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    //Input file
    public static final String inputFile = "input.txt";
    //Scanner for input
    public static Scanner readInput;
    //Matrix
    public static LinkedList<Character[]> matrix = new LinkedList<>();

    public static void main(String[] args) {

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
        //Read input from file and creates a matrix
        while(readInput.hasNextLine()) {
            //Gets the line from the input file
            String line = readInput.nextLine();
            //Using wizardry we convert from char[] to Character[]
            //This is a hack solution I found on stack overflow as I could not think of a good way to do the conversion
            //Also I figure this part is unimportant since its not the focus of the lab
            Character[] bigBoi = line.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            //Adds temp to the matrix
            matrix.add(bigBoi);
        }
    }
}
