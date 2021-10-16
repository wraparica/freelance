package com.example.kinduya.helper;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.GameFillEntity;

import java.util.List;

public class GameFillHelper {
    private KinduyaDatabase db;

    private MediatorLiveData<List<GameFillEntity>> questions = new MediatorLiveData<>();
    private LiveData<List<GameFillEntity>> liveItems;

    public GameFillHelper(Context context){
        this.db = KinduyaDatabase.getInstance(context);
        refreshItems();
    }

    public void refreshItems(){
        this.questions.removeSource(liveItems);

        this.liveItems = db.gameFillEntityDao().getLiveQuestions();
        this.questions.addSource(liveItems, list -> questions.postValue(list));
    }
}
