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

import com.bumptech.glide.Glide;
import com.example.kinduya.MainActivity;
import com.example.kinduya.R;

public class CulturalMusicFragment extends Fragment {

    Button btnplay, btnff, btnfr;
    TextView txtsname, txtstart, txtsstop;
    SeekBar seekmusic;

    ImageView back, imgView;
    String name, image;
    static MediaPlayer mediaPlayer;
    int position;
    int category;
    Thread updateSeekbar;

    private static final String ARG_CATEGORY = "category";
    private static final String ARG_NAME = "name";
    private static final String ARG_IMAGE = "image";
    public static CulturalMusicFragment newInstance(String name, int category, String image) {
        CulturalMusicFragment fragment = new CulturalMusicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY, category);
        args.putString(ARG_NAME, name);
        args.putString(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_CATEGORY);
            name = getArguments().getString(ARG_NAME);
            image = getArguments().getString(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cultural_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        btnff = v.findViewById(R.id.ffbtn);
        btnplay = v.findViewById(R.id.playbtn);
        btnfr = v.findViewById(R.id.frbtn);
        txtsname = v.findViewById(R.id.txtsname);
        txtstart = v.findViewById(R.id.txtsstart);
        txtsstop = v.findViewById(R.id.txtsstop);
        seekmusic = v.findViewById(R.id.seekbar);
        back = v.findViewById(R.id.back);
        imgView = v.findViewById(R.id.imgview);
        back.setOnClickListener(view -> back());

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        txtsname.setSelected(true);
        Uri uri;
        switch (category){
            case 1:
                uri = Uri.parse("android.resource://"
                        + requireActivity().getPackageName()
                        + "/"
                        + R.raw.adecer_kaw_pumanaw);
                break;
            case 2: uri = Uri.parse("android.resource://"
                    + requireActivity().getPackageName()
                    + "/"
                    + R.raw.banwa_na_madayaw_davao_oriental_provincial_hymm);
                break;
            case 3: uri = Uri.parse("android.resource://"
                    + requireActivity().getPackageName()
                    + "/"
                    + R.raw.davao_oriental_mandaya_doxology);
                break;
            case 4: uri = Uri.parse("android.resource://"
                    + requireActivity().getPackageName()
                    + "/"
                    + R.raw.handumon_ko_lang);
                break;
            case 5: uri = Uri.parse("android.resource://"
                    + requireActivity().getPackageName()
                    + "/"
                    + R.raw.oh_budi);
                break;
            case 6: uri = Uri.parse("android.resource://"
                    + requireActivity().getPackageName()
                    + "/"
                    + R.raw.olo_adon_pa_kaw);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        Glide.with(this).load(getImage(image)).into(imgView);
        txtsname.setText(name);
        mediaPlayer = MediaPlayer.create(requireContext(), uri);
        MainActivity.pauseBg();
        mediaPlayer.start();

        btnplay.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                btnplay.setBackgroundResource(R.drawable.ic_play);
                mediaPlayer.pause();
            } else{
                btnplay.setBackgroundResource(R.drawable.ic_pause);
                mediaPlayer.start();
            }
        });

        updateSeekbar = new Thread(){
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = 0;
                while (currentPosition < totalDuration){
                    try {
                        sleep(500);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekmusic.setProgress(currentPosition);
                    } catch (InterruptedException | IllegalStateException e){
                        e.printStackTrace();
                    }
                }

            }
        };

        seekmusic.setMax(mediaPlayer.getDuration());
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
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        txtsstop.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                txtstart.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        }, delay);

        btnff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                }
            }
        });

        btnfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                }
            }
        });
    }
    private void back(){
        mediaPlayer.pause();
        MainActivity.playBg();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout,CulturalMusicMainFragment.newInstance(true)).commit();
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

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }


}