package com.example.natour21.Presenter;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.rx.RxAmplify;
import com.bumptech.glide.Glide;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IUtenteDAO;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Entity.Utente;
import com.example.natour21.R;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerStorage;
import com.example.natour21.Utils.ItinerariPersonaliAdapter;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.Utils.Other.UploadS3;
import com.example.natour21.View.Fragment.ProfiloFragment;
import com.example.natour21.View.Other.HomePageActivity;
import com.example.natour21.View.Other.TracciatiPersonaliActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ProfiloPresenter {

    private final String TAG = "ProfiloPresenter";
    private final ProfiloFragment mProfiloFragment;
    private final TracciatiPersonaliActivity mTracciatiPersonaliActivity;

    public ProfiloPresenter(ProfiloFragment mProfiloFragment) {
        this.mProfiloFragment = mProfiloFragment;
        this.mTracciatiPersonaliActivity = null;
    }

    public ProfiloPresenter(TracciatiPersonaliActivity mTracciatiPersonaliActivity) {
        this.mProfiloFragment = null;
        this.mTracciatiPersonaliActivity = mTracciatiPersonaliActivity;
    }

    public void uploadProfile(Uri uri) {
        Log.i(TAG, "Uploading current profile.");
        String photoLnk = "photo-"+LocalUser.getLocalUsername(mProfiloFragment.getActivity());
        UploadS3.upload(uri, photoLnk,
                mProfiloFragment.getActivity(), new Callback() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.i(TAG,"onSuccess: uploadProfile started.");
                        Utente u = LocalUser.getLocalUser(mProfiloFragment.getActivity());
                        u.setPhotolnk(photoLnk);
                        final IUtenteDAO userDAO = FactoryDAO.getUserDAO();
                        userDAO.putUser(u, new Callback() {
                            @Override
                            public void onSuccess(Object o) {
                                Log.i(TAG,"onSuccess: putUser started.");
                                Toasty.success(mProfiloFragment.getActivity(),"Foto caricata con successo.",
                                        Toasty.LENGTH_SHORT,true).show();
                                LocalUser.setLocalUser(mProfiloFragment.getActivity(),u);
                                mProfiloFragment.setProfiloFragment();
                                ((HomePageActivity)mProfiloFragment.getActivity()).uploadUser();
                            }
                            @Override
                            public void onFailure(Throwable e) {
                                Log.e(TAG,"onError: putUser started.");
                                Log.e(TAG,e.getLocalizedMessage());
                                Handler.HandleError(e,mProfiloFragment.getActivity());
                            }
                        });
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG,"onError: putUser started.");
                        Log.e(TAG,e.getLocalizedMessage());
                        HandlerStorage.HandleStore(e,mProfiloFragment.getActivity());
                    }
                });
    }

    public void setPhotoProfilePage(ImageView imageView, String key) {
        Log.i(TAG, "Setting current Profile image.");
        UploadS3.getUrl(key, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.e(TAG,"onSuccess: setPhotoProfilePage started.");
                String url = (String) o;
                Glide.with(mProfiloFragment)
                        .load(url)
                        .error(R.drawable.avatar)
                        .circleCrop()
                        .dontAnimate()
                        .into(imageView);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: setPhotoProfilePage started.");
                Log.e(TAG,e.getLocalizedMessage());
                HandlerStorage.HandleStore(e,mProfiloFragment.getActivity());
            }
        });
    }

    public void getItinerariLocalUser(String username) {
        Log.i(TAG, "Caricamento itinerari utente loggato: "+LocalUser.getLocalUsername(mTracciatiPersonaliActivity));
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.getItinerarioByUsername(username, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getItinerariLocalUser started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Itinerario> list = (ArrayList<Itinerario>) o;
                    assert mTracciatiPersonaliActivity != null;
                    mTracciatiPersonaliActivity.upload(list);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getItinerariLocalUser started.");
                Log.e(TAG,e.getLocalizedMessage());
                Handler.HandleError(e,mTracciatiPersonaliActivity);
            }
        });
    }

    public void deleteItinerario(long id_itinerario, ItinerariPersonaliAdapter itinerariPersonaliAdapter, int adapterPosition) {
        Log.i(TAG, "Delete itinerario.");
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.removeItinerario(id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: deleteItinerario started.");
                Toasty.success(mTracciatiPersonaliActivity, "Itinerario eliminato.",
                        Toasty.LENGTH_SHORT, true).show();
                itinerariPersonaliAdapter.remove(adapterPosition);
                itinerariPersonaliAdapter.notifyItemRemoved(adapterPosition);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onError: deleteItinerario started.");
                Log.e(TAG, e.getLocalizedMessage());
                Handler.HandleError(e, mTracciatiPersonaliActivity);
            }
        });
    }

    public void deleteAccount() {
        Log.i(TAG,"Delete current user: "+LocalUser.getLocalUsername(mProfiloFragment.getActivity()));
        final IUtenteDAO utenteDAO = FactoryDAO.getUserDAO();
        utenteDAO.deleteUser(LocalUser.getLocalUsername(mProfiloFragment.getActivity()), new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: deleteAccout started.");
                LocalUser.deleteLocalUser(mProfiloFragment.getActivity());
                Toasty.success(mProfiloFragment.getActivity(), "Utente eliminato.",
                        Toasty.LENGTH_SHORT, true).show();
                RxAmplify.Auth.signOut().subscribe();
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onError: deleteItinerario started.");
                Log.e(TAG, e.getLocalizedMessage());
                Handler.HandleError(e, mProfiloFragment.getActivity());
            }
        });
    }

}
