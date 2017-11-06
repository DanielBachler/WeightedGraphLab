package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    public static int[] weights;

    public static void main(String[] args) {
        getInput();
        primsAlgorithm();
        kruskals();
        //getInput();
        System.out.println();
        //getInput();
        warshalls();
        mirror();
        printMatrix();
    }

    //Gets the matrix input from the specified file name inputFile
    public static void getInput() {
        //Finds file and assigns scanner to the file
        try {
            File input = new File(inputFile);
            readInput = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        //Gets first line of values, and reads them into a char array
        String temp = readInput.nextLine();
        labels = temp.toCharArray();
        //The size of the array
        int size = labels.length;
        //Reads through entire file
        int j = 0;
        while (readInput.hasNextLine()) {
            //Creates an array of nodes with the correct length
            Node[] toAdd = new Node[size];
            //Gets the next line
            String line = readInput.nextLine();
            //String tokenizer to cut spaces and make my life easier
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            //Throws out the first value as it is a known label
            String garbage = tokenizer.nextToken();
            //Runs once for each value in the row, assumed that rows are all same size
            for (int i = 0; i < size; i++) {
                //Creates the node
                Node addMe = new Node(tokenizer.nextToken(), i, j);
                //Adds it to the temp array
                toAdd[i] = addMe;
            }
            j++;
            //Adds the array to the matrix
            matrix.add(toAdd);
        }
        readInput.close();
    }

    //Prints the matrix
    public static void printMatrix() {
        for (Node[] nA : matrix) {
            for (Node n : nA) {
                System.out.printf("%4s", n.getValue()[1]);
            }
            System.out.println();
        }


    }

    public static void kruskals() {
        sortByWeight();
        System.out.println();
        for (int weight : weights) {
            System.out.print(weight + " ");
        }
        char[] mst = new char[labels.length * 2];
        int j = 0;
        for (int i = 0; i < weights.length; i++) {
            Node node = findMyDaddy(weights[i]);
            int contains = 0;
            for (int k = 0; k < labels.length * 2; k++) {
                if (mst[k] == labels[node.i] || mst[k] == labels[node.j]) {
                    contains++;
                }
            }
            if (contains < 2) {
                mst[j++] = labels[node.i];
                mst[j++] = labels[node.j];
            }
        }
        System.out.println();
        for (char c : mst) {
            System.out.print(c + " ");
        }
    }

    //Creates a minimum spanning tree using prim's algorithm
    public static void primsAlgorithm() {
        //The LinkedList to hold the final Minimum Spanning Tree
        LinkedList<Integer> mst = new LinkedList<>();
        //Counter for mst
        int i = 0;
        int[] inMST = new int[labels.length];
        //Creates a priority queue for sorting the vertices
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int current = 0;
        boolean spanning = false;
        while (!spanning) {
            mst.add(current);
            inMST[current] = 1;
            //Starting with the first Node, gets and sorts all connected vertices, except self connections equal to 0
            for (Node n : matrix.get(current)) {
                if (n.getValue()[0].equals("int")) {
                    if (Integer.parseInt(n.getValue()[1]) != 0)
                        queue.add(n);
                }
            }
            //Gets the next node
            current = queue.remove().i;
            //Keeps getting new connections until a new one is found
            while (inMST[current] == 1) {
                if (!queue.isEmpty())
                    current = queue.remove().i;
                else {
                    spanning = true;
                    break;
                }
            }
            queue.clear();
        }
        for (int n : mst) {
            System.out.printf("%c ", labels[n]);
        }
    }

    public static void warshalls() {
        for (int k = 0; k < labels.length; k++) {
            for (int i = 0; i < labels.length; i++) {
                for (int j = 0; j < labels.length; j++) {
                    Node one = matrix.get(i)[j];
                    Node two = matrix.get(i)[k];
                    Node three = matrix.get(k)[j];
                    if (one.valueInt > two.valueInt + three.valueInt)
                    {
                        one.valueInt = two.valueInt + three.valueInt;
                        printMatrix();
                    }
                }
            }
        }
    }

    public static void sortByWeight() {
        int k = 0;
        weights = new int[labels.length * labels.length];
        for (int i = labels.length - 2; i >= 0; i--) {
            for (int j = labels.length - 1; j > i; j--) {
                //If the spot in the matrix is a number
                if (matrix.get(i)[j].getValue()[0].equals("int")) {
                    weights[k++] = matrix.get(i)[j].valueInt;
                }
            }
        }
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = weights[i];
        }
        Arrays.sort(temp);
        weights = temp;
    }

    public static Node findMyDaddy(int weight) {
        Node daddy = null;
        for (int i = labels.length - 2; i >= 0; i--) {
            boolean stop = false;
            for (int j = labels.length - 1; j > i; j--) {
                if (matrix.get(i)[j].valueInt == weight) {
                    daddy = matrix.get(i)[j];
                    daddy.valueInt = 0;
                    stop = true;
                    break;
                }
            }
            if (stop)
                break;
        }
        return daddy;
    }

    public static void mirror() {
        for(int i = 0; i < labels.length; i++) {
            for(int j = 0; j <= i; j++) {
                Node temp = matrix.get(j)[i];
                temp.valueInt = matrix.get(i)[j].valueInt;
            }
        }
    }
}
