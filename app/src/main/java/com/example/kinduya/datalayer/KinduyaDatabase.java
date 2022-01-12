package com.example.kinduya.datalayer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kinduya.dao.AppDataDao;
import com.example.kinduya.dao.GameFillChoicesDao;
import com.example.kinduya.dao.GameFillEntityDao;
import com.example.kinduya.dao.HighscoreDao;
import com.example.kinduya.dao.SpeechToSpeechDao;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;
import com.example.kinduya.entities.HighscoreEntity;
import com.example.kinduya.entities.SpeechToSpeechEntity;

@Database(entities = {GameFillEntity.class,
        GameFillChoicesEntity.class,
        HighscoreEntity.class,
        AppDataEntity.class,
        SpeechToSpeechEntity.class},
        version = 1,
        exportSchema = true)
public abstract class KinduyaDatabase extends RoomDatabase {
    private static KinduyaDatabase INSTANCE;
    public static KinduyaDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), KinduyaDatabase.class, "kinduyaDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public abstract GameFillChoicesDao gameFillChoicesDao();
    public abstract GameFillEntityDao gameFillEntityDao();
    public abstract HighscoreDao highscoreDao();
    public abstract AppDataDao appDataDao();
    public abstract SpeechToSpeechDao speechToSpeechDao();
}