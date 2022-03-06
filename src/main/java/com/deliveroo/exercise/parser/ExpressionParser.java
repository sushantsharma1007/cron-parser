package com.deliveroo.exercise.parser;

import static java.lang.String.format;

import com.deliveroo.exercise.exceptions.InvalidFieldException;
import com.deliveroo.exercise.fields.Field;
import com.deliveroo.exercise.types.FieldType;

public class ExpressionParser {

    private String input;
	private Field minutes;
    private Field hours;
    private Field dayOfMonth;
    private Field month;
    private Field dayOfWeek;
    private String command;

    public ExpressionParser(String input) throws InvalidFieldException  {
        this.input=input;
        this.parse();

    }
    
    private void parse() throws InvalidFieldException{
    	String[] expressionFields = this.input.split("\\s+");
        if (expressionFields.length != 6) {
            throw new InvalidFieldException("Expected [minute] [hour] [day of month] [day of week] [command] but got :" + this.input);
        }

        minutes = new Field(expressionFields[0], FieldType.MINUTES);
        hours = new Field(expressionFields[1], FieldType.HOURS);
        dayOfMonth = new Field(expressionFields[2], FieldType.DAY_OF_MONTH);
        month = new Field(expressionFields[3], FieldType.MONTH);
        dayOfWeek = new Field(expressionFields[4], FieldType.DAY_OF_WEEK);
        command = expressionFields[5];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(format("%-14s%s\n", "Minute", minutes.toString()));
        sb.append(format("%-14s%s\n", "Hour", hours.toString()));
        sb.append(format("%-14s%s\n", "Day Of Month", dayOfMonth.toString()));
        sb.append(format("%-14s%s\n", "Month", month.toString()));
        sb.append(format("%-14s%s\n", "Day Of Week", dayOfWeek.toString()));
        sb.append(format("%-14s%s\n", "Command", command));
        return sb.toString();
    }

}
