package com.example.kinduya.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;

public class VocabularyMenuFragment extends Fragment {


    ImageView words, back, phrases;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        back = v.findViewById(R.id.back);
        words = v.findViewById(R.id.words);
        phrases = v.findViewById(R.id.phrases);

        Glide.with(this).load(getImage("words")).into(words);
        Glide.with(this).load(getImage("phrases")).into(phrases);
        back.setOnClickListener(view -> back());
        words.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, VocabularyWordsFragment.newInstance(5, 0, null)).commit();
        });

        phrases.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new VocabularyPhrasesFragment()).commit();
        });
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new MainFragment()).commit();
    }
}