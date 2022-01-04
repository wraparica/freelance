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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.kinduya.MainActivity;
import com.example.kinduya.R;


public class VideoFragment extends Fragment {

    private VideoView videoView;
    MediaPlayer mMediaPlayer;
    Button btnplay, btnff, btnfr;
    ImageView back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        videoView = v.findViewById(R.id.videoview);
        btnff = v.findViewById(R.id.ffbtn);
        btnplay = v.findViewById(R.id.playbtn);
        btnfr = v.findViewById(R.id.frbtn);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        MainActivity.pauseBg();
        Uri uri = Uri.parse("android.resource://"
                + requireContext().getPackageName()
                + "/"
                + R.raw.splash_new);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(mediaPlayer -> {
            mMediaPlayer = mediaPlayer;
            mMediaPlayer.start();

        });

        btnplay.setOnClickListener(view -> {
            if (mMediaPlayer.isPlaying()){
                btnplay.setBackgroundResource(R.drawable.ic_play);
                mMediaPlayer.pause();
            } else{
                btnplay.setBackgroundResource(R.drawable.ic_pause);
                mMediaPlayer.start();
            }
        });
    }

    private void back(){
        mMediaPlayer.pause();
        MainActivity.playBg();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout,CulturalMusicMainFragment.newInstance(false)).commit();
    }
}