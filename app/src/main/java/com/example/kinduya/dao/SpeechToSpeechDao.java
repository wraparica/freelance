package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.GameFillEntity;
import com.example.kinduya.entities.HighscoreEntity;
import com.example.kinduya.entities.SpeechToSpeechEntity;

import java.util.List;

@Dao
public interface SpeechToSpeechDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<SpeechToSpeechEntity> SpeechToSpeechEntity);


    @Query("DELETE FROM speech_to_speech")
    void deleteSpeechToSpeech();

    @Query("INSERT OR REPLACE INTO speech_to_speech (english, mandaya, mandaya_file, english_file) " +
            " VALUES ('tonight', 'adun na gabi','adun_na_gabi_24', 'tonight_24' )," +
            "('star apple', 'kaymito','kaymito_13','star_apple_13')," +
            "('spider', 'lawalawa','lawalawa_15','spider_15')," +
            "('snake', 'ud','ud_19','snake_19')," +
            "('grasshopper', 'pispis','pispis_16','grasshopper_16')," +
            "('pig', 'baboy','baboy_17','pig_17')," +
            "('duck', 'pato','pato_22','duck_22')," +
            "('mosquito', 'ilam','ilam_12','mosquito_12')," +
            "('frog', 'ambak','ambak_18','frog_18')," +
            "('morning', 'kamdag','kamdag_25','morning_25')," +
            "('cat', 'miyaw','miyaw_23','cat_23')," +
            "('calamansi', 'limonsito','limonsito_14','calamansi_14')," +
            "('kitten', 'kuku','kuku_21','kitten_21')," +
            "('liar', 'bakakun','bakakun_4','bakakun_4')," +
            "('dark', 'madlum','madlum_10','madlum_10')," +
            "('smells', 'mawu','mawu_6','smells_6')," +
            "('time', 'oras','oras_7','oras_7')," +
            "('jealous', 'pandaway','pandaway_5','pandaway_5')," +
            "('strong', 'kusugan','kusugay_1','strong_1')," +
            "('evening', 'Miga kagabi da','miga_kagabi_da_11','evening_11')," +
            "('strsunbird', 'tamsi','tamsi_2','sunbird_2')," +
            "('animals', 'ya mga ayup 8','ya_mga_ayup_8','animals_8')," +
            "('afternoon', 'ambung','ambung_9','afternoon_9')," +
            "('hungry', 'imagutom','imagutom_3','hungry_3')," +
            "('squid', 'nukos','nukus_20','squid_20') ")
    void insert();

    @Query("SELECT * FROM speech_to_speech where english =:english")
    boolean checkIfWithResult(String english);

    @Query("SELECT * FROM speech_to_speech where english LIKE :english")
    SpeechToSpeechEntity getSpeechToSpeechByEnglish(String english);

    @Query("SELECT * FROM speech_to_speech where id = 1")
    SpeechToSpeechEntity getSpeechToSpeechById();

    @Query("SELECT * FROM game_fill LIMIT 20")
    LiveData<List<GameFillEntity>> getLiveQuestions();
}