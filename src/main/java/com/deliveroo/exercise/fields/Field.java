package com.deliveroo.exercise.fields;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.deliveroo.exercise.exceptions.InvalidFieldException;
import com.deliveroo.exercise.types.FieldType;

public class Field {

    private final String input;
    private FieldType type;
    
    // This set will be used to store output
    private Set<Integer> calculatedValues = new TreeSet<>();

    public Field(String input, FieldType type) throws InvalidFieldException {
        this.type = type;
        this.input = input;

        this.parseDefinedValues();

        this.parseRangeOfValues();

        this.parseIntervals();
        

       /*If stand alone value without any range, interval or multiple fixed values
        * add in the output set
        */
        if (calculatedValues.isEmpty()) {
            calculatedValues.add(parseNumber(input));
        }
    }


    // To stringify the output set
    public String toString() {
        return calculatedValues.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    
    //Parse the intervals
    private void parseIntervals() throws InvalidFieldException {
        if (input.startsWith("*")) {
            int interval = 1;
            String[] intervals = input.split("/");
            if (intervals.length > 2) {
                throw new InvalidFieldException("Input " + input + " for " + type + " contains multiple intervals");
            }
            if (intervals.length == 2) {
                interval = parseNumber(intervals[1]);
            }
            populateClaculatedValues(type.min, type.max, interval);
        }
    }

    //parse the range
    private void parseRangeOfValues() throws InvalidFieldException {
        String[] range = input.split("-");
        if (range.length == 2) {
            int start = parseNumber(range[0]);
            int end = parseNumber(range[1]);
            populateClaculatedValues(start, end, 1);
        }
    }

    //parse the fixed set of values
    private void parseDefinedValues() throws InvalidFieldException {
        String[] fixedDates = input.split(",");
        if (fixedDates.length > 1) {
            for (String date : fixedDates) {
                int value = parseNumber(date);
                populateClaculatedValues(value, value, 1);
            }
        }
    }


    /*Populate the values based on the min, max and the interval they are given
     * for fixed values and range the interval will be 1
     * but for intervals the step will be defined in input
     */
    private void populateClaculatedValues(int start, int end, int increment) throws InvalidFieldException {
        if (increment == 0) {
            throw new InvalidFieldException("Input " + input + " for " + type + " interval is 0");
        }
        if (end < start) {
            throw new InvalidFieldException("Input " + input + " for " + type + " ends before it starts");
        }
        if (start < type.min || end > type.max) {
            throw new InvalidFieldException("Input " + input + " for " + type + " has invalid range (" + type.min + "-" + type.max + ")");
        }
        for (int i = start; i <= end; i += increment) {
            calculatedValues.add(i);
        }
    }

    // parse the input provided and check if that is a valid input else throw exception
    private Integer parseNumber(String no) throws InvalidFieldException {
        try {
            return Integer.parseInt(no);
        } catch (NumberFormatException nfe) {
            throw new InvalidFieldException("Invalid value '" + no + "' for type " + type + ": " + nfe.getMessage());
        }
    }
}
