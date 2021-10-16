package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "game_fill_choices")
public class GameFillChoicesEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "question_id")
    private long question_id;

    @ColumnInfo(name = "choices")
    private String choices;

    public GameFillChoicesEntity(long question_id, String choices) {
        this.question_id = question_id;
        this.choices = choices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    @Ignore
    public static final DiffUtil.ItemCallback<GameFillChoicesEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<GameFillChoicesEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull GameFillChoicesEntity oldItem, @NonNull GameFillChoicesEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.choices, newItem.choices)
                    && Objects.equals(oldItem.question_id, newItem.question_id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GameFillChoicesEntity oldItem, @NonNull GameFillChoicesEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.choices, newItem.choices)
                    && Objects.equals(oldItem.question_id, newItem.question_id);
        }
    };
}
