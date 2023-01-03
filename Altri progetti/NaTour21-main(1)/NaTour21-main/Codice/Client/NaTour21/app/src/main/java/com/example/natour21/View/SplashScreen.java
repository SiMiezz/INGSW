package com.example.natour21.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.natour21.R;

import es.dmoral.toasty.Toasty;

public class SplashScreen extends AppCompatActivity {

    private final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Toasty.Config.getInstance()
                //.tintIcon(boolean tintIcon)
                //.setToastTypeface(@NonNull Typeface typeface)
                //.setTextSize(int sizeInSp)
                .allowQueue(true)
                .setGravity(Gravity.BOTTOM|Gravity.CENTER,Gravity.CENTER_HORIZONTAL,144)
                .supportDarkTheme(true)
                //.setRTL(boolean isRTL)
                .apply();

        ImageView logo = findViewById(R.id.logoSA);
        ImageView title = findViewById(R.id.titoloSA);
        TextView slogan = findViewById(R.id.slogan);
        ConstraintLayout layout = findViewById(R.id.layoutSA);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}