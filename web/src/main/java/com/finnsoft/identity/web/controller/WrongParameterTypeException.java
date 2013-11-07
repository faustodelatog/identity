/**
 * 
 */
package com.finnsoft.identity.web.controller;

/**
 * @author faustodelatog
 * 
 */
public class WrongParameterTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongParameterTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongParameterTypeException(String message) {
		super(message);
	}

	public WrongParameterTypeException(Throwable cause) {
		super(cause);
	}

}
