package com.example.kinduya.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.helper.VocabularyWordHelper;

import java.util.List;

public class VocabularyViewModel extends AndroidViewModel {
    private VocabularyWordHelper helper;
    private Application app;
    public VocabularyViewModel(@NonNull Application application){
        super(application);
        this.app = application;
        this.helper = new VocabularyWordHelper(app);
    }

    public void setSearchQuery(String searchQuery){
        helper.setSearchQuery(searchQuery);

    }

    public void setCategory(int category){
        helper.setCategory(category);

    }

    public LiveData<List<AppDataEntity>> getLiveItems(){
        return helper.getObservableItems();
    }

}
