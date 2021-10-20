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
import com.example.kinduya.adapter.VocabularyWordsMenuAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.VocabularyWordsMenuObject;

import java.util.ArrayList;
import java.util.List;


public class VocabularyWordsMenuFragment extends Fragment implements VocabularyWordsMenuAdapter.ItemOnClickListener {


    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_SEARCH_QUERY = "search";


    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvVocabularyWords;
    VocabularyWordsMenuAdapter adapter;
    ImageView back;
    int category, position;

    public static VocabularyWordsMenuFragment newInstance(int category, int position, String searchQueryParams) {
        VocabularyWordsMenuFragment fragment = new VocabularyWordsMenuFragment();
        Bundle args = new Bundle();
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
            category = getArguments().getInt(ARG_CATEGORY);
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_words_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        rvVocabularyWords = v.findViewById(R.id.rvMenu);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());


        adapter = new VocabularyWordsMenuAdapter(this, requireContext());
        rvVocabularyWords.setAdapter(adapter);
        adapter.submitList(populateItems());
        rvVocabularyWords.scrollToPosition(position);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new VocabularyMenuFragment()).commit();
    }

    @Override
    public void onItemClicked(VocabularyWordsMenuObject item, int position) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,
                R.anim.to_left, R.anim.from_left, R.anim.to_right);
        fragmentTransaction.replace(R.id.frameLayout,
                VocabularyWordsFragment.newInstance(item.getCategory(), 0, position, "")).commit();
    }


    private List<VocabularyWordsMenuObject> populateItems(){
        List<VocabularyWordsMenuObject> items = new ArrayList<>();
        items.add(new VocabularyWordsMenuObject("family","family", 10));
        items.add(new VocabularyWordsMenuObject("gender","gender", 11));
        items.add(new VocabularyWordsMenuObject("person","persons", 12));
        items.add(new VocabularyWordsMenuObject("relationship","relationship", 13));
        items.add(new VocabularyWordsMenuObject("animals","animals", 14));
        items.add(new VocabularyWordsMenuObject("questions","questions", 15));
        items.add(new VocabularyWordsMenuObject("emotions","emotions", 16));
        items.add(new VocabularyWordsMenuObject("feelings","feelings", 17));
        items.add(new VocabularyWordsMenuObject("beverage","beverage", 18));
        items.add(new VocabularyWordsMenuObject("illness","illness", 19));
        items.add(new VocabularyWordsMenuObject("foods","foods", 20));
        items.add(new VocabularyWordsMenuObject("vegetables","vegetables", 21));
        items.add(new VocabularyWordsMenuObject("insects","insects", 22));
        items.add(new VocabularyWordsMenuObject("fruits","fruits", 23));
        items.add(new VocabularyWordsMenuObject("day","day", 24));
        return items;
    }


}