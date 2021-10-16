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
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;

import java.util.List;


public class BodyPartsMainFragment extends Fragment implements BodyPartsMainAdapter.BodyPartsOnClickListener {

    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvBodyParts;
    BodyPartsMainAdapter adapter;
    ImageView back;
    public BodyPartsMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_body_parts_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        rvBodyParts = v.findViewById(R.id.rvBodyParts);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        adapter = new BodyPartsMainAdapter(requireContext(), this);
        List<AppDataEntity> bodyParts = kinduyaDatabase.appDataDao().getBodyParts();
        adapter.submitList(bodyParts);
        rvBodyParts.setAdapter(adapter);
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new MainFragment()).commit();
    }

    @Override
    public void onBodyPartsClicked(AppDataEntity appDataEntity) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,
                BodyPartsTranslationFragment.newInstance(appDataEntity.getId())).commit();
    }
}