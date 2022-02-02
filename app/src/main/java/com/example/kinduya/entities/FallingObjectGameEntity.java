package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;
@Entity(tableName = "falling_object_game")
public class FallingObjectGameEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "category")
    private long category = 0;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "image")
    private String image;

    public FallingObjectGameEntity(String question, String answer, String image, long category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Ignore
    public static final DiffUtil.ItemCallback<FallingObjectGameEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<FallingObjectGameEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull FallingObjectGameEntity oldItem, @NonNull FallingObjectGameEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.answer, newItem.answer)
                    && Objects.equals(oldItem.category, newItem.category)
                    && Objects.equals(oldItem.question, newItem.question);
        }

        @Override
        public boolean areContentsTheSame(@NonNull FallingObjectGameEntity oldItem, @NonNull FallingObjectGameEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.answer, newItem.answer)
                    && Objects.equals(oldItem.category, newItem.category)
                    && Objects.equals(oldItem.question, newItem.question);
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}

