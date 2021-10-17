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

public class VocabularyPhrasesTranslationFragment extends Fragment {


    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_SEARCH_QUERY = "search";
    private long id;
    private int category, position;
    private String searchQueryParams;
    ImageView imageView, back;
    TextInputEditText tvEnglish, tvMandaya;
    KinduyaDatabase kinduyaDatabase;

    public static VocabularyPhrasesTranslationFragment newInstance(long id, int category, int position, String searchQueryParams) {
        VocabularyPhrasesTranslationFragment fragment = new VocabularyPhrasesTranslationFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        args.putInt(ARG_CATEGORY, category);
        args.putInt(ARG_POSITION, position);
        args.putString(ARG_SEARCH_QUERY, searchQueryParams);
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
            searchQueryParams = getArguments().getString(ARG_SEARCH_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_phrases_translation, container, false);
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
        tvEnglish.setText(item.getEnglish_phrase());
        tvMandaya.setText(item.getMandaya_phrase());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, VocabularyPhrasesFragment.newInstance(category, position, searchQueryParams)).commit();
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}