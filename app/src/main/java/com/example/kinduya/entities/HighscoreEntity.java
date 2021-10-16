package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "highschore")
public class HighscoreEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "score")
    private int score;


    public HighscoreEntity(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Ignore
    public static final DiffUtil.ItemCallback<HighscoreEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<HighscoreEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull HighscoreEntity oldItem, @NonNull HighscoreEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.score, newItem.score)
                    && Objects.equals(oldItem.name, newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull HighscoreEntity oldItem, @NonNull HighscoreEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.score, newItem.score)
                    && Objects.equals(oldItem.name, newItem.name);
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
