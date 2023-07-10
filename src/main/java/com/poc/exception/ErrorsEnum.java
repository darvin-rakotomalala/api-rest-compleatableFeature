package com.poc.exception;

public enum ErrorsEnum {

    /**
     * ERR_MCS_POC
     */

    ERR_MCS_TUTORIAL_OBJECT_EMPTY("Error occurred - customer object shouldn't be NULL or EMPTY"),
    ERR_MCS_CF_NOT_FOUND("Error occurred - no customer found with this id");

    private final String errorMessage;

    private ErrorsEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return " errorMessage : " + errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
