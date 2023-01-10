package com.ingsw.frontend.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ingsw.frontend.Adapter.CategoryAdapter;
import com.ingsw.frontend.R;

import java.util.ArrayList;
import java.util.List;

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


    private ArrayList<String> arrayList;
    private ImageButton removeButton;
    private ImageButton addButton;


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

        /////////////////////////////////////////////


        arrayList = new ArrayList();
        removeButton = rootView.findViewById(R.id.remove_category_button);
        addButton = rootView.findViewById(R.id.add_category_button);

        arrayList.add("ciao");
        arrayList.add("miao");
        arrayList.add("ncsdjiaknvuc ewf cnc slkkzndqjai sa nc sknln kasakd vfdsc erdsc ");
        arrayList.add("miao");
        arrayList.add("CACCA");
        arrayList.add("VBUICDSNWHA CBDJZ CZDJ");
        arrayList.add("ciao");
        arrayList.add("miao");
        arrayList.add("ncsdjiaknvuc ewf cnc slkkzndqjai sa nc sknln kasakd vfdsc erdsc ");
        arrayList.add("miao");
        arrayList.add("CACCA");
        arrayList.add("VBUICDSNWHA CBDJZ CZDJ");



        CategoryAdapter adapter = new CategoryAdapter(getContext(),arrayList);

        RecyclerView myView = (RecyclerView) rootView.findViewById(R.id.categories_listview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(linearLayoutManager);
        myView.setAdapter(adapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CategoryAdapter.currentLayout == -1){
                    CategoryAdapter.currentLayout = -2;
                    adapter.notifyDataSetChanged();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CategoryAdapter.currentLayout == -2){
                    CategoryAdapter.currentLayout = -1;
                    adapter.notifyDataSetChanged();
                }
            }
        });



        /////////////////////////////////////////////


        return rootView;
    }
}