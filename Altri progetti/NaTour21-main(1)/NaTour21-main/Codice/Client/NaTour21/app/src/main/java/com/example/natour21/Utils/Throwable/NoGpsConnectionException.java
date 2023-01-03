package com.example.natour21.Utils.Throwable;

public class NoGpsConnectionException extends RuntimeException {

    private String cause;

    public NoGpsConnectionException() {}

    public NoGpsConnectionException(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "cause: " + cause;
    }

}
