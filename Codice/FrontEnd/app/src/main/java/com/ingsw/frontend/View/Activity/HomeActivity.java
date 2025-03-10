package com.ingsw.frontend.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Dialog.CategoryCreateDialog;
import com.ingsw.frontend.View.Dialog.ElementCreateDialog;
import com.ingsw.frontend.View.Dialog.OrderCreateDialog;
import com.ingsw.frontend.View.Dialog.UserCreateDialog;
import com.ingsw.frontend.View.Dialog.UserUpdateDialog;
import com.ingsw.frontend.View.Fragment.KitchenFragment;
import com.ingsw.frontend.View.Fragment.LogoFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;
import com.ingsw.frontend.View.Fragment.MenuFragment;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;
import com.ingsw.frontend.View.Fragment.SectionButtonsFragment;
import com.ingsw.frontend.View.Fragment.TablesFragment;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;
import com.ingsw.frontend.View.Fragment.UserFragment;

public class HomeActivity extends FragmentActivity implements ElementCreateDialog.ElementCreateDialogListener, UserCreateDialog.UserCreateDialogListener, UserUpdateDialog.UserUpdateDialogListener, CategoryCreateDialog.CategoryCreateDialogListener, OrderCreateDialog.OrderCreateDialogListener {

    private Intent intent;
    private User user;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "Schermata Home");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "HomeActivity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction
                .add(R.id.sectionbutton_container, SectionButtonsFragment.class,null)
                .add(R.id.logo_container, LogoFragment.class,null);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        if((!user.getPwd().equals(User.getDefaultPwd()))){
            switch (user.getJob().toString()){
                case "admin":
                    fragmentTransaction.add(R.id.section_container, RestaurantFragment.class, null);
                    break;
                case "supervisor":
                    fragmentTransaction.add(R.id.section_container, MenuFragment.class, null);
                    break;
                case "waiter":
                    fragmentTransaction.add(R.id.section_container, TablesFragment.class, null);
                    break;
                case "chef":
                    fragmentTransaction.add(R.id.section_container, KitchenFragment.class, null);
                    break;
            }
        }
        fragmentTransaction.add(R.id.user_container, UserFragment.class,null).commitNow();
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
        transaction.replace(R.id.section_container, fragment.getClass(), null);
        transaction.commitNow();
    }

    @Override
    public void createElement(Element element,MenuElementsFragment menuElementsFragment) {
        menuElementsFragment.createElement(element);
    }

    @Override
    public void createUser(User user,MembersSupervisorsFragment membersSupervisorsFragment,MembersWaitersFragment membersWaitersFragment, MembersChefsFragment membersChefsFragment) {
        if(membersSupervisorsFragment != null){
            membersSupervisorsFragment.createUser(user);
        }
        else if(membersWaitersFragment != null){
            membersWaitersFragment.createUser(user);
        }
        else{
            membersChefsFragment.createUser(user);
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
    public void createCategory(Category category,MenuCategoriesFoodFragment menuCategoriesFoodFragment, MenuCategoriesDrinkFragment menuCategoriesDrinkFragment) {
        if(menuCategoriesFoodFragment != null){
            menuCategoriesFoodFragment.createCategory(category);
        }
        else{
            menuCategoriesDrinkFragment.createCategory(category);
        }
    }

    @Override
    public void createOrder(Order order, TablesSelectedFragment tablesSelectedFragment) {
        tablesSelectedFragment.createOrder(order);
    }
}