package com.example.kinduya.fragments;

import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.kinduya.MainActivity;
import com.example.kinduya.R;


public class VideoFragment extends Fragment {

    private VideoView videoView;
    MediaPlayer mMediaPlayer;
    Button btnplay, btnff, btnfr;
    ImageView back;
    TextView txtsname, txtstart, txtsstop;
    SeekBar seekmusic;
    Thread updateSeekbar;
    int category;
    final Handler handler = new Handler();
    private static final String ARG_CATEGORY = "category";
    public static VideoFragment newInstance(int category) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_CATEGORY);
        }
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
        txtstart = v.findViewById(R.id.txtsstart);
        txtsstop = v.findViewById(R.id.txtsstop);
        seekmusic = v.findViewById(R.id.seekbar);
        back.setOnClickListener(view -> back());
        MainActivity.pauseBg();
        Uri uri;
        switch (category){
            case 7 : uri = Uri.parse("android.resource://"
                    + requireContext().getPackageName()
                    + "/"
                    + R.raw.culturaldance);
            break;
            case 8 : uri = Uri.parse("android.resource://"
                    + requireContext().getPackageName()
                    + "/"
                    + R.raw.animation);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }

        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(mediaPlayer -> {
            mMediaPlayer = mediaPlayer;
            mMediaPlayer.start();
            updateSeekbar = new Thread(){
            @Override
            public void run() {
                int totalDuration = mMediaPlayer.getDuration();
                int currentPosition = 0;
                while (currentPosition < totalDuration){
                    try {
                        sleep(500);
                        currentPosition = mMediaPlayer.getCurrentPosition();
                        seekmusic.setProgress(currentPosition);
                    } catch (InterruptedException | IllegalStateException e){
                        e.printStackTrace();
                    }
                }

            }
        };
            seekmusic.setMax(mMediaPlayer.getDuration());
            updateSeekbar.start();
            seekmusic.getProgressDrawable().setColorFilter(requireContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            seekmusic.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            seekmusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mMediaPlayer.seekTo(seekBar.getProgress());
                }
            });

            String endTime = createTime(mMediaPlayer.getDuration());
            txtsstop.setText(endTime);


            final int delay = 1000;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String currentTime = createTime(mMediaPlayer.getCurrentPosition());
                    txtstart.setText(currentTime);
                    handler.postDelayed(this, delay);
                }
            }, delay);

            btnff.setOnClickListener(view -> {
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + 10000);
                }
            });

            btnfr.setOnClickListener(view -> {
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - 10000);
                }
            });
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


    public String createTime(int duration){
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        time+=min+":";
        if (sec < 10){
            time+="0";
        }
        time+=sec;
        return time;
    }

    private void back(){
        mMediaPlayer.pause();
        handler.removeCallbacksAndMessages(null);
        MainActivity.playBg();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout,CulturalMusicMainFragment.newInstance(false)).commit();
    }
}