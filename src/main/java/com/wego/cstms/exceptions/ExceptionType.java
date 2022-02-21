package com.wego.cstms.exceptions;


public enum ExceptionType {
    ENTITY_NOT_FOUND("exception.not.found"),
    DUPLICATE_ENTITY("exception.duplicate");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
