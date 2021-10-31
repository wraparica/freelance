package com.example.kinduya.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kinduya.R;

public class CulturalMusicFragment extends Fragment {

    Button btnplay, btnff, btnfr;
    TextView txtsname, txnstart, txtsstop;
    SeekBar seekmusic;

    String sname;
    static MediaPlayer mediaPlayer;
    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        txtsstop = v.findViewById(R.id.txtsstart);
        txtsstop = v.findViewById(R.id.txtsstop);
        seekmusic = v.findViewById(R.id.seekbar);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        txtsname.setSelected(true);


    }
}