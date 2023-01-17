package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.CategoryAdapter;

import java.util.ArrayList;

public class MenuCategoriesFoodFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView foodView;
    private MenuElementsFragment menuElementsFragment;
    private MenuCategoriesFragment menuCategoriesFragment;
    private CategoryAdapter adapter;
    private MenuCategoriesPresenter menuCategoriesPresenter;

    private Menu menu;

    public MenuCategoriesFoodFragment() {
        // Required empty public constructor
    }

    public MenuCategoriesFoodFragment(MenuCategoriesFragment menuCategoriesFragment, MenuElementsFragment menuElementsFragment){
        this.menuCategoriesFragment = menuCategoriesFragment;
        this.menuElementsFragment = menuElementsFragment;
    }

    public static MenuCategoriesFoodFragment newInstance(String param1, String param2) {
        MenuCategoriesFoodFragment fragment = new MenuCategoriesFoodFragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_menu_categories_food, container, false);

        menuCategoriesPresenter = new MenuCategoriesPresenter(menuCategoriesFragment);

        foodView = rootView.findViewById(R.id.category_food_listview);

        adapter = new CategoryAdapter(getContext(), new ArrayList<Category>(), menuElementsFragment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        foodView.setLayoutManager(linearLayoutManager);
        foodView.setAdapter(adapter);

        Intent intent = getActivity().getIntent();

        Menu menu = (Menu) intent.getSerializableExtra("menu");

        menuCategoriesPresenter.getByMenuIdAndAliment(menu.getId(), Aliment_Type.valueOf("food"));

        return rootView;
    }


    public void loadCategory(ArrayList<Category> categoryList){
        adapter.clearList();
        adapter.setArrayList(categoryList);
        adapter.notifyDataSetChanged();
    }

    public CategoryAdapter getAdapter(){
        return adapter;
    }

    public void removeSelectedItems() {
        ArrayList<Category> categories = adapter.getSelectedItemsArrayList();
        for (Category category: categories) {
            menuCategoriesPresenter.deleteByCategoryId(category.getId());
        }
    }

}