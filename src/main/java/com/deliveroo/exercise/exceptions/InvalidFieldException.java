package com.deliveroo.exercise.exceptions;

public class InvalidFieldException extends Throwable {
	private static final long serialVersionUID = 2886007132054698732L;

	public InvalidFieldException(String message) {
        super(message);
    }
}
