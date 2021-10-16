package com.example.kinduya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.kinduya.MainActivity;
import com.example.kinduya.R;
import com.example.kinduya.container.VideoBackgroundView;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.viewmodel.SplashScreenViewModel;

public class SplashScreen extends AppCompatActivity {

    VideoBackgroundView videoBackgroundView;
    KinduyaDatabase kinduyaDatabase;
    private SplashScreenViewModel splashScreenViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        kinduyaDatabase = KinduyaDatabase.getInstance(this.getApplicationContext());
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        splashScreenViewModel.insertQuestion(kinduyaDatabase);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }, 2000);
    }

}