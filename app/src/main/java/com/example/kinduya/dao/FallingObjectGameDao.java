package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.FallingObjectGameEntity;
import com.example.kinduya.entities.GameFillEntity;

import java.util.List;

@Dao
public interface FallingObjectGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<GameFillEntity> FallingObjectGameEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(GameFillEntity FallingObjectGameEntity);

    @Query("DELETE FROM falling_object_game")
    void deleteQuestions();

    // SHAPE 1
    // COLORS 2
    // BODYPARTS 3
    // NUMBERS 4
    @Query("INSERT OR REPLACE INTO falling_object_game (id, question, answer, image, category) " +
            " VALUES (1, 'CIRCLE','CIRCLE','circle_falling','1'),"+
            " (2,'OBLONG','OBLONG','oblong_falling','1'),"+
            " (3,'PENTAGON','PENTAGON','pentagon_falling','1'),"+
            " (4,'RECTANGLE','RECTANGLE','rectangle_falling','1'),"+
            " (5,'SQUARE','SQUARE','square_falling','1'),"+
            " (6,'STAR','STAR','star_falling','1'),"+
            " (7,'TRIANGLE','TRIANGLE','triangle_falling','1'),"+
            " (8,'HEART','HEART','heart_falling','1'),"+
            // COLORS
            " (9,'BLACK','BLACK','black_falling','2'),"+
            " (10,'BLUE','BLUE','blue_falling','2'),"+
            " (11,'BROWN','BROWN','brown_falling','2'),"+
            " (12,'GRAY','GRAY','gray_falling','2'),"+
            " (13,'ORANGE','ORANGE','orange_falling','2'),"+
            " (14,'RED','RED','red_falling','2'),"+
            " (15,'VIOLET','VIOLET','violet_falling','2'),"+
            " (16,'WHITE','WHITE','white_falling','2'),"+
            // NUMBERS
            " (17,'ONE','ONE','one_falling','4'),"+
            " (18,'TWO','TWO','two_falling','4'),"+
            " (19,'THREE','THREE','three_falling','4'),"+
            " (20,'FOUR','FOUR','four_falling','4'),"+
            " (21,'FIVE','FIVE','five_falling','4'),"+
            " (22,'SIX','SIX','six_falling','4'),"+
            " (23,'SEVEN','SEVEN','seven_falling','4'),"+
            " (24,'EIGHT','EIGHT','eigth_falling','4'),"+
            " (25,'NINE','NINE','nine_falling','4'),"+
            " (26,'TEN','TEN','ten_falling','4')"
    )
    void insert();

    @Query("SELECT * FROM falling_object_game ORDER BY RANDOM() LIMIT 15")
    List<FallingObjectGameEntity> getQuestions();

    @Query("SELECT * FROM falling_object_game LIMIT 20")
    LiveData<List<FallingObjectGameEntity>> getLiveQuestions();
}