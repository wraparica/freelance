package com.example.kinduya.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kinduya.R;
import com.example.kinduya.adapter.BodyPartsMainAdapter;
import com.example.kinduya.adapter.PronunciationMainAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;

import java.util.List;


public class PronunciationDefaultFragment extends Fragment implements PronunciationMainAdapter.ItemOnClickListener {

    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvBodyParts;
    PronunciationMainAdapter adapter;
    ImageView back;
    List<AppDataEntity> data;
    int category, position;
    public PronunciationDefaultFragment(int category, int position) {
        this.category = category;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pronunciation_default, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        rvBodyParts = v.findViewById(R.id.rvBodyParts);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        adapter = new PronunciationMainAdapter(requireContext(), this);
        data = kinduyaDatabase.appDataDao().getData(category);
        adapter.submitList(data);
        rvBodyParts.setAdapter(adapter);
        rvBodyParts.scrollToPosition(position);
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new PronunciationMainFragment()).commit();
    }

    @Override
    public void onItemClicked(AppDataEntity appDataEntity, int position) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,
                R.anim.to_left, R.anim.from_left, R.anim.to_right);
        fragmentTransaction.replace(R.id.frameLayout,
                PronunciationDefaultTranslationFragment.newInstance(appDataEntity.getId(), category, position)).commit();
    }
}