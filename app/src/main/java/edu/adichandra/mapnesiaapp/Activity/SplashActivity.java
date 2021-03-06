package edu.adichandra.mapnesiaapp.Activity;

/*
 * NIM   : 10118015
 * NAMA  : Adi Chandra Nugraha
 * KELAS : IF1
 * TGL   : 09/07/2021
 * Deskripsi : Menampilkan halaman SplashScreen dengan waktu 3000
 * */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import edu.adichandra.mapnesiaapp.NotificationReceiver;
import edu.adichandra.mapnesiaapp.R;

public class SplashActivity extends AppCompatActivity {

    ImageView img;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.logo);
        TextView textView = findViewById(R.id.appname);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

        textView.startAnimation(animation);
        img.startAnimation(animation);

        NotificationReceiver notificationReceiver = new NotificationReceiver();
        notificationReceiver.setRepeatingAlarm(getBaseContext());

        timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(3000);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, WalkthroughActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}