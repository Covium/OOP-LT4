package com.company;

// Custom error.
public class NegativeInput extends Exception {
    public NegativeInput(String message) { super(message); }
}
