package com.example.kinduya.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;


public class GameMenuFragment extends Fragment {

    private VideoView videoView;
    MediaPlayer mMediaPlayer;
    int currentVidoePosition;
    ImageView start, back, highscore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        videoView = v.findViewById(R.id.videoview);
        start = v.findViewById(R.id.start);
        back = v.findViewById(R.id.back);
        highscore = v.findViewById(R.id.highscore);


        Glide.with(this).load(getImage("start")).into(start);
        Glide.with(this).load(getImage("highscore")).into(highscore);
        start.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new GameFillFragment()).commit();
        });

        highscore.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, HighscoreFragment.newInstance(false)).commit();
        });

        back.setOnClickListener(view -> back());

        Uri uri = Uri.parse("android.resource://"
        + requireContext().getPackageName()
        + "/"
        + R.raw.bacground);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(true);
                if(currentVidoePosition != 0){
                    mMediaPlayer.seekTo(currentVidoePosition);
                    mMediaPlayer.start();
                }
            }
        });
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new GamesMainMenuFragment()).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        currentVidoePosition = mMediaPlayer.getCurrentPosition();
        videoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}