package com.example.natour21.Presenter;

import android.util.Log;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ICompilationDAO;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Utils.CollezioniAdapter;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.ItinerariCollectionAdapter;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Fragment.CollezioniFragment;
import com.example.natour21.View.Other.CollezioneActivity;
import com.example.natour21.View.Other.NuovaCollezioneActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class CollezioniPresenter {

    private final String TAG = "CollezioniPresenter";
    private final CollezioniFragment mCollezioniFragment;
    private final CollezioneActivity mCollezioneActivity;
    private final NuovaCollezioneActivity mNuovaCollezioneActivity;

    public CollezioniPresenter(CollezioniFragment collezioniFragment) {
        this.mCollezioniFragment = collezioniFragment;
        this.mCollezioneActivity = null;
        this.mNuovaCollezioneActivity = null;
    }

    public CollezioniPresenter(CollezioneActivity collezioneActivity) {
        this.mCollezioniFragment = null;
        this.mCollezioneActivity = collezioneActivity;
        this.mNuovaCollezioneActivity = null;
    }

    public CollezioniPresenter(NuovaCollezioneActivity nuovaCollezioneActivity) {
        this.mCollezioniFragment = null;
        this.mCollezioneActivity = null;
        this.mNuovaCollezioneActivity = nuovaCollezioneActivity;
    }

    public void getCollezioniUsername(String username) {
        Log.i(TAG, "Getting collection of: "+username);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        compilationDAO.getCompilationByUsername(username, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getCollezioniUsername started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Compilation> list = (ArrayList<Compilation>) o;
                    assert mCollezioniFragment != null;
                    mCollezioniFragment.upload(list);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getCollezioniUsername started.");
                Log.e(TAG,e.getLocalizedMessage());
                Handler.HandleError(e,mCollezioniFragment.getActivity());
            }
        });
    }

    public void getColectionInfo(long id_compilation) {
        Log.i(TAG, "Getting collecition info: "+id_compilation);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        compilationDAO.getCompilationById(id_compilation, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getColectionInfo started.");
                try {
                    @SuppressWarnings("unchecked")
                    Compilation c = (Compilation) o;
                    assert mCollezioneActivity != null;
                    mCollezioneActivity.setView(c.getTitolo(),c.getDescrizione());
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getColectionInfo started.");
                Log.e(TAG,e.getLocalizedMessage());
                Handler.HandleError(e,mCollezioniFragment.getActivity());
            }
        });
    }

    public void getItinerariCollezione(long id_compilation) {
        Log.i(TAG, "Getting itinerario in: "+id_compilation);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        if(id_compilation!=-1) {
            compilationDAO.getItinerariInCompilation(id_compilation, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Log.i(TAG,"onSuccess: getItinerariCollezione started.");
                    try {
                        @SuppressWarnings("unchecked")
                        ArrayList<Itinerario> itinerarios = (ArrayList<Itinerario>) o;
                        assert mCollezioneActivity != null;
                        mCollezioneActivity.upload(itinerarios);
                    }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                    }
                }
                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG,"onError: getItinerariCollezione started.");
                    Log.e(TAG,e.getLocalizedMessage());
                    Handler.HandleError(e,mCollezioneActivity);
                }
            });
        }else{
            Toasty.error(mCollezioneActivity, "Caricamento della collezione fallito, riprovare.",
                    Toasty.LENGTH_SHORT, true).show();
            mCollezioneActivity.onBackPressed();
        }
    }

    public void saveCompilation(String nome, String descrizione) {
        Log.i(TAG, "Saving compilation: "+nome);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        if(verifica(nome,descrizione)) {
            Log.i(TAG,"Posting new compilation...");
            Compilation newCompilation = new Compilation();
            newCompilation.setTitolo(nome);
            newCompilation.setDescrizione(descrizione);
            newCompilation.setId_utente(LocalUser.getLocalUser(mNuovaCollezioneActivity).getUsername());
            compilationDAO.postCompilation(newCompilation, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Log.i(TAG,"onSuccess: saveCompilation started.");
                    Toasty.success(mNuovaCollezioneActivity, "Nuova collezione creata.",
                            Toasty.LENGTH_SHORT, true).show();
                    mNuovaCollezioneActivity.clear();
                    mNuovaCollezioneActivity.onBackPressed();
                }
                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG, "onError: saveCompilation started.");
                    Log.e(TAG, e.getLocalizedMessage());
                    Handler.HandleError(e, mNuovaCollezioneActivity);
                }
            });
        }
    }

    public void deleteItinerarioCompilation(long id_itinerario, long id_compilation,
                                            ItinerariCollectionAdapter itinerariCollectionAdapter,
                                            int adapterPosition) {
        Log.i(TAG, "Deleteing itinerario "+id_compilation+" in compilation "+id_compilation);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        compilationDAO.deleteItinerarioInCompilation(id_compilation, id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: deleteItinerarioCompilation started.");
                Toasty.success(mCollezioneActivity, "Collezione eliminata.",
                        Toasty.LENGTH_SHORT, true).show();
                itinerariCollectionAdapter.remove(adapterPosition);
                itinerariCollectionAdapter.notifyItemRemoved(adapterPosition);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onError: deleteItinerarioCompilation started.");
                Log.e(TAG, e.getLocalizedMessage());
                Handler.HandleError(e, mCollezioniFragment.getActivity());
            }
        });
    }

    public void deleteCollection(long id_compilation,
                                 CollezioniAdapter collezioniAdapter,
                                 int adapterPosition) {
        Log.i(TAG, "Delete compilation with id: "+ id_compilation);
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        compilationDAO.deleteCompilation(id_compilation, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: deleteCollection started.");
                Toasty.success(mCollezioniFragment.getActivity(), "Collezione eliminata.",
                        Toasty.LENGTH_SHORT, true).show();
                collezioniAdapter.remove(adapterPosition);
                collezioniAdapter.notifyItemRemoved(adapterPosition);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onError: deleteCollection started.");
                Log.e(TAG, e.getLocalizedMessage());
                Handler.HandleError(e, mCollezioniFragment.getActivity());
            }
        });
    }

    private boolean verifica(String nome, String descrizione) {
        Log.i(TAG,"Verifica campi collezione.");
        if (nome.isEmpty()) {
            mNuovaCollezioneActivity.setNomeCollezioneError("Il nome non può essere vuoto.");
            return false;
        }
        if (descrizione.isEmpty()) {
            mNuovaCollezioneActivity.setDescrizioneCollezioneError("La descrizione non può essere vuota.");
            return false;
        }
        if(nome.length()>25) {
            mNuovaCollezioneActivity.setNomeCollezioneError("Lunghezza massima: 25.");
            return false;
        }
        if(descrizione.length()>250) {
            mNuovaCollezioneActivity.setDescrizioneCollezioneError("Lunghezza massima: 250.");
            return false;
        }
        return true;
    }
}