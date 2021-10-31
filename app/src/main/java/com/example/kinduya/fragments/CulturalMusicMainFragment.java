package com.example.kinduya.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kinduya.R;
import com.example.kinduya.adapter.CulturalMusicMainAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.CulturalMusicMainObject;

import java.util.ArrayList;
import java.util.List;

public class CulturalMusicMainFragment extends Fragment implements CulturalMusicMainAdapter.ItemOnClickListener {


    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_SEARCH_QUERY = "search";
    private static final String ARG_IS_MUSIC = "music";


    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvVocabularyWords;
    CulturalMusicMainAdapter adapter;
    ImageView back;
    int category, position;
    boolean music;

    public static CulturalMusicMainFragment newInstance(boolean music) {
        CulturalMusicMainFragment fragment = new CulturalMusicMainFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_MUSIC, music);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            music = getArguments().getBoolean(ARG_IS_MUSIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cultural_music_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        rvVocabularyWords = v.findViewById(R.id.rvMenu);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());


        adapter = new CulturalMusicMainAdapter(this, requireContext());
        rvVocabularyWords.setAdapter(adapter);
        adapter.submitList(populateItems(music));
        rvVocabularyWords.scrollToPosition(position);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new MainFragment()).commit();
    }

    private List<CulturalMusicMainObject> populateItems(boolean music){
        List<CulturalMusicMainObject> items = new ArrayList<>();
        if (music) {
            items.add(new CulturalMusicMainObject("family", "family", 1));
            items.add(new CulturalMusicMainObject("gender", "gender", 2));
            items.add(new CulturalMusicMainObject("person", "persons", 3));
            items.add(new CulturalMusicMainObject("relationship", "relationship", 4));
        } else {
            items.add(new CulturalMusicMainObject("questions", "questions", 5));
            items.add(new CulturalMusicMainObject("response", "response", 6));
        }
        return items;
    }

    @Override
    public void onItemClicked(CulturalMusicMainObject culturalMusicMainObject, int position) {

    }
}