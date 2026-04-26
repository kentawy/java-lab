package org.example;

/**
 * Custom exception for invalid field values during object creation or update.
 */
public class InvalidFieldValueException extends Exception {
    public InvalidFieldValueException(String message) {
        super(message);
    }
}
