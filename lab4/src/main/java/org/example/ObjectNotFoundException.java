package org.example;

/**
 * Custom exception thrown when an object is not found in a collection.
 */
public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
