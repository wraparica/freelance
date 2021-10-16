package com.example.kinduya.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kinduya.R;
import com.example.kinduya.adapter.PronunciationMainAdapter;
import com.example.kinduya.adapter.VocabularyMainAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.viewmodel.VocabularyViewModel;

import java.util.List;

public class VocabularyWordsFragment extends Fragment implements VocabularyMainAdapter.ItemOnClickListener {


    private static final String ARG_ID = "id";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_POSITION = "position";
    private static final String ARG_SEARCH_QUERY = "search";


    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvVocabularyWords;
    VocabularyMainAdapter adapter;
    LiveData<List<AppDataEntity>> data;
    ImageView back;
    int category, position;
    VocabularyViewModel vocabularyViewModel;
    SearchView searchView;
    String searchQuery, searchQueryParams = "";

    public static VocabularyWordsFragment newInstance(int category, int position, String searchQueryParams) {
        VocabularyWordsFragment fragment = new VocabularyWordsFragment();
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
            searchQueryParams = getArguments().getString(ARG_SEARCH_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_words, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        rvVocabularyWords = v.findViewById(R.id.rvVocabularyWord);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        vocabularyViewModel = new ViewModelProvider(this).get(VocabularyViewModel.class);
        searchView = v.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        setupSearch();
        if (searchQueryParams != null && !searchQueryParams.equals("")) {
            searchView.setQuery(searchQueryParams, false);

            vocabularyViewModel.setSearchQuery("%" + searchQueryParams + "%");
        } else {
            vocabularyViewModel.setSearchQuery("%%");
        }

        adapter = new VocabularyMainAdapter(this, 5);
        rvVocabularyWords.setAdapter(adapter);
        vocabularyViewModel.setCategory(5);
        vocabularyViewModel.getLiveItems().observe(getViewLifecycleOwner(), appDataEntities -> {
            adapter.submitList(appDataEntities);
            rvVocabularyWords.scrollToPosition(position);
        });

        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupSearch(){
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText;
                vocabularyViewModel.setSearchQuery("%" + newText + "%");
                return true;
            }
        });
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new VocabularyMenuFragment()).commit();
    }

    @Override
    public void onItemClicked(AppDataEntity appDataEntity, int position) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,
                R.anim.to_left, R.anim.from_left, R.anim.to_right);
        fragmentTransaction.replace(R.id.frameLayout,
                VocabularyWordsTranslationFragment.newInstance(appDataEntity.getId(), category, position, searchQuery)).commit();
    }
}