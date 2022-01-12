package com.example.kinduya.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenViewModel extends ViewModel {


    public void insertQuestion(KinduyaDatabase db){

        db.gameFillChoicesDao().deleteChoices();
        db.gameFillEntityDao().deleteQuestions();
        db.appDataDao().deleteAppData();
        db.appDataDao().insert();
        db.gameFillEntityDao().insert();
        db.gameFillChoicesDao().insert();
        db.speechToSpeechDao().insert();

    }
}
