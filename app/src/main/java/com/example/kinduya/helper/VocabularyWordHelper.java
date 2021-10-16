package com.example.kinduya.helper;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.kinduya.dao.AppDataDao;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;

import java.util.List;

public class VocabularyWordHelper {

    private KinduyaDatabase db;
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private MutableLiveData<Integer> category = new MutableLiveData<>();
    private MediatorLiveData<List<AppDataEntity>> items = new MediatorLiveData<>();
    private LiveData<List<AppDataEntity>> liveData;

    public VocabularyWordHelper(Context context) {
        this.db = KinduyaDatabase.getInstance(context);
        this.items.addSource(searchQuery, i-> {
            refreshList();
        });
        this.items.addSource(category, i-> {
            refreshList();
        });
    }
    public void refreshList(){
        this.items.removeSource(liveData);
        this.liveData = db.appDataDao().getLiveData(getCategory(), getSearchQuery());

        this.items.addSource(liveData, list -> items.postValue(list));
    }

    public int getCategory(){
        Integer result = this.category.getValue();
        return result == null ? 5 : result;
    }

    public void setCategory(int category){
        this.category.postValue(category);
    }

    public String getSearchQuery(){
        String result = this.searchQuery.getValue();
        return result == null ? "%%" : "%" + result + "%";
    }

    public void setSearchQuery(String searchQuery){
        this.searchQuery.postValue(searchQuery);
    }
    public LiveData<List<AppDataEntity>> getObservableItems(){
        return items;
    }
}
