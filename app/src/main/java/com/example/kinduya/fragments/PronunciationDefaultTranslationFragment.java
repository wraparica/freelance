package com.example.kinduya.fragments;

import static android.view.View.GONE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class PronunciationDefaultTranslationFragment extends Fragment {

    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_PHRASE = "phrase";
    private long id;
    private int category, position;
    ImageView imageView, back, down, up;
    TextView englishTranslation, mandayaTranslation;
    TextInputEditText tvEnglish, tvMandaya, tvEnglishPhrase, tvMandayaPhrase;
    KinduyaDatabase kinduyaDatabase;
    private List<AppDataEntity> data;
    boolean phrase = false;
    LinearLayout llwords, llphrase;
    public static PronunciationDefaultTranslationFragment newInstance(long id, int category, int position, boolean phrase) {
        PronunciationDefaultTranslationFragment fragment = new PronunciationDefaultTranslationFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        args.putInt(ARG_CATEGORY, category);
        args.putInt(ARG_POSITION, position);
        args.putBoolean(ARG_PHRASE, phrase);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getLong(ARG_ID);
            category = getArguments().getInt(ARG_CATEGORY);
            position = getArguments().getInt(ARG_POSITION);
            phrase = getArguments().getBoolean(ARG_PHRASE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pronunciation_default_translation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {

        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        AppDataEntity item = kinduyaDatabase.appDataDao().getAppDataById(id);


        imageView = v.findViewById(R.id.image);
        tvEnglish = v.findViewById(R.id.tvEnglish);
        tvMandaya = v.findViewById(R.id.tvMandaya);
        tvEnglishPhrase = v.findViewById(R.id.tvEnglishPhrase);
        tvMandayaPhrase = v.findViewById(R.id.tvMandayaPhrase);
        back = v.findViewById(R.id.back);
        down = v.findViewById(R.id.down);
        up = v.findViewById(R.id.up);
        llwords = v.findViewById(R.id.words);
        llphrase = v.findViewById(R.id.phrase);

        if (phrase){
            down.setVisibility(View.INVISIBLE);
            tvEnglishPhrase.setText(item.getEnglish_phrase());
            tvMandayaPhrase.setText(item.getMandaya_phrase());
            llwords.setVisibility(GONE);
            down.setVisibility(GONE);
        } else {
            tvEnglish.setText(item.getEnglish());
            tvMandaya.setText(item.getMandaya());
            llphrase.setVisibility(GONE);
            up.setVisibility(GONE);
        }
        back.setOnClickListener(view -> back());
        down.setOnClickListener(view -> down());
        up.setOnClickListener(view -> backToWords());
        Glide.with(this).load(getImage(item.getImage())).into(imageView);
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new PronunciationDefaultFragment(category, position)).commit();
    }

    private void backToWords(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_top,
                R.anim.to_bottom, R.anim.to_top, R.anim.from_bottom);
        fragmentTransaction.replace(R.id.frameLayout, PronunciationDefaultTranslationFragment.newInstance(id, category, position, false)).commit();
    }

    private void down(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_bottom,
                R.anim.to_top, R.anim.from_top, R.anim.to_bottom);
        fragmentTransaction.replace(R.id.frameLayout, PronunciationDefaultTranslationFragment.newInstance(id, category, position, true)).commit();
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}