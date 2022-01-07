package com.example.kinduya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.kinduya.MainActivity;
import com.example.kinduya.R;
import com.example.kinduya.container.VideoBackgroundView;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.dialog.TermsAndConditionDialog;
import com.example.kinduya.utilities.PrefKeys;
import com.example.kinduya.utilities.PreferencesManager;
import com.example.kinduya.viewmodel.SplashScreenViewModel;

public class SplashScreen extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private VideoView videoView;
    MediaPlayer mMediaPlayer;
    KinduyaDatabase kinduyaDatabase;
    PreferencesManager preferencesManager;
    private SplashScreenViewModel splashScreenViewModel;
    public static final String PRIVACY_POLICY_DIALOG_TAG = "privacyPolicyDialog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash_screen);
        kinduyaDatabase = KinduyaDatabase.getInstance(this.getApplicationContext());
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        splashScreenViewModel.insertQuestion(kinduyaDatabase);
        videoView = findViewById(R.id.videoview);
        preferencesManager = PreferencesManager.getInstance(this);
        Handler handler = new Handler();


        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.splash_new);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                videoView.start();

            }
        });
        videoView.setOnCompletionListener(mediaPlayer -> {
            if (!preferencesManager.getBoolean(PrefKeys.TERMS_AND_CONDITION)){
                showPrivacyPolicyDialog();
            } else {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }


    protected void showPrivacyPolicyDialog() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(PRIVACY_POLICY_DIALOG_TAG);
        if (prev != null) {
            transaction.remove(prev);
        }
        TermsAndConditionDialog dialog = new TermsAndConditionDialog();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        dialog.show(transaction, PRIVACY_POLICY_DIALOG_TAG);

    }
    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (!preferencesManager.getBoolean(PrefKeys.TERMS_AND_CONDITION)){
            Toast.makeText(this,
                    "You need to accept Terms and condition to use the application", Toast.LENGTH_LONG).show();

            finish();
        } else {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }
    }
}