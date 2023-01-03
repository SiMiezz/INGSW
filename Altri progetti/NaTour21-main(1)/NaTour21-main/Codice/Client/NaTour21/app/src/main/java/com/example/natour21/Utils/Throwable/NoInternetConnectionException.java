package com.example.natour21.Utils.Throwable;

public class NoInternetConnectionException extends RuntimeException {

    private String cause;

    public NoInternetConnectionException() {}

    public NoInternetConnectionException(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "cause: " + cause;
    }

}
