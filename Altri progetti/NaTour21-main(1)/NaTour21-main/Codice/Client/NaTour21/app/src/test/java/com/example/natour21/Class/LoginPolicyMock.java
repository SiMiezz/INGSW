package com.example.natour21.Class;

import com.example.natour21.Presenter.SignUpPresenter;

public class LoginPolicyMock {

    private SignUpPresenter test;

    public LoginPolicyMock() {
         test = new SignUpPresenter(null);
    }

    public boolean isValid(String email, String password) throws IllegalArgumentException {
        return test.verificaEmail(email) && test.verificaPass(password);
    }
}
