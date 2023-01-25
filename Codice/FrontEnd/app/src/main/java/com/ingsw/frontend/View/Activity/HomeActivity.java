package com.ingsw.frontend.View.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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
import com.ingsw.frontend.View.Fragment.KitchenFragment;
import com.ingsw.frontend.View.Fragment.LogoFragment;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFragment;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;
import com.ingsw.frontend.View.Fragment.MenuFragment;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;
import com.ingsw.frontend.View.Fragment.SectionButtonsFragment;
import com.ingsw.frontend.View.Fragment.TablesFragment;
import com.ingsw.frontend.View.Fragment.UserFragment;
import com.ingsw.frontend.R;

public class HomeActivity extends FragmentActivity implements ElementCreateDialog.ElementCreateDialogListener, UserCreateDialog.UserCreateDialogListener, UserUpdateDialog.UserUpdateDialogListener, CategoryCreateDialog.CategoryCreateDialogListener {

    private Intent intent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().add(R.id.sectionbutton_container, new SectionButtonsFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.logo_container, new LogoFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.user_container, new UserFragment()).commit();

        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        if((!user.getPwd().equals(User.getDefaultPwd()))){
            switch (user.getJob().toString()){
                case "admin":
                    getSupportFragmentManager().beginTransaction().replace(R.id.section_container, new RestaurantFragment()).commit();
                    break;
                case "supervisor":
                    getSupportFragmentManager().beginTransaction().replace(R.id.section_container, new MenuFragment()).commit();
                    break;
                case "waiter":
                    getSupportFragmentManager().beginTransaction().replace(R.id.section_container, new TablesFragment()).commit();
                    break;
                case "chef":
                    getSupportFragmentManager().beginTransaction().replace(R.id.section_container, new KitchenFragment()).commit();
                    break;
            }
        }
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
    public void createElement(String name, String description, Double price, Boolean prepackaged,MenuElementsFragment menuElementsFragment) {
        Element element = new Element(name,description,price,prepackaged, menuElementsFragment.getCategoryId());

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

        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }


    @Override
    public void createCategory(String name,MenuCategoriesFoodFragment menuCategoriesFoodFragment, MenuCategoriesDrinkFragment menuCategoriesDrinkFragment) {
        if(menuCategoriesFoodFragment!=null){
            Category category = new Category(name,menuCategoriesFoodFragment.getAliment_type(),menuCategoriesFoodFragment.getMenu().getId());
            menuCategoriesFoodFragment.createCategory(category);
        }
        else{
            Category category = new Category(name,menuCategoriesDrinkFragment.getAliment_type(),menuCategoriesDrinkFragment.getMenu().getId());
            menuCategoriesDrinkFragment.createCategory(category);
        }
    }
}