package com.example.kinduya.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kinduya.dao.GameFillEntityDao;
import com.example.kinduya.entities.GameFillEntity;

import java.util.ArrayList;
import java.util.List;

public class GameFillViewModel extends ViewModel {

    private MutableLiveData<Integer> questionCount;
    public MutableLiveData<Integer> getQuestionCount(){
        if(questionCount == null){
            questionCount = new MutableLiveData<>();
            questionCount.postValue(0);
        }
        return questionCount;
    }
    public void postQuestionCount(int count){
        questionCount.postValue(count+1);
    }


    private MutableLiveData<Integer> answeredCount;
    public MutableLiveData<Integer> getAnsweredCount(){
        if(answeredCount == null){
            answeredCount = new MutableLiveData<>();
            answeredCount.postValue(0);
        }
        return answeredCount;
    }
    public void postAnsweredCount(int count){
        answeredCount.postValue(count+1);
    }
}
