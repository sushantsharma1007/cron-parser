package com.deliveroo.exercise;

import java.util.Arrays;

import com.deliveroo.exercise.exceptions.InvalidFieldException;
import com.deliveroo.exercise.parser.ExpressionParser;

public class Main {


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected expression as string but got multiple inputs :" + Arrays.toString(args));
            return;
        }

        try {

            ExpressionParser expr = new ExpressionParser(args[0]);
            System.out.println(expr);

        } catch (InvalidFieldException invalidExpression) {
            System.err.println(invalidExpression.getMessage());
        }
    }
}
