package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.AllergenService;
import com.ingsw.frontend.View.Dialog.ElementCreateDialog;

import java.util.ArrayList;

public class AllergenPresenter {
    private AllergenService allergenService;
    private ElementCreateDialog elementCreateDialog;

    public AllergenPresenter(ElementCreateDialog elementCreateDialog){
        this.elementCreateDialog = elementCreateDialog;
        this.allergenService = new AllergenService();
    }

    public void getAllAllergens(){
        allergenService.getAll(new Callback() {

            @Override
            public void returnResult(Object o) {
                ArrayList<Allergen> allergenArrayList = (ArrayList<Allergen>) o;
                elementCreateDialog.loadAllergens(allergenArrayList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        });

    }
}
