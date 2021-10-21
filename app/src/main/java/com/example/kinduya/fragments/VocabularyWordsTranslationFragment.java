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
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.google.android.material.textfield.TextInputEditText;

public class VocabularyWordsTranslationFragment extends Fragment {


    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_MAIN_POSITION = "main_position";
    private static final String ARG_SEARCH_QUERY = "search";
    private static final String ARG_IS_PHRASE = "phrase";
    private long id;
    private int category, position, mainPosition;
    private String searchQueryParams;
    ImageView imageView, back;
    TextInputEditText tvEnglish, tvMandaya;
    KinduyaDatabase kinduyaDatabase;
    boolean phrase;

    public static VocabularyWordsTranslationFragment newInstance(long id, int category, int position,
                                                                 int mainPosition, String searchQueryParams,
                                                                 boolean phrase) {
        VocabularyWordsTranslationFragment fragment = new VocabularyWordsTranslationFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        args.putInt(ARG_CATEGORY, category);
        args.putInt(ARG_POSITION, position);
        args.putInt(ARG_MAIN_POSITION, mainPosition);
        args.putString(ARG_SEARCH_QUERY, searchQueryParams);
        args.putBoolean(ARG_IS_PHRASE, phrase);
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
            mainPosition = getArguments().getInt(ARG_MAIN_POSITION);
            searchQueryParams = getArguments().getString(ARG_SEARCH_QUERY);
            phrase = getArguments().getBoolean(ARG_IS_PHRASE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_words_translation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        AppDataEntity item = kinduyaDatabase.appDataDao().getAppDataById(id);
        tvEnglish = v.findViewById(R.id.tvEnglish);
        tvMandaya = v.findViewById(R.id.tvMandaya);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        imageView = v.findViewById(R.id.background);
        Glide.with(this).load(getImage("vocabulary_translation")).into(imageView);
        tvEnglish.setText(item.getEnglish());
        tvMandaya.setText(item.getMandaya());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, VocabularyWordsFragment.newInstance(category, position,mainPosition, searchQueryParams, phrase)).commit();
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}