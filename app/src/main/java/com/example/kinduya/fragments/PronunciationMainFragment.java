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

public class PronunciationMainFragment extends Fragment {

    ImageView shapes, colors, bodyParts, numbers, back, pronunciation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pronunciation_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        shapes = v.findViewById(R.id.shapes);
        numbers = v.findViewById(R.id.numbers);
        colors = v.findViewById(R.id.colors);
        bodyParts = v.findViewById(R.id.bodyParts);
        back = v.findViewById(R.id.back);
        pronunciation = v.findViewById(R.id.pronunciation);
        Glide.with(this).load(getImage("shapes_final")).into(shapes);
        Glide.with(this).load(getImage("pronunciation")).into(pronunciation);
        Glide.with(this).load(getImage("colors_final")).into(colors);
        Glide.with(this).load(getImage("body_parts__final")).into(bodyParts);
        Glide.with(this).load(getImage("numbers_final")).into(numbers);
        back.setOnClickListener(view -> back());
        shapes.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new PronunciationDefaultFragment(1, 0)).commit();
        });
        colors.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new PronunciationDefaultFragment(2, 0)).commit();
        });
        bodyParts.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new PronunciationDefaultFragment(3, 0)).commit();
        });
        numbers.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_right,
                    R.anim.to_left, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.frameLayout, new PronunciationDefaultFragment(4, 0)).commit();
        });
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new MainFragment()).commit();
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}