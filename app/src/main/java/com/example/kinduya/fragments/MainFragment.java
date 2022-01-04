package com.example.kinduya.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;

public class MainFragment extends Fragment {

    ImageView game, abc, pronunciation, video, vocabulary, music, speech_to_speech;
    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        game = v.findViewById(R.id.game);
        video = v.findViewById(R.id.video);
        vocabulary = v.findViewById(R.id.vocabulary);
        pronunciation = v.findViewById(R.id.pronunciation);
        music = v.findViewById(R.id.music);
        speech_to_speech = v.findViewById(R.id.speech_to_speech);

        Glide.with(this).load(getImage("pronuncation_1")).into(pronunciation);
        Glide.with(this).load(getImage("videos_1")).into(video);
        Glide.with(this).load(getImage("vocabulary_1")).into(vocabulary);
        Glide.with(this).load(getImage("songs_1")).into(music);
        Glide.with(this).load(getImage("game_1")).into(game);
        Glide.with(this).load(getImage("speech_to_speech_1")).into(speech_to_speech);
        game.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new GameMenuFragment()).commit();
        });
        vocabulary.setOnClickListener(view -> {
            getParentFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right)
            .replace(R.id.frameLayout, new VocabularyMenuFragment()).commit();
        });
        pronunciation.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new PronunciationMainFragment()).commit();

        });

        music.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, CulturalMusicMainFragment.newInstance(true)).commit();

        });

        video.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, CulturalMusicMainFragment.newInstance(false)).commit();

        });
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}