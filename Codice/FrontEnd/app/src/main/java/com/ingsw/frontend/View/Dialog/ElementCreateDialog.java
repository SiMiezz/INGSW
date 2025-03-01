package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Presenter.AllergenPresenter;
import com.ingsw.frontend.Presenter.OpenFoodPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.AllergenAdapter;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;

import java.util.ArrayList;

public class ElementCreateDialog extends AppCompatDialogFragment {

    private Button btnCreateOk;
    private Button btnCreateCancel;
    private AutoCompleteTextView editTextname;
    private EditText editTextTranslateName;
    private EditText editTextdescription;
    private EditText editTextTranslateDescription;
    private EditText editTextprice;
    private CheckBox checkBoxprepackaged;

    private TextView viewTranslateName;
    private TextView viewTranslateDescription;
    private Spinner spinner;

    private ArrayList<Allergen> allergenArrayList;
    private ArrayList<String> productNameList;

    private ElementCreateDialogListener elementCreateDialogListener;
    private MenuElementsFragment menuElementsFragment;
    private AllergenPresenter allergenPresenter = new AllergenPresenter(this);
    private OpenFoodPresenter openFoodPresenter = new OpenFoodPresenter(this);

    private AllergenAdapter allergenAdapter;

    public ElementCreateDialog(MenuElementsFragment menuElementsFragment) {
        this.menuElementsFragment = menuElementsFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.element_create_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .show();

        btnCreateOk = (Button) view.findViewById(R.id.elementCreateOk_btn);
        btnCreateCancel = (Button) view.findViewById(R.id.elementCreateCancel_btn);

        btnCreateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextname.getText().toString().isEmpty() || editTextprice.getText().toString().isEmpty())){
                    String name = editTextname.getText().toString();
                    String translateName = editTextTranslateName.getText().toString();
                    String description = editTextdescription.getText().toString();
                    String translateDescription = editTextTranslateDescription.getText().toString();
                    Boolean prepackaged = checkBoxprepackaged.isChecked();

                    String priceString = editTextprice.getText().toString();
                    char[] checkPrice = priceString.toCharArray();
                    StringBuilder stringBuilder = new StringBuilder();
                    for(char c : checkPrice){
                        if(Character.isDigit(c))
                            stringBuilder.append(c);
                        else if(c == ',')
                            stringBuilder.append('.');
                        else if(c == '.')
                            stringBuilder.append(c);

                    }

                    Double price = Double.parseDouble(stringBuilder.toString());

                    Element element = new Element(name,translateName,description,translateDescription,price,prepackaged,menuElementsFragment.getCategoryId(),allergenAdapter.getSelectedAllergenArrayList());

                    elementCreateDialogListener.createElement(element,menuElementsFragment);
                    dialog.dismiss();
                }
            }
        });

        btnCreateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        allergenArrayList = new ArrayList<>();
        productNameList = new ArrayList<>();
        editTextname = view.findViewById(R.id.acv_element_name);
        editTextTranslateName = view.findViewById(R.id.edit_element_translatename);
        editTextdescription = view.findViewById(R.id.edit_element_description);
        editTextTranslateDescription = view.findViewById(R.id.edit_element_translatedescription);
        editTextprice = view.findViewById(R.id.edit_element_price);
        checkBoxprepackaged = view.findViewById(R.id.checkbox_prepackaged);
        spinner = view.findViewById(R.id.allergen_spinner);

        viewTranslateName = view.findViewById(R.id.view_translatename);
        viewTranslateDescription = view.findViewById(R.id.view_translatedescription);

        if(!menuElementsFragment.getRestaurant().isTouristic()){
            editTextTranslateName.setBackgroundResource(R.drawable.shape_edit_text_popuplock);
            editTextTranslateDescription.setBackgroundResource(R.drawable.shape_edit_text_popuplock);
            editTextTranslateDescription.setEnabled(false);
            editTextTranslateName.setEnabled(false);
        }

        allergenPresenter.getAllAllergens();
        allergenAdapter = new AllergenAdapter(getContext(), 0, allergenArrayList);
        spinner.setAdapter(allergenAdapter);

        editTextname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                openFoodPresenter.getProductList(editTextname.getText().toString());

                ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,productNameList);
                nameAdapter.getFilter().filter(editTextname.getText(), editTextname);
                editTextname.setAdapter(nameAdapter);

                nameAdapter.notifyDataSetChanged();

                editTextname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openFoodPresenter.getDescription(editTextname.getText().toString());
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editTextname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(menuElementsFragment.getRestaurant().isTouristic()){
                        TranslatorOptions options =
                                new TranslatorOptions.Builder()
                                        .setSourceLanguage(TranslateLanguage.ITALIAN)
                                        .setTargetLanguage(TranslateLanguage.ENGLISH)
                                        .build();

                        final Translator itaEngTranslator = Translation.getClient(options);
                        getLifecycle().addObserver(itaEngTranslator);

                        DownloadConditions conditions = new DownloadConditions.Builder()
                                .requireWifi()
                                .build();

                        itaEngTranslator.downloadModelIfNeeded(conditions)
                                .addOnSuccessListener(
                                        new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                if(!editTextname.getText().toString().isEmpty()){
                                                    itaEngTranslator.translate(editTextname.getText().toString())
                                                            .addOnSuccessListener(
                                                                    new OnSuccessListener() {
                                                                        @Override
                                                                        public void onSuccess(Object o) {
                                                                            String translatedName = (String) o;

                                                                            editTextTranslateName.setText(translatedName);
                                                                        }
                                                                    });
                                                }
                                                else{
                                                    editTextTranslateName.setText("");
                                                }

                                                editTextdescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                    @Override
                                                    public void onFocusChange(View v, boolean hasFocus) {
                                                        if(!hasFocus){
                                                            if(!editTextdescription.getText().toString().isEmpty()){
                                                                itaEngTranslator.translate(editTextdescription.getText().toString())
                                                                        .addOnSuccessListener(
                                                                                new OnSuccessListener() {
                                                                                    @Override
                                                                                    public void onSuccess(Object o) {
                                                                                        String translatedDescription = (String) o;

                                                                                        editTextTranslateDescription.setText(translatedDescription);
                                                                                    }
                                                                                });
                                                            }
                                                            else{
                                                                editTextTranslateDescription.setText("");
                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                        });
                    }
                }
            }
        });

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            elementCreateDialogListener = (ElementCreateDialogListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=800;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface ElementCreateDialogListener {
        void createElement(Element element,MenuElementsFragment menuElementsFragment);
    }

    public void loadAllergens(ArrayList<Allergen> allergens) {
        allergenArrayList.addAll(allergens);
    }

    public void loadProductNames(ArrayList<String> productNames) {
        productNameList.addAll(productNames);
    }

    public void loadDescription(String productDescription) {
        editTextdescription.setText(productDescription);
    }
}
