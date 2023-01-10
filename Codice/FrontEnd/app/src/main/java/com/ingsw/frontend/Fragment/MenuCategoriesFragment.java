package com.ingsw.frontend.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ingsw.frontend.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuCategoriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;


    public MenuCategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuCategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuCategoriesFragment newInstance(String param1, String param2) {
        MenuCategoriesFragment fragment = new MenuCategoriesFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_menu_categories, container, false);

//        RecyclerView recyclerView = rootView.findViewById(R.id.categories_listview);
        ListView listView = rootView.findViewById(R.id.categories_listview);
        FloatingActionButton addButton = rootView.findViewById(R.id.add_button_listview);
        FloatingActionButton removeButton = rootView.findViewById(R.id.remove_button_listview);

        arrayList = new ArrayList();

        arrayList.add("ciao");
        arrayList.add("miao");

        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);
                listView.setAdapter(arrayAdapter);
            }
        });
        



        return rootView;
    }
}