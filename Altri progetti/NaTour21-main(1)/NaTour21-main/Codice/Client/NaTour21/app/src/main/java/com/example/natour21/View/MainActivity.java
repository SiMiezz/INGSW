package com.example.natour21.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.rx.RxAmplify;
import com.example.natour21.Entity.Utente;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Fragment.LoginFragment;
import com.example.natour21.View.Fragment.ForgotPasswordFragment;
import com.example.natour21.View.Fragment.SignUpFragment;
import com.example.natour21.View.Other.HomePageActivity;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    FrameLayout frameLayout;
    public Fragment loginFragment = new LoginFragment();
    public Fragment forgotPasswordFragment = new ForgotPasswordFragment();
    public Fragment signupFragment = new SignUpFragment();

    String actualFragment = "LoginFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutActivityMain);
        changeFragment(loginFragment);
    }

    @Override
    public void onBackPressed() {
        switch (actualFragment){
            case "LoginFragment": break;
            case "SignUpFragment":
                changeFragment(loginFragment);
                break;
            case "RecuperaPasswordFragment":
                changeFragment(loginFragment);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            RxAmplify.Auth.handleWebUISignInResponse(data);
        }
    }

    public void changeFragment(Fragment fragmentToChange){
        actualFragment = fragmentToChange.toString();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutActivityMain, fragmentToChange);
        fragmentTransaction.commit();
    }

    public void goHomePage(Utente u){
        Log.i(TAG, "Go to homepage.");
        Toasty.success(MainActivity.this,"Login effettuato!",
                Toasty.LENGTH_SHORT,true).show();
        if(u==null){
            Log.i(TAG, "Utente guest.");
            Intent newIntent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(newIntent);
        }else{
            Log.i(TAG, u.toString());
            Intent newIntent = new Intent(MainActivity.this, HomePageActivity.class);
            newIntent.putExtra("Utente", u);
            startActivity(newIntent);
        }
    }
}