package com.example.natour21.Presenter;

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthSession;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.rx.RxAmplify;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IUtenteDAO;
import com.example.natour21.Entity.Utente;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerAuthentication;
import com.example.natour21.View.Fragment.LoginFragment;
import com.example.natour21.View.MainActivity;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter {

    private final String TAG = "LoginPresenter";
    private final LoginFragment mLoginFragment;

    public LoginPresenter(LoginFragment mLoginFragment) {
        this.mLoginFragment = mLoginFragment;
    }

    public void doLoginFacebook() {
        Log.i(TAG,"Login Facebook call.");
        mLoginFragment.openPopupLoading();
        RxAmplify.Auth.signInWithSocialWebUI(AuthProvider.facebook(), mLoginFragment.getActivity())
                .retry(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthSignInResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthSignInResult authSignInResult) {
                        Log.i(TAG, "onSuccess: Facebook signIN started.");
                        if (authSignInResult.isSignInComplete()){
                            getSocialInformation(new Callback() {
                                @Override
                                public void onSuccess(Object o) {
                                    Log.i(TAG, "onSuccess: Facebook Callback started.");
                                    Utente user = (Utente) o;
                                    mLoginFragment.closePopupLoading();
                                    ((MainActivity)mLoginFragment.getActivity()).goHomePage(user);
                                }
                                @Override
                                public void onFailure(Throwable e) {
                                    Log.e(TAG, "onError: Facebook Callback started.");
                                    Log.e(TAG, e.toString());
                                    mLoginFragment.closePopupLoading();
                                    if(e instanceof AmazonServiceException) {
                                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                                    }else
                                        Handler.HandleError(e, mLoginFragment.getActivity());
                                }
                            });
                       }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: Facebook signIN started.");
                        Log.e(TAG, e.toString());
                        mLoginFragment.closePopupLoading();
                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                    }
                });
    }

    public void doLoginGoogle() {
        Log.i(TAG,"Login Google call.");
        mLoginFragment.openPopupLoading();
        RxAmplify.Auth.signInWithSocialWebUI(AuthProvider.google(), mLoginFragment.getActivity())
                .retry(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthSignInResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthSignInResult authSignInResult) {
                        Log.i(TAG, "onSuccess: Google signIn started.");
                        if (authSignInResult.isSignInComplete()){
                            getSocialInformation(new Callback() {
                                @Override
                                public void onSuccess(Object o) {
                                    Log.i(TAG, "onSuccess: Google Callback started.");
                                    Utente user = (Utente) o;
                                    mLoginFragment.closePopupLoading();
                                    ((MainActivity)mLoginFragment.getActivity()).goHomePage(user);
                                }
                                @Override
                                public void onFailure(Throwable e) {
                                    Log.e(TAG, "onError: Google Callback started.");
                                    Log.e(TAG, e.toString());
                                    mLoginFragment.closePopupLoading();
                                    if(e instanceof AmazonServiceException) {
                                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                                    }else
                                        Handler.HandleError(e, mLoginFragment.getActivity());
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: Google signIN started.");
                        Log.e(TAG, e.toString());
                        mLoginFragment.closePopupLoading();
                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                    }
                });
    }

    public void loginGuest() {
        Log.i(TAG,"Login guest call.");
        ((MainActivity) mLoginFragment.getActivity()).goHomePage(null);
    }

    public void localLogin(String username, String password) {
        Log.i(TAG,"Login local user call.");
        boolean correct = true;

        if ((username.isEmpty()) || !username.matches("^[a-zA-Z0-9]{3,16}$")) {
            Log.e(TAG,"Control username not ok.");
            mLoginFragment.setUsernameError();
            correct = false;
        }
        if (password.isEmpty()) {
            Log.e(TAG,"Control password not ok.");
            mLoginFragment.setPasswordError();
            correct = false;
        }

        if (correct) {
            Log.i(TAG,"All control are ok.");
            mLoginFragment.openPopupLoading();
            RxAmplify.Auth.signIn(username, password)
                    .retry(1)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<AuthSignInResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {}
                        @Override
                        public void onSuccess(@NonNull AuthSignInResult authSignInResult) {
                            Log.i(TAG, "onSuccess: signIn started.");
                            if (authSignInResult.isSignInComplete()) {
                                String username = RxAmplify.Auth.getCurrentUser().getUsername().toString();
                                getUserInformation(username, new Callback() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Log.i(TAG, "onSuccess: Local signIn Callback started.");
                                        Utente user = (Utente) o;
                                        mLoginFragment.closePopupLoading();
                                        ((MainActivity) mLoginFragment.getActivity()).goHomePage(user);
                                    }
                                    @Override
                                    public void onFailure(Throwable e) {
                                        Log.e(TAG, "onError: Local signIn Callback started.");
                                        Log.e(TAG, e.toString());
                                        mLoginFragment.closePopupLoading();
                                        Handler.HandleError(e, mLoginFragment.getActivity());
                                    }
                                });
                            }
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: signIn started.");
                            Log.e(TAG, e.toString());
                            mLoginFragment.closePopupLoading();
                            HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());

                            if (e instanceof AuthException.UserNotConfirmedException)
                                resendCode(username);
                        }
                    });
        }
    }

    private void resendCode(String username) {
        Log.i(TAG,"Resend code.");
        RxAmplify.Auth.resendSignUpCode(username)
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthSignUpResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthSignUpResult authSignUpResult) {
                        Log.i(TAG,"onSuccess: resend code started.");
                        if(authSignUpResult.isSignUpComplete()) {
                            Toasty.info(mLoginFragment.getActivity(),"Codice di verifica inviato.",
                                    Toasty.LENGTH_SHORT,true).show();
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError: resend code started.");
                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                    }
                });
    }

    public void getUserInformation(String username, Callback callback) {
        Log.i(TAG,"Getting user information.");
        final IUtenteDAO userDAO = FactoryDAO.getUserDAO();
        userDAO.getUser(username, callback);
    }

    public void getSocialInformation(Callback callback) {
        Log.i(TAG,"Getting social user information.");
        final IUtenteDAO userDAO = FactoryDAO.getUserDAO();
        userDAO.getUserSocial(callback);
    }

    public void isLogged() {
        Log.i(TAG,"IsLogged user checking.");
        RxAmplify.Auth.fetchAuthSession()
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AuthSession>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull AuthSession authSession) {
                        Log.i(TAG, "onSuccess: isLogged started.");
                        if (authSession.isSignedIn()) {
                            String username = RxAmplify.Auth.getCurrentUser().getUsername().toString();
                            getUserInformation(username, new Callback() {
                                @Override
                                public void onSuccess(Object o) {
                                    Log.i(TAG, "onSuccess: isLogged Callback started.");
                                    Utente user = (Utente) o;
                                    ((MainActivity)mLoginFragment.getActivity()).goHomePage(user);
                                }
                                @Override
                                public void onFailure(Throwable e) {
                                    Log.e(TAG, "onError: isLogged Callback started.");
                                    Log.e(TAG, e.toString());
                                    Handler.HandleError(e, mLoginFragment.getActivity());
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: isLogged started.");
                        Log.e(TAG, e.toString());
                        HandlerAuthentication.HandleAuth(e, mLoginFragment.getActivity());
                    }
                });
    }

    public LoginFragment getmLoginFragment() {
        return mLoginFragment;
    }
}
