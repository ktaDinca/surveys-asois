package com.springapp.mvc.service.exception;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 15/Dec/2014
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
