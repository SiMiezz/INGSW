package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.View.Adapter.CategoryAdapter;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class MenuCategoriesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MenuElementsFragment menuElementsFragment;


    private ArrayList<Category> arrayList;
    private ImageButton removeButton;
    private ImageButton addButton;
    private ImageButton backButton;
    private ImageButton confirmButton;
    private TabLayout tabLayout;

    private RecyclerView foodView;
    private RecyclerView drinkView;

    private MenuCategoriesFoodFragment menuCategoriesFoodFragment;
    private MenuCategoriesDrinkFragment menuCategoriesDrinkFragment;

    private CategoryAdapter adapter;
    private MenuCategoriesPresenter menuCategoriesPresenter = new MenuCategoriesPresenter(this);

    public MenuCategoriesFragment() {
        // Required empty public constructor
    }

    public MenuCategoriesFragment(MenuElementsFragment menuElementsFragment){
        this.menuElementsFragment = menuElementsFragment;
    }

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


        arrayList = new ArrayList<>();
        removeButton = rootView.findViewById(R.id.remove_category_button);
        addButton = rootView.findViewById(R.id.add_category_button);
        backButton = rootView.findViewById(R.id.back_category_button);
        confirmButton = rootView.findViewById(R.id.confirm_category_button);
        tabLayout = rootView.findViewById(R.id.tab_category);

//        drinkView = rootView.findViewById(R.id.category_drink_listview);

        menuCategoriesFoodFragment = new MenuCategoriesFoodFragment(this, menuElementsFragment);
        menuCategoriesDrinkFragment = new MenuCategoriesDrinkFragment(this, menuElementsFragment);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.food_drink_container, menuCategoriesFoodFragment);
        fragmentTransaction.commit();


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CategoryAdapter.currentLayout == -1){
                    CategoryAdapter.currentLayout = -2;
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
                if(CategoryAdapter.currentLayout == -2){
                    CategoryAdapter.currentLayout = -1;
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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.food_drink_container, menuCategoriesFoodFragment);
                    fragmentTransaction.commit();
                }
                else{
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.food_drink_container, menuCategoriesDrinkFragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        /////////////////////////////////////////////


        return rootView;
    }

    public MenuCategoriesFoodFragment getMenuCategoriesFoodFragment() {
        return menuCategoriesFoodFragment;
    }

    public MenuCategoriesDrinkFragment getMenuCategoriesDrinkFragment(){
        return menuCategoriesDrinkFragment;
    }
}