package com.example.natour21.Utils.Throwable;

public class NoAuthorizedGuestException extends RuntimeException {

    private String cause;

    public NoAuthorizedGuestException() {}

    public NoAuthorizedGuestException(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "cause: " + cause;
    }

}
