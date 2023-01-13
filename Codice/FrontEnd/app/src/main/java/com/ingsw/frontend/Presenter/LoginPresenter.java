package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.UserService;
import com.ingsw.frontend.View.Fragment.LoginFragment;

import java.util.ArrayList;

public class LoginPresenter {

    private LoginFragment loginFragment;
    private UserService userService;

    // CONSTRUCTOR

    public LoginPresenter(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        userService = new UserService();
    }

    // GETTER AND SETTER

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
    }

    public void getByEmailAndPassword(String email, String pwd){
        userService.getByEmailAndPassword(new Callback(){
            @Override
            public void returnResult(Object o) {
                Boolean res = (Boolean) o;

                loginFragment.login(res);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },email,pwd);
    }
}
