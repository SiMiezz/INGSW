package com.example.natour21.View.Other;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.natour21.Entity.Utente;
import com.example.natour21.Presenter.HomePagePresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Fragment.ChatFragment;
import com.example.natour21.View.Fragment.CollezioniFragment;
import com.example.natour21.View.Fragment.HomePageFragment;
import com.example.natour21.View.Fragment.PreferitiFragment;
import com.example.natour21.View.Fragment.ProfiloFragment;
import com.example.natour21.View.Fragment.SupportFragment;
import com.example.natour21.View.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Pattern;

public class HomePageActivity extends AppCompatActivity {

    private FrameLayout frameLayoutHomePage;
    private final ChatFragment chatFragment = new ChatFragment();
    private final CollezioniFragment collezioniFragment = new CollezioniFragment();
    private final HomePageFragment homePageFragment = new HomePageFragment();
    private final ProfiloFragment profiloFragment = new ProfiloFragment();
    private final SupportFragment supportFragment = new SupportFragment();
    private final PreferitiFragment preferitiFragment = new PreferitiFragment();

    private View headerView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ImageView burgerMenu, assistenzaImageView, immagineMenuLaterale;
    private TextView emailMenuLaterale, usernameMenuLaterale, titoloToolbarHomePage;
    private Button logoutButton;

    private HomePagePresenter mHomePagePresenter;
    private final String TAG = "HomePageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mHomePagePresenter = new HomePagePresenter(this);

        changeFragment(homePageFragment);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.menulaterale);
        toolbar = (Toolbar) findViewById(R.id.HPtoolbar);
        headerView = navigationView.getHeaderView(0);
        immagineMenuLaterale = (ImageView) headerView.findViewById(R.id.HPprofileImage);
        emailMenuLaterale = (TextView) headerView.findViewById(R.id.LGtextViewOR);
        usernameMenuLaterale = (TextView) headerView.findViewById(R.id.usernameTextViewMenuLaterale);

        Utente user;

        Intent intentArrived = getIntent();
        if(intentArrived != null && intentArrived.hasExtra("Utente")) {
            Log.i(TAG,"UserDelivered NaTour21.");
            user = (Utente) intentArrived.getSerializableExtra("Utente");
            LocalUser.setLocalUser(this, user);
            Log.i(TAG,user.toString());
        }else{
            Log.i(TAG,"UserDelivered GUEST.");
            user = LocalUser.getLocalUser(this);
            Log.i(TAG,user.toString());
        }

        uploadUser();

        logoutButton = (Button) findViewById(R.id.logoutHomePageButton);
        burgerMenu = (ImageView) findViewById(R.id.burger_menu);
        assistenzaImageView = findViewById(R.id.assistenza);
        titoloToolbarHomePage = (TextView) findViewById(R.id.titoloToolbarHomePage);
        frameLayoutHomePage = (FrameLayout) findViewById(R.id.frameLayoutHomePage);

        assistenzaImageView.setOnClickListener(v -> {
            Log.i(TAG,"Click assistenza.");
            drawerLayout.closeDrawer(GravityCompat.START);
            titoloToolbarHomePage.setText(R.string.assistence);
            changeFragment(supportFragment);
        });

        burgerMenu.setOnClickListener(v -> {
            Log.i(TAG,"Click burger.");
            drawerLayout.openDrawer(GravityCompat.START);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            Log.i(TAG,"Navigation selector.");
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_home:         drawerLayout.closeDrawer(GravityCompat.START);
                                            titoloToolbarHomePage.setText(R.string.home);
                                            changeFragment(homePageFragment);
                                            break;
                case R.id.nav_chat:         drawerLayout.closeDrawer(GravityCompat.START);
                                            titoloToolbarHomePage.setText(R.string.chat);
                                            changeFragment(chatFragment);
                                            break;
                case R.id.nav_collections:  if(!LocalUser.isGuest(this)) {
                                                drawerLayout.closeDrawer(GravityCompat.START);
                                                titoloToolbarHomePage.setText(R.string.collezioni);
                                                changeFragment(collezioniFragment);
                                            }
                                            break;
                case R.id.nav_profile:      if(!LocalUser.isGuest(this)) {
                                                drawerLayout.closeDrawer(GravityCompat.START);
                                                titoloToolbarHomePage.setText(R.string.profilo);
                                                changeFragment(profiloFragment);
                                            }
                                            break;
                case R.id.nav_add:          if(!LocalUser.isGuest(this)) {
                                                Intent newIntent = new Intent(HomePageActivity.this, PubblicaItinerarioActivity.class);
                                                startActivity(newIntent);
                                            }
                                            break;
                case R.id.nav_registra:     if(!LocalUser.isGuest(this)) {
                                                Intent intentRegistraPercorso = new Intent(HomePageActivity.this, RegistraPercorsoActivity.class);
                                                startActivity(intentRegistraPercorso);
                                            }
                                            break;
                case R.id.nav_favorites:    drawerLayout.closeDrawer(GravityCompat.START);
                                            titoloToolbarHomePage.setText(R.string.preferiti);
                                            changeFragment(preferitiFragment);
                                            break;
            }
            return true;
        });

        logoutButton.setOnClickListener(v -> {
            Log.i(TAG,"Click logout.");
            new MaterialAlertDialogBuilder(HomePageActivity.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Esci")
                    .setMessage("Sei sicuro di voler uscire?")
                    .setPositiveButton("Esci", (dialogInterface, i) -> {
                        Log.i(TAG,"Signout process started.");
                        mHomePagePresenter.signout();
                    })
                    .setNegativeButton("Annulla", (dialogInterface, i) -> {
                        Log.i(TAG,"Signout dismiss.");
                        Log.i(TAG,"Signout annullato.");
                    })
                    .show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume Called.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause Called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop Called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy Called.");
    }

    @Override
    public void onBackPressed() {
        if(titoloToolbarHomePage != null) {
            titoloToolbarHomePage.setText(R.string.home);
            changeFragment(homePageFragment);
        }
    }

    public void uploadUser() {
        Log.i(TAG,"Upload user.");
        if(LocalUser.getLocalUsername(this).matches("^facebook.*$|^google.*$"))
            usernameMenuLaterale.setText(LocalUser.getLocalNameSurname(this));
        else
            usernameMenuLaterale.setText(LocalUser.getLocalUsername(this));

        emailMenuLaterale.setText(LocalUser.getLocalEmail(this));

        String key = LocalUser.getLocalUser(this).getPhotolnk();
        if(!key.isEmpty())
            mHomePagePresenter.setPhotoHomePage(this.immagineMenuLaterale, key);
    }

    public void onSignOutPressed() {
        Log.i(TAG,"Signout pressed.");
        Intent intentLogout = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intentLogout);
    }

    public void changeFragment(Fragment fragmentToChange){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutHomePage, fragmentToChange);
        fragmentTransaction.commit();
    }

}