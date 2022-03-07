package com.deliveroo.exercise.exceptions;

/* Custom exception to be used as consistent exception through out the code
 */
public class InvalidFieldException extends Throwable {
	private static final long serialVersionUID = 2886007132054698732L;

	public InvalidFieldException(String message) {
        super(message);
    }
}
