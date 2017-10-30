package com.company;

/**
 * Created by Daniel on 10/30/2017.
 */
public class Node {
    String type;
    int valueInt;
    String valueString;

    public Node(String input) {
        if(Integer.getInteger(input) != null) {
            type = "int";
            valueInt = Integer.getInteger(input);
        } else {
            type = "string";
            valueString = input;
        }
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
}
