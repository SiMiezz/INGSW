package com.example.natour21.Presenter;

import android.util.Log;
import android.util.Patterns;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.rx.RxAmplify;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IUtenteDAO;
import com.example.natour21.Entity.Utente;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerAuthentication;
import com.example.natour21.View.Fragment.SignUpFragment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignUpPresenter {

    private final String TAG = "SignUpPresenter";
    private final SignUpFragment mSignUpFragment;

    public SignUpPresenter(SignUpFragment mSignUpFragment) {
        this.mSignUpFragment = mSignUpFragment;
    }

    public void signUp(String email, String username, String nome, String cognome, String password, String confermaPassword, boolean flag) {
        Log.i(TAG,"Signup in corso.");
        boolean error = false;

        if(email.isEmpty() || !verificaEmail(email)) {
            Log.e(TAG,"Control email: not ok.");
            mSignUpFragment.setError("email");
            error = true;
        }
        if(username.isEmpty() || !username.matches("^[a-zA-Z0-9]{3,16}$")) {
            Log.e(TAG,"Control username: not ok.");
            mSignUpFragment.setError("username");
            error = true;
        }
        if(nome.isEmpty() || !nome.matches("[a-zA-Z\\s]+")) {
            Log.e(TAG,"Control nome: not ok.");
            mSignUpFragment.setError("nome");
            error = true;
        }
        if(cognome.isEmpty() || !cognome.matches("[a-zA-Z\\s]+")) {
            Log.e(TAG,"Control cognome: not ok.");
            mSignUpFragment.setError("cognome");
            error = true;
        }
        if(password.isEmpty() || !verificaPass(password)) {
            Log.e(TAG,"Control password: not ok.");
            mSignUpFragment.setError("password");
            error = true;
        }
        if(password.isEmpty() || confermaPassword.isEmpty() || !password.equals(confermaPassword)) {
            Log.e(TAG,"Control confirm password: not ok.");
            mSignUpFragment.setError("confirmPassword");
            error = true;
        }
        if(!flag){
            Log.e(TAG,"Control TOS: not ok.");
            mSignUpFragment.setError("tos");
            error = true;
        }

        if(!error) {
            Log.i(TAG,"All control are ok.");
            ArrayList<AuthUserAttribute> attributi = new ArrayList<>();
            attributi.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), cognome));
            attributi.add(new AuthUserAttribute(AuthUserAttributeKey.givenName(), nome));
            attributi.add(new AuthUserAttribute(AuthUserAttributeKey.email(), email));
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttributes(attributi)
                    .build();

            Log.i(TAG,"Get all attributes: " + username);
            startSignup(username, password, options);
        }
    }

    private void startSignup(String username, String password, AuthSignUpOptions options) {
        Log.i(TAG,"Signup call.");
        Utente newUser = new Utente();
        newUser.setUsername(username);
        newUser.setCognome(options.getUserAttributes().get(0).getValue());
        newUser.setNome(options.getUserAttributes().get(1).getValue());
        newUser.setEmail(options.getUserAttributes().get(2).getValue());
        newUser.setPhotolnk("");

        RxAmplify.Auth.signUp(username,password,options)
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthSignUpResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthSignUpResult authSignUpResult) {
                        Log.i(TAG, "onSuccess: signUp started.");
                        if(authSignUpResult.isSignUpComplete()) {
                            postNewUser(newUser, new Callback() {
                                @Override
                                public void onSuccess(Object o) {
                                    Log.i(TAG, "onSuccess: signUp Callback started.");
                                    Toasty.success(mSignUpFragment.getActivity(),"Signup completato.",
                                            Toasty.LENGTH_SHORT,true).show();
                                    mSignUpFragment.clear();
                                    mSignUpFragment.returnToLogin();
                                }
                                @Override
                                public void onFailure(Throwable e) {
                                    Log.e(TAG, "onError: signUp started.");
                                    Log.e(TAG, e.toString());
                                    Handler.HandleError(e, mSignUpFragment.getActivity());
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: signUp started.");
                        Log.e(TAG, e.toString());
                        HandlerAuthentication.HandleAuth(e, mSignUpFragment.getActivity());
                    }
                });
    }

    public void postNewUser(Utente u, Callback callback) {
        Log.i(TAG,"Post new user.");
        final IUtenteDAO userDAO = FactoryDAO.getUserDAO();
        userDAO.postUser(u,callback);
    }

    public boolean verificaEmail(String email) throws IllegalArgumentException {
        //Log.i(TAG,"Verify signup email.");
        if(email==null)
            throw new IllegalArgumentException();

        Pattern path = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})");
        Matcher m = path.matcher(email);
        return m.matches();
    }

    public boolean verificaPass(String password) throws IllegalArgumentException {
        //Log.i(TAG,"Verify signup password.");
        if(password==null)
            throw new IllegalArgumentException();

        Pattern path = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$");
        Matcher m = path.matcher(password);
        return m.matches();
    }

}
