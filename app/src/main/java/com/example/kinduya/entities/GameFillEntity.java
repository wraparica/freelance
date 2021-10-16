package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "game_fill")
public class GameFillEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "englishTranslation")
    private String englishTranslation;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "correctQuestion")
    private String correctQuestion;

    @ColumnInfo(name = "image")
    private String image;

    public GameFillEntity(String question, String englishTranslation, String answer, String image, String correctQuestion) {
        this.question = question;
        this.englishTranslation = englishTranslation;
        this.answer = answer;
        this.image = image;
        this.correctQuestion = correctQuestion;
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
    public static final DiffUtil.ItemCallback<GameFillEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<GameFillEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull GameFillEntity oldItem, @NonNull GameFillEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.answer, newItem.answer)
                    && Objects.equals(oldItem.question, newItem.question);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GameFillEntity oldItem, @NonNull GameFillEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.answer, newItem.answer)
                    && Objects.equals(oldItem.question, newItem.question);
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCorrectQuestion() {
        return correctQuestion;
    }

    public void setCorrectQuestion(String correctQuestion) {
        this.correctQuestion = correctQuestion;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }
}
