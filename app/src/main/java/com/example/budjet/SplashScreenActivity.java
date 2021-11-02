package com.example.budjet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH =3000;
    Animation animation;
    private ImageView imageView;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pour mettre l'application en pleine écran
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        //dans Android le design visuel est créé en xml. Et chaque activité est associée à un design
        // R signifie ressource
        //
        //la mise en page signifie la conception
        //
        //main est le xml que vous avez créé sous res->layout->main.xml
        setContentView(R.layout.activity_splash_screen);

        //ecran de demarrage
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        imageView = findViewById(R.id.imageView);
        appName = findViewById(R.id.appName);

        imageView.setAnimation(animation);
        appName.setAnimation(animation);
//appeler une méthode après un certain délai.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Pour démarrer l'activite , on a utiliser la méthode startActivity(Intent).
                // Cette méthode est définie par l'objet Context hérité par l'activité.
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH);
    }
}