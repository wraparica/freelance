package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "app_data")
public class AppDataEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id = 0;

    @ColumnInfo(name = "mandaya")
    private String mandaya;

    @ColumnInfo(name = "english")
    private String english;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "english_phrase")
    private String english_phrase;

    @ColumnInfo(name = "mandaya_phrase")
    private String mandaya_phrase;

    public AppDataEntity(String mandaya, String english, int category, String image, String english_phrase, String mandaya_phrase) {
        this.mandaya = mandaya;
        this.english = english;
        this.category = category;
        this.image = image;
        this.english_phrase = english_phrase;
        this.mandaya_phrase = mandaya_phrase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMandaya() {
        return mandaya;
    }

    public void setMandaya(String mandaya) {
        this.mandaya = mandaya;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Ignore
    public static final DiffUtil.ItemCallback<AppDataEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<AppDataEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull AppDataEntity oldItem, @NonNull AppDataEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.mandaya, newItem.mandaya)
                    && Objects.equals(oldItem.english, newItem.english)
                    && Objects.equals(oldItem.category, newItem.category);
        }

        @Override
        public boolean areContentsTheSame(@NonNull AppDataEntity oldItem, @NonNull AppDataEntity newItem) {
            return Objects.equals(oldItem.id, newItem.id)
                    && Objects.equals(oldItem.mandaya, newItem.mandaya)
                    && Objects.equals(oldItem.english, newItem.english)
                    && Objects.equals(oldItem.category, newItem.category);
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEnglish_phrase() {
        return english_phrase;
    }

    public void setEnglish_phrase(String english_phrase) {
        this.english_phrase = english_phrase;
    }

    public String getMandaya_phrase() {
        return mandaya_phrase;
    }

    public void setMandaya_phrase(String mandaya_phrase) {
        this.mandaya_phrase = mandaya_phrase;
    }
}
