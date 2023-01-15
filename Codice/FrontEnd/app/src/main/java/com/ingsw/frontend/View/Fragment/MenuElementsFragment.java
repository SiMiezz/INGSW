package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Presenter.MenuElementsPresenter;
import com.ingsw.frontend.View.Adapter.ElementAdapter;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class MenuElementsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MenuElementsFragment() {
        // Required empty public constructor
    }

    private ArrayList<Element> arrayList;
    private ImageButton removeButton;
    private ImageButton addButton;
    private ImageButton backButton;
    private ImageButton confirmButton;

    private RecyclerView myView;
    private ElementAdapter adapter;
    private MenuElementsPresenter menuElementsPresenter = new MenuElementsPresenter(this);

    public static MenuElementsFragment newInstance(String param1, String param2) {
        MenuElementsFragment fragment = new MenuElementsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_elements, container, false);


        arrayList = new ArrayList<>();
        removeButton = rootView.findViewById(R.id.remove_element_button);
        addButton = rootView.findViewById(R.id.add_element_button);
        backButton = rootView.findViewById(R.id.back_element_button);
        confirmButton = rootView.findViewById(R.id.confirm_element_button);
        myView = rootView.findViewById(R.id.elements_listview);

        adapter = new ElementAdapter(getContext(),arrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(linearLayoutManager);
        myView.setAdapter(adapter);


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ElementAdapter.currentLayout == -1){
                    ElementAdapter.currentLayout = -2;
                    adapter.notifyDataSetChanged();
                }

                removeButton.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);

                addButton.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ElementAdapter.currentLayout == -2){
                    ElementAdapter.currentLayout = -1;
                    adapter.notifyDataSetChanged();
                }

                backButton.setVisibility(View.INVISIBLE);
                removeButton.setVisibility(View.VISIBLE);

                confirmButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }

    public ElementAdapter getAdapter(){
        return adapter;
    }

    public void getElementFromClick(Integer idCategory){
        menuElementsPresenter.getByCategoryId(idCategory);
    }

    public void loadElement(ArrayList<Element> elementList){
        adapter.clearList();
        adapter.setArrayList(elementList);
        adapter.notifyDataSetChanged();
    }

    public void setEmptyList(){
        adapter.clearList();
        adapter.setArrayList(new ArrayList<>());
        adapter.notifyDataSetChanged();
    }



}