package com.ingsw.frontend.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ingsw.frontend.Fragment.LogoFragment;
import com.ingsw.frontend.Fragment.RestaurantFragment;
import com.ingsw.frontend.Fragment.SectionButtonsFragment;
import com.ingsw.frontend.Fragment.UserFragment;
import com.ingsw.frontend.R;

public class HomeActivity extends FragmentActivity {

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
}