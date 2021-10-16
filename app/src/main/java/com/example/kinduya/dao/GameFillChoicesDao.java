package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.GameFillChoicesEntity;

import java.util.List;

@Dao
public interface GameFillChoicesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<GameFillChoicesEntity> gameFillChoicesEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(GameFillChoicesEntity gameFillChoicesEntity);

    @Query("SELECT * FROM game_fill_choices WHERE question_id = :questionId")
    LiveData<List<GameFillChoicesEntity>> getChoices(long questionId);

    @Query("SELECT * FROM game_fill_choices WHERE question_id = :questionId")
    List<GameFillChoicesEntity> getChoicesTest(long questionId);

    @Query("DELETE FROM game_fill_choices")
    void deleteChoices();

    @Query("INSERT OR REPLACE INTO game_fill_choices (question_id, choices) " +
            " VALUES " +
            "(1, 'W'), " +
            "(1, 'T'), " +
            "(1, 'R'), " +
            "(1, 'L'), " +
            "(2, 'P'), " +
            "(2, 'S'), " +
            "(2, 'K'), " +
            "(2, 'M'), " +
            "(3, 'I'), " +
            "(3, 'U'), " +
            "(3, 'O'), " +
            "(3, 'E'), " +
            "(4, 'A'), " +
            "(4, 'I'), " +
            "(4, 'U'), " +
            "(4, 'O')")
    void insert();
}
