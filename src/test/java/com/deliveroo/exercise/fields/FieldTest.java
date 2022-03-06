package com.deliveroo.exercise.fields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.deliveroo.exercise.exceptions.InvalidFieldException;
import com.deliveroo.exercise.types.FieldType;

public class FieldTest {

    @Test
    public void testCorrectRange() throws InvalidFieldException {
        Field field = new Field("1-5", FieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5", field.toString());
        field = new Field("1-1", FieldType.DAY_OF_MONTH);
        assertEquals("1", field.toString());
        field = new Field("1-2", FieldType.DAY_OF_MONTH);
        assertEquals("1 2", field.toString());
        field = new Field("1-15", FieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", field.toString());


        field = new Field("0-1", FieldType.HOURS);
        assertEquals("0 1", field.toString());
        field = new Field("0-3", FieldType.HOURS);
        assertEquals("0 1 2 3", field.toString());
        field = new Field("0", FieldType.HOURS);
        assertEquals("0", field.toString());
        field = new Field("23", FieldType.HOURS);
        assertEquals("23", field.toString());
    }


    @Test
    public void testIncorrectRange() {
        parse("0-5", FieldType.DAY_OF_MONTH, "outside valid range");
        parse("0-5-6", FieldType.DAY_OF_MONTH, "Invalid number");
        parse("1-32", FieldType.DAY_OF_MONTH, "outside valid range");
        parse("1-0", FieldType.DAY_OF_MONTH, "ends before it starts");
    }


    @Test
    public void testCorrectFixedValues() throws InvalidFieldException {
        Field field = new Field("1", FieldType.DAY_OF_MONTH);
        assertEquals("1", field.toString());
        field = new Field("1,2,3,4,5", FieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5", field.toString());
        field = new Field("1,1,1", FieldType.DAY_OF_MONTH);
        assertEquals("1", field.toString());
        field = new Field("1,2", FieldType.DAY_OF_MONTH);
        assertEquals("1 2", field.toString());
        field = new Field("1,2,3,4,5,6,7", FieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5 6 7", field.toString());


        field = new Field("0,1", FieldType.HOURS);
        assertEquals("0 1", field.toString());
        field = new Field("0,1,2,3", FieldType.HOURS);
        assertEquals("0 1 2 3", field.toString());
        field = new Field("0", FieldType.HOURS);
        assertEquals("0", field.toString());
        field = new Field("0,0", FieldType.HOURS);
        assertEquals("0", field.toString());
        field = new Field("0,23", FieldType.HOURS);
        assertEquals("0 23", field.toString());
    }

    @Test
    public void testIncorrectFixedValues() {
        parse("0,5", FieldType.DAY_OF_MONTH, "outside valid range");
        parse("1,32", FieldType.DAY_OF_MONTH, "outside valid range");
        }

    @Test
    public void testCorrectIntervals() throws InvalidFieldException {
        Field field = new Field("*/10", FieldType.DAY_OF_MONTH);
        assertEquals("1 11 21 31", field.toString());
        field = new Field("*/20", FieldType.DAY_OF_MONTH);
        assertEquals("1 21", field.toString());
        field = new Field("*/30", FieldType.DAY_OF_MONTH);
        assertEquals("1 31", field.toString());
        field = new Field("*/40", FieldType.DAY_OF_MONTH);
        assertEquals("1", field.toString());


        field = new Field("*/10", FieldType.HOURS);
        assertEquals("0 10 20", field.toString());
        field = new Field("*/15", FieldType.HOURS);
        assertEquals("0 15", field.toString());
        field = new Field("*/20", FieldType.HOURS);
        assertEquals("0 20", field.toString());
        field = new Field("*/23", FieldType.HOURS);
        assertEquals("0 23", field.toString());
        field = new Field("*/24", FieldType.HOURS);
        assertEquals("0", field.toString());

        field = new Field("*", FieldType.DAY_OF_WEEK);
        assertEquals("1 2 3 4 5 6 7", field.toString());

        field = new Field("*", FieldType.MONTH);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", field.toString());
    }

    @Test
    public void testIncorrectIntervals()  {
        parse("*/0", FieldType.DAY_OF_MONTH, "interval is 0");
        parse("*/10/10", FieldType.DAY_OF_MONTH, "has too many intervals");
        parse("*/A", FieldType.DAY_OF_MONTH, "Invalid number 'A'");
        parse("A/A", FieldType.DAY_OF_MONTH, "Invalid number 'A/A'");
        parse("0/0", FieldType.DAY_OF_MONTH, "Invalid number '0/0'");
        parse("0/15", FieldType.DAY_OF_MONTH, "Invalid number '0/15'");
    }
 

    private void parse(String input, FieldType fieldType, String msg) {
        try {
            new Field(input, fieldType);
            fail(input + " should not be a valid " + fieldType);
        } catch (InvalidFieldException e) {
            assertTrue(e.getMessage().contains(msg));
            assertTrue(e.getMessage().contains(fieldType.toString()));
        }
    }


}