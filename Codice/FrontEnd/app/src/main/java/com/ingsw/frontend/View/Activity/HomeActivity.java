package com.ingsw.frontend.View.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.View.Dialog.CategoryCreateDialog;
import com.ingsw.frontend.View.Dialog.ElementCreateDialog;
import com.ingsw.frontend.View.Dialog.UserCreateDialog;
import com.ingsw.frontend.View.Dialog.UserUpdateDialog;
import com.ingsw.frontend.View.Fragment.LogoFragment;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;
import com.ingsw.frontend.View.Fragment.SectionButtonsFragment;
import com.ingsw.frontend.View.Fragment.UserFragment;
import com.ingsw.frontend.R;

public class HomeActivity extends FragmentActivity implements ElementCreateDialog.ElementCreateDialogListener, UserCreateDialog.UserCreateDialogListener, UserUpdateDialog.UserUpdateDialogListener, CategoryCreateDialog.CategoryCreateDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().add(R.id.sectionbutton_container, new SectionButtonsFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.logo_container, new LogoFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.user_container, new UserFragment()).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.section_container, new RestaurantFragment()).commit();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void changeFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.section_container, fragment);
        transaction.commit();
    }

    @Override
    public void createElement(String name, String description, Double price, Boolean prepackaged,Integer idCategory) {
        Element element = new Element(name,description,price,prepackaged,idCategory);

        MenuElementsFragment menuElementsFragment = new MenuElementsFragment();

        menuElementsFragment.createElement(element);
    }

    @Override
    public void createUser(String email, String pwd, String name, String surname, User_Type job, String restaurant) {
        User user = new User(email,pwd,name,surname,job,restaurant);

        switch(job.toString()){
            case "admin":
                MembersAdminFragment membersAdminFragment = new MembersAdminFragment();
                membersAdminFragment.createUser(user);
                break;
            case "supervisor":
                MembersSupervisorsFragment membersSupervisorsFragment = new MembersSupervisorsFragment();
                membersSupervisorsFragment.createUser(user);
                break;
            case "waiter":
                MembersWaitersFragment membersWaitersFragment = new MembersWaitersFragment();
                membersWaitersFragment.createUser(user);
                break;
            case "chef":
                MembersChefsFragment membersChefsFragment = new MembersChefsFragment();
                membersChefsFragment.createUser(user);
                break;
        }
    }

    @Override
    public void updateUser(User user) {
        UserFragment userFragment = new UserFragment();

        userFragment.updateUser(user);
    }


    @Override
    public void createElement(String name, Aliment_Type aliment,Integer idMenu) {
        Category category = new Category(name,aliment,idMenu);

        switch(aliment.toString()){
            case "food":
                MenuCategoriesFoodFragment menuCategoriesFoodFragment = new MenuCategoriesFoodFragment();
                menuCategoriesFoodFragment.createCategory(category);
                break;
            case "drink":
                MenuCategoriesDrinkFragment menuCategoriesDrinkFragment = new MenuCategoriesDrinkFragment();
                menuCategoriesDrinkFragment.createCategory(category);
                break;
        }
    }
}