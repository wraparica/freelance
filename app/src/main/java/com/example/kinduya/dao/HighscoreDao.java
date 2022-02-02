package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;
import com.example.kinduya.entities.HighscoreEntity;

import java.util.List;

@Dao
public interface HighscoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<HighscoreEntity> highscoreEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(HighscoreEntity highscoreEntity);

    @Query("DELETE FROM highschore")
    void deleteHighscores();

    @Query("INSERT OR REPLACE INTO game_fill (id, question, answer, image, correctQuestion) " +
            " VALUES (1, 'M _ N G O', 'A', 'mango', 'M A N G O')," +
            " (2, 'A _ P L E', 'P', 'apple', 'A P P L E')," +
            "(3, 'B A _ L ', 'L', 'ball', 'B A L L')," +
            "(4, 'C A _', 'T', 'cat', 'C A T') ")
    void insert();

    @Query("SELECT * FROM highschore WHERE game = :game ORDER BY score DESC")
    List<HighscoreEntity> getHighscoresByGame(int game);

    @Query("SELECT * FROM game_fill LIMIT 20")
    LiveData<List<GameFillEntity>> getLiveQuestions();
}