package com.example.natour21.Presenter;

import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.rx.RxAmplify;
import com.bumptech.glide.Glide;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.R;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerAuthentication;
import com.example.natour21.Utils.Handler.HandlerStorage;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.Utils.Other.UploadS3;
import com.example.natour21.View.Fragment.HomePageFragment;
import com.example.natour21.View.Other.HomePageActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenter {

    private final String TAG = "HomePagePresenter";
    private final HomePageFragment mHomePageFragment;
    private final HomePageActivity mHomePageActivity;

    public HomePagePresenter(HomePageFragment mHomePageFragment) {
        this.mHomePageFragment = mHomePageFragment;
        this.mHomePageActivity = null;
    }

    public HomePagePresenter(HomePageActivity mHomePageActivity) {
        this.mHomePageFragment = null;
        this.mHomePageActivity = mHomePageActivity;
    }

    public HomePagePresenter() {
        this.mHomePageFragment = null;
        this.mHomePageActivity = null;
    }

    public void getItinerari() {
        Log.i(TAG, "GetAll itinerario.");
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.getAllRecent(new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getItinerari started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Itinerario> list = (ArrayList<Itinerario>) o;
                    assert mHomePageFragment != null;
                    mHomePageFragment.upload(list);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getItinerari started.");
                Log.e(TAG,e.getLocalizedMessage());
                assert mHomePageFragment != null;
                Handler.HandleError(e,mHomePageFragment.getActivity());
            }
        });
    }

    public void getItinerariByName(String name) {
        Log.i(TAG, "Search by name.");
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.getItinerariByName(name, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getItinerariByName started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Itinerario> list = (ArrayList<Itinerario>) o;
                    assert mHomePageFragment != null;
                    mHomePageFragment.upload(list);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getItinerariByName started.");
                Log.e(TAG,e.getLocalizedMessage());
                assert mHomePageFragment != null;
                Handler.HandleError(e,mHomePageFragment.getActivity());
            }
        });
    }

    public void getItinerariByFilter(Map<String,String> filter) {
        Log.i(TAG, "Search by Filter.");
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.getItinerarioByFilter(filter, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getItinerariByFilter started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Itinerario> list = (ArrayList<Itinerario>) o;
                    assert mHomePageFragment != null;
                    mHomePageFragment.upload(list);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getItinerariByFilter started.");
                Log.e(TAG,e.getLocalizedMessage());
                assert mHomePageFragment != null;
                Handler.HandleError(e,mHomePageFragment.getActivity());
            }
        });
    }

    public void setPhotoHomePage(ImageView imageView, String key) {
        Log.i(TAG,"Setting photo homepage.");
        UploadS3.getUrl(key, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: setPhotoHomePage started.");
                String url = (String) o;
                assert mHomePageActivity != null;
                Glide.with(mHomePageActivity)
                        .load(url)
                        .error(R.drawable.avatar)
                        .circleCrop()
                        .dontAnimate()
                        .into(imageView);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: setPhotoHomePage started.");
                Log.e(TAG,e.getLocalizedMessage());
                assert mHomePageActivity != null;
                HandlerStorage.HandleStore(e,mHomePageActivity);
            }
        });
    }

    public Map<String,String> getFilerMap(String titolo, String puntoinizio, String puntofine,
                                          String durata, String lunghezza, String difficulty,
                                          String accessoDisabili, String areageografica)
            throws IllegalArgumentException {
        //Log.i(TAG,"Setting query mapping filter.");
        Map<String,String> filterMap = new HashMap<>();
        String url = "?";
        if(StringUtils.isNumeric(titolo))
            throw new IllegalArgumentException();
        filterMap.put("titolo",titolo);

        if(StringUtils.isNumeric(puntoinizio))
            throw new IllegalArgumentException();
        filterMap.put("puntoinizio",puntoinizio);

        if(StringUtils.isNumeric(puntofine))
            throw new IllegalArgumentException();
        filterMap.put("puntofine",puntofine);


        Pattern path = Pattern.compile("^(\\d\\d:[0-5]\\d:[0-5]\\d)");
        Matcher m = path.matcher(durata);
        if(durata.isEmpty())
            durata="23:59:00";
        else if(!m.matches())
            throw new IllegalArgumentException();
        filterMap.put("durata",durata);

        if(lunghezza!=null) {
            if(lunghezza.isEmpty())
                lunghezza = "100.0";
            else if(!StringUtils.isNumeric(lunghezza) && Double.parseDouble(lunghezza)<=0)
                throw new IllegalArgumentException();
        }
        filterMap.put("length",lunghezza);

        if(difficulty.equals("Qualsiasi"))
            difficulty="";
        filterMap.put("difficulty",difficulty);

        if(!(accessoDisabili.equals("true") || accessoDisabili.equals("false")))
            throw new IllegalArgumentException();
        filterMap.put("accessodisabili",accessoDisabili);

        if(StringUtils.isNumeric(areageografica))
            throw new IllegalArgumentException();
        filterMap.put("areageografica",areageografica);

        url = url
                +"titolo="+titolo
                +"&puntoinizio="+puntoinizio
                +"&puntofine="+puntofine
                +"&durata="+durata
                +"&length="+lunghezza
                +"&difficulty="+difficulty
                +"&accessodisabili="+accessoDisabili
                +"&areageografica="+areageografica;
        filterMap.put("url",url);
        return filterMap;
    }

    public void signout() {
        Log.i(TAG,"Signout:"+LocalUser.getLocalUsername(mHomePageActivity));
        assert mHomePageActivity != null;
        Toasty.warning(mHomePageActivity,"Logout in corso...",
                Toasty.LENGTH_SHORT,true).show();
        if(LocalUser.isGuest(mHomePageActivity)) {
            mHomePageActivity.onSignOutPressed();
        }else{
            RxAmplify.Auth.signOut()
                    .observeOn(Schedulers.newThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {}
                        @Override
                        public void onComplete() {
                            Log.i(TAG,"onComplete: signout started.");
                            LocalUser.deleteLocalUser(mHomePageActivity);
                            mHomePageActivity.onSignOutPressed();
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG,"onError: signout started.");
                            Log.e(TAG,e.getLocalizedMessage());
                            HandlerAuthentication.HandleAuth(e,mHomePageActivity);
                        }
                    });
        }
    }
}
