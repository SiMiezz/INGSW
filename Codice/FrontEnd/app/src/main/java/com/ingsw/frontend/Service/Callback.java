package com.ingsw.frontend.Service;

public interface Callback {
    void returnResult(Object o);
    void returnError(Throwable e);
}
