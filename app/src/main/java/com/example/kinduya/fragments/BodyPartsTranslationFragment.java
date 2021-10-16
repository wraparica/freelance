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
import android.widget.TextView;

import com.example.kinduya.R;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;


public class BodyPartsTranslationFragment extends Fragment {

    private static final String ARG_ID = "id";
    private long id;
    ImageView imageView, back;
    TextView englishTranslation, mandayaTranslation;
    KinduyaDatabase kinduyaDatabase;
    public static BodyPartsTranslationFragment newInstance(long id) {
        BodyPartsTranslationFragment fragment = new BodyPartsTranslationFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getLong(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_body_parts_translation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        AppDataEntity item = kinduyaDatabase.appDataDao().getAppDataById(id);
        imageView = v.findViewById(R.id.image);
        englishTranslation = v.findViewById(R.id.englishTranslation);
        mandayaTranslation = v.findViewById(R.id.tvMandayaTranslation);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        int id = requireContext().getResources().getIdentifier(
                item.getImage(), "drawable", requireContext().getPackageName());
        imageView.setBackgroundResource(id);
        englishTranslation.setText(item.getEnglish());
        mandayaTranslation.setText(item.getMandaya());
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new BodyPartsMainFragment()).commit();
    }
}