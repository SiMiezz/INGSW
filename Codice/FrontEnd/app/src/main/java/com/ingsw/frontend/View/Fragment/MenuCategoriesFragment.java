package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Adapter.CategoryAdapter;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Dialog.CategoryCreateDialog;

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




        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        /////////////////////////////////////////////


        arrayList = new ArrayList<>();
        removeButton = getView().findViewById(R.id.remove_category_button);
        addButton = getView().findViewById(R.id.add_category_button);
        backButton = getView().findViewById(R.id.back_category_button);
        confirmButton = getView().findViewById(R.id.confirm_category_button);
        tabLayout = getView().findViewById(R.id.tab_category);

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

                CategoryAdapter.currentLayout = -1;
                if(menuCategoriesFoodFragment.getAdapter() != null)
                    menuCategoriesFoodFragment.getAdapter().notifyDataSetChanged();
                if(menuCategoriesDrinkFragment.getAdapter() != null)
                    menuCategoriesDrinkFragment.getAdapter().notifyDataSetChanged();

                if(CategoryAdapter.currentLayout == -1){
                    CategoryAdapter.currentLayout = -2;
                    if(menuCategoriesFoodFragment.getAdapter() != null)
                        menuCategoriesFoodFragment.getAdapter().notifyDataSetChanged();
                    if(menuCategoriesDrinkFragment.getAdapter() != null)
                        menuCategoriesDrinkFragment.getAdapter().notifyDataSetChanged();
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
                    if(menuCategoriesFoodFragment.getAdapter() != null)
                        menuCategoriesFoodFragment.getAdapter().notifyDataSetChanged();
                    if(menuCategoriesDrinkFragment.getAdapter() != null)
                        menuCategoriesDrinkFragment.getAdapter().notifyDataSetChanged();
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
                if(tabLayout.getSelectedTabPosition() == 0){
                    menuCategoriesFoodFragment.openDialog();
                }
                else if(tabLayout.getSelectedTabPosition() == 1){
                    menuCategoriesDrinkFragment.openDialog();
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    menuCategoriesFoodFragment.removeSelectedItems();
                }
                else if(tabLayout.getSelectedTabPosition() == 1){
                    menuCategoriesDrinkFragment.removeSelectedItems();
                }

                if(CategoryAdapter.currentLayout == -2){
                    CategoryAdapter.currentLayout = -1;
                }

                backButton.setVisibility(View.INVISIBLE);
                removeButton.setVisibility(View.VISIBLE);

                confirmButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    menuElementsFragment.setCategoryId(null);
                    menuElementsFragment.setEmptyList();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.food_drink_container, menuCategoriesFoodFragment);
                    fragmentTransaction.commit();
                }
                else{
                    menuElementsFragment.setCategoryId(null);
                    menuElementsFragment.setEmptyList();
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
    }



    public MenuCategoriesFoodFragment getMenuCategoriesFoodFragment() {
        return menuCategoriesFoodFragment;
    }

    public MenuCategoriesDrinkFragment getMenuCategoriesDrinkFragment(){
        return menuCategoriesDrinkFragment;
    }
}