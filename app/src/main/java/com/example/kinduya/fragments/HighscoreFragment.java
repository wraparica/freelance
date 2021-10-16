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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        List<HighscoreEntity> data = kinduyaDatabase.highscoreDao().getHighscores();
        for(HighscoreEntity h : data){
            Log.d("highscores", h.getName());
            Log.d("highscores", String.valueOf(h.getScore()));
        }
        adapter.submitList(data);
        highscores.setLayoutManager(new LinearLayoutManager(requireContext()));
        highscores.setAdapter(adapter);
    }
    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new GameMenuFragment()).commit();
    }
}