package com.example.kinduya.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kinduya.R;
import com.example.kinduya.adapter.HighscoreAdapter;
import com.example.kinduya.adapter.PronunciationMainAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.HighscoreEntity;

import java.util.List;


public class HighscoreFragment extends Fragment {

    KinduyaDatabase kinduyaDatabase;
    RecyclerView highscores;
    HighscoreAdapter adapter;
    ImageView back;
    boolean fallingObject;
    private static final String ARG_IS_FALLING_OBJECT = "ARG_IS_FALLING_OBJECT";
    public static HighscoreFragment newInstance(boolean fallingObject) {
        HighscoreFragment fragment = new HighscoreFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_FALLING_OBJECT, fallingObject);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fallingObject = getArguments().getBoolean(ARG_IS_FALLING_OBJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_highscore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        highscores = view.findViewById(R.id.highscores);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        back = view.findViewById(R.id.back);
        back.setOnClickListener(v -> back());
        adapter = new HighscoreAdapter();
        int game = fallingObject ? 0 : 1;
        Log.d("game", String.valueOf(game));
        List<HighscoreEntity> data = kinduyaDatabase.highscoreDao().getHighscoresByGame(game);
        List<HighscoreEntity> data1 = kinduyaDatabase.highscoreDao().getHighscores();
        for(HighscoreEntity d : data1){
            Log.d("gam1e", d.getName());
            Log.d("game1", String.valueOf(d.getScore()));
            Log.d("game1", String.valueOf(d.getGame()));
        }
        adapter.submitList(data);
        highscores.setLayoutManager(new LinearLayoutManager(requireContext()));
        highscores.setAdapter(adapter);
    }
    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        if(fallingObject) {
            fragmentTransaction.replace(R.id.frameLayout, new FallingObjectsGameMainFragment()).commit();
        } else {
            fragmentTransaction.replace(R.id.frameLayout, new GameMenuFragment()).commit();
        }
    }
}