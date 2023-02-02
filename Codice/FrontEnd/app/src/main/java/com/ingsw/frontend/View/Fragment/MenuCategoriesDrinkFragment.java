package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.CategoryAdapter;
import com.ingsw.frontend.View.Dialog.CategoryCreateDialog;

import java.util.ArrayList;
import java.util.Collections;

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
    private MenuCategoriesPresenter menuCategoriesPresenter = new MenuCategoriesPresenter(this);

    private Intent intent;
    private Menu menu;
    private final Aliment_Type aliment_type = Aliment_Type.drink;

    private FloatingActionButton sortDrinkButton;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Aliment_Type getAliment_type() {
        return aliment_type;
    }

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

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        drinkView = getView().findViewById(R.id.category_drink_listview);
        sortDrinkButton = getView().findViewById(R.id.sort_category_drink_button);

        adapter = new CategoryAdapter(getContext(), new ArrayList<Category>(), menuElementsFragment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drinkView.setLayoutManager(linearLayoutManager);
        drinkView.setAdapter(adapter);

        intent = getActivity().getIntent();

        menu = (Menu) intent.getSerializableExtra("menu");

        menuCategoriesPresenter.getByMenuIdAndAliment(menu.getId(), Aliment_Type.valueOf("drink"));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(drinkView);

        sortDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CategoryAdapter.currentLayout == -1 || CategoryAdapter.currentLayout == -2){
                    CategoryAdapter.currentLayout = -3;
                    adapter.notifyDataSetChanged();
                } else if(CategoryAdapter.currentLayout == -3){
                    CategoryAdapter.currentLayout = -1;
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(adapter.getArrayList(), fromPosition, toPosition);

            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

            for (Category category:adapter.getArrayList()) {
                category.setPosizione(adapter.getArrayList().indexOf(category));
                menuCategoriesPresenter.update(category);
            }

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

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
            menuCategoriesPresenter.delete(category);
        }
    }

    public void createCategory(Category category){
        menuCategoriesPresenter.create(category);
    }

    public void openDialog(){
        CategoryCreateDialog categoryCreateDialog = new CategoryCreateDialog(this);
        categoryCreateDialog.show(requireActivity().getSupportFragmentManager(),"Category");
    }

    public FloatingActionButton getSortDrinkButton() {
        return sortDrinkButton;
    }

    public void setSortDrinkButton(FloatingActionButton sortDrinkButton) {
        this.sortDrinkButton = sortDrinkButton;
    }
}