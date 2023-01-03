package com.example.natour21.Utils.Throwable;

public class UnsupportedDAOException extends RuntimeException {

    private String cause;

    public UnsupportedDAOException() {}

    public UnsupportedDAOException(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "cause: " + cause;
    }

}
