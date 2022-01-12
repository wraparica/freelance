package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "speech_to_speech")
public class SpeechToSpeechEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "english")
    private String english;

    @ColumnInfo(name = "mandaya")
    private String mandaya;

    @ColumnInfo(name = "english_file")
    private String english_file;

    @ColumnInfo(name = "mandaya_file")
    private String mandaya_file;


    public SpeechToSpeechEntity(String english, String mandaya, String mandaya_file, String english_file) {
        this.english = english;
        this.mandaya = mandaya;
        this.mandaya_file = mandaya_file;
        this.english_file = english_file;
    }

    @Ignore
    public static final DiffUtil.ItemCallback<SpeechToSpeechEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<SpeechToSpeechEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull SpeechToSpeechEntity oldItem, @NonNull SpeechToSpeechEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.english, newItem.english)
                    && Objects.equals(oldItem.english_file, newItem.english_file)
                    && Objects.equals(oldItem.mandaya_file, newItem.mandaya_file)
                    && Objects.equals(oldItem.mandaya, newItem.mandaya);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SpeechToSpeechEntity oldItem, @NonNull SpeechToSpeechEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.english, newItem.english)
                    && Objects.equals(oldItem.english_file, newItem.english_file)
                    && Objects.equals(oldItem.mandaya_file, newItem.mandaya_file)
                    && Objects.equals(oldItem.mandaya, newItem.mandaya);
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMandaya() {
        return mandaya;
    }

    public void setMandaya(String mandaya) {
        this.mandaya = mandaya;
    }

    public String getEnglish_file() {
        return english_file;
    }

    public void setEnglish_file(String english_file) {
        this.english_file = english_file;
    }

    public String getMandaya_file() {
        return mandaya_file;
    }

    public void setMandaya_file(String mandaya_file) {
        this.mandaya_file = mandaya_file;
    }
}
