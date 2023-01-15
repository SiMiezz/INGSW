package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.CategoryAdapter;

import java.util.ArrayList;

public class MenuCategoriesDrinkFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView drinkView;
    private MenuElementsFragment menuElementsFragment;
    private MenuCategoriesFragment menuCategoriesFragment;
    private CategoryAdapter adapter;
    private MenuCategoriesPresenter menuCategoriesPresenter;

    public MenuCategoriesDrinkFragment() {
        // Required empty public constructor
    }

    public MenuCategoriesDrinkFragment(MenuCategoriesFragment menuCategoriesFragment, MenuElementsFragment menuElementsFragment){
        this.menuCategoriesFragment = menuCategoriesFragment;
        this.menuElementsFragment = menuElementsFragment;
    }

    public static MenuCategoriesDrinkFragment newInstance(String param1, String param2) {
        MenuCategoriesDrinkFragment fragment = new MenuCategoriesDrinkFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_menu_categories_drink, container, false);

        menuCategoriesPresenter = new MenuCategoriesPresenter(menuCategoriesFragment);

        drinkView = rootView.findViewById(R.id.category_drink_listview);

        adapter = new CategoryAdapter(getContext(), new ArrayList<Category>(), menuElementsFragment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drinkView.setLayoutManager(linearLayoutManager);
        drinkView.setAdapter(adapter);

        menuCategoriesPresenter.getByMenuIdAndAliment(1, Aliment_Type.valueOf("drink"));




        return rootView;
    }

    public void loadCategory(ArrayList<Category> categoryList){
        adapter.clearList();
        adapter.setArrayList(categoryList);
        adapter.notifyDataSetChanged();
    }
}