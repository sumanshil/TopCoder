package com.functionalprogramming.validation;

public class GenericValidationResult {
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    private GenericValidationResult(boolean valid) {
        this.valid = valid;
    }

    public static GenericValidationResult ok() {
        return new GenericValidationResult(true);
    }

    public static GenericValidationResult fail() {
        return new GenericValidationResult(false);
    }
}
