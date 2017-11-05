package com.company;

public class Node implements Comparable<Node>{
    String type;
    int valueInt;
    String valueString;
    int spot;

    public Node(String input, int spot) {
        try {
            type = "int";
            valueInt = Integer.parseInt(input);
        } catch (NumberFormatException n) {
            type = "string";
            valueString = input;
        }
        this.spot = spot;
    }

    //Gets the value and returns it.  Format is a string array, first value is type second value is value
    public String[] getValue() {
        String[] returnMe = new String[2];
        switch (type) {
            case "int":
                returnMe[0] = "int";
                returnMe[1] = "" + valueInt;
                break;
            case "string":
                returnMe[0] = "string";
                returnMe[1] = valueString;
                break;
            default:
                returnMe[0] = "error";
                break;
        }
        return returnMe;
    }

    //Negative before
    //Positive after
    //0 equal
    //Makes the nodes sortable with a priority queue
    //Only works for nodes with an int value
    //Returns 0 if non-functioning
    public int compareTo(Node n) {
        //Checks if both nodes are int nodes
        if(this.getValue()[0].equals("int") && n.getValue()[0].equals("int")) {
            int one = Integer.parseInt(this.getValue()[1]);
            int two = Integer.parseInt(n.getValue()[1]);
            if(one > two) {
                return 1;
            } else if(one < two) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
