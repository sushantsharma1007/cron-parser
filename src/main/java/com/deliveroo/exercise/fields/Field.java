package com.deliveroo.exercise.fields;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.deliveroo.exercise.exceptions.InvalidFieldException;
import com.deliveroo.exercise.types.FieldType;

public class Field {

    private final String input;
    private FieldType type;
    private Set<Integer> calculatedValues = new TreeSet<>();

    public Field(String input, FieldType type) throws InvalidFieldException {
        this.type = type;
        this.input = input;

        this.parseDefinedValues();

        this.parseRangeOfValues();

        this.parseIntervals();
        

        if (calculatedValues.isEmpty()) {
            calculatedValues.add(parseNumber(input));
        }
    }


    public String toString() {
        return calculatedValues.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private void parseIntervals() throws InvalidFieldException {
        if (input.startsWith("*")) {
            int interval = 1;
            String[] intervals = input.split("/");
            if (intervals.length > 2) {
                throw new InvalidFieldException("Number " + input + " for " + type + "has too many intervals");
            }
            if (intervals.length == 2) {
                interval = parseNumber(intervals[1]);
            }
            populateClaculatedValues(type.min, type.max, interval);
        }
    }

    private void parseRangeOfValues() throws InvalidFieldException {
        String[] range = input.split("-");
        if (range.length == 2) {
            int start = parseNumber(range[0]);
            int end = parseNumber(range[1]);
            populateClaculatedValues(start, end, 1);
        }
    }

    private void parseDefinedValues() throws InvalidFieldException {
        String[] fixedDates = input.split(",");
        if (fixedDates.length > 1) {
            //fixed values
            for (String date : fixedDates) {
                int value = parseNumber(date);
                populateClaculatedValues(value, value, 1);
            }
        }
    }


    private void populateClaculatedValues(int start, int end, int increment) throws InvalidFieldException {
        if (increment == 0) {
            throw new InvalidFieldException("Number " + input + " for " + type + " interval is 0");
        }
        if (end < start) {
            throw new InvalidFieldException("Number " + input + " for " + type + " ends before it starts");
        }
        if (start < type.min || end > type.max) {
            throw new InvalidFieldException("Number " + input + " for " + type + " is outside valid range (" + type.min + "-" + type.max + ")");
        }
        for (int i = start; i <= end; i += increment) {
            calculatedValues.add(i);
        }
    }

    private Integer parseNumber(String no) throws InvalidFieldException {
        try {
            return Integer.parseInt(no);
        } catch (NumberFormatException nfe) {
            throw new InvalidFieldException("Invalid number '" + no + "' for type " + type + ": " + nfe.getMessage());
        }
    }
}
