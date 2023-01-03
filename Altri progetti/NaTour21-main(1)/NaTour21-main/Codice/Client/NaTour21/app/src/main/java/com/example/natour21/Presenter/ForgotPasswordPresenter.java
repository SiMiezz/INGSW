package com.example.natour21.Presenter;

import android.util.Log;

import com.amplifyframework.auth.result.AuthResetPasswordResult;
import com.amplifyframework.rx.RxAmplify;
import com.example.natour21.Utils.Handler.HandlerAuthentication;
import com.example.natour21.View.Fragment.ForgotPasswordFragment;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ForgotPasswordPresenter {

    private final String TAG = "ForgotPasswordPresenter";
    private final ForgotPasswordFragment mForgotPasswordFragment;

    public ForgotPasswordPresenter(ForgotPasswordFragment mForgotPasswordFragment) {
        this.mForgotPasswordFragment = mForgotPasswordFragment;
    }

    public void recoveryAccount(String username) {
        Log.i(TAG,"Recovery personal account: "+username);
        if(!verificaUsername(username))
            sendCode(username);
        else
            mForgotPasswordFragment.setUsernameError();
    }

    private void sendCode(String username) {
        Log.i(TAG,"Sending new code.");
        Log.i(TAG,"Send code to " + username);
        RxAmplify.Auth.resetPassword(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthResetPasswordResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthResetPasswordResult authResetPasswordResult) {
                        Log.i(TAG, "onSuccess: SendCode started.");
                        Toasty.info(mForgotPasswordFragment.getActivity(),"Codice di reset inviato all'email.",
                                Toasty.LENGTH_SHORT,true).show();
                        mForgotPasswordFragment.showRecoveryForm();
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: SendCode started.");
                        Log.e(TAG, e.toString());
                        HandlerAuthentication.HandleAuth(e, mForgotPasswordFragment.getActivity());
                    }
                });
    }

    public void setNewPassword(String code, String password) {
        Log.i(TAG,"Setting new Password.");
        if (verificaPassword(password))
            mForgotPasswordFragment.setNewPasswordError();
        else {
            RxAmplify.Auth.confirmResetPassword(password,code)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {}
                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onSuccess: setNewPassword started.");
                            Toasty.success(mForgotPasswordFragment.getActivity(),"Password resettata con successo!",
                                    Toasty.LENGTH_SHORT,true).show();
                            mForgotPasswordFragment.hideRecoveryForm();
                            mForgotPasswordFragment.returnToLogin();
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: setNewPassword started.");
                            Log.e(TAG, e.toString());
                            HandlerAuthentication.HandleAuth(e, mForgotPasswordFragment.getActivity());
                        }
                    });
        }
    }

    private boolean verificaUsername(String username) {
        Log.i(TAG,"Verifica username forgot.");
        return (username.isEmpty()) || (!username.matches("^[a-zA-Z0-9]{3,16}$"));
    }

    private boolean verificaPassword(String password) {
        Log.i(TAG,"Verifica password forgot.");
        return (password.isEmpty()) || (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$"));
    }
}
