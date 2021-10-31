package com.example.kinduya.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

public class CulturalMusicMainObject {

    private String name;
    private String image;
    private int category;


    public CulturalMusicMainObject(String name, String image, int category) {
        this.name = name;
        this.image = image;
        this.category = category;
    }

    @Ignore
    public static final DiffUtil.ItemCallback<CulturalMusicMainObject> DIFF_CALLBACK = new DiffUtil.ItemCallback<CulturalMusicMainObject>() {
        @Override
        public boolean areItemsTheSame(@NonNull CulturalMusicMainObject oldItem, @NonNull CulturalMusicMainObject newItem) {
            return Objects.equals(oldItem.image, newItem.image)
                    && Objects.equals(oldItem.name, newItem.name)
                    && Objects.equals(oldItem.category, newItem.category);
        }

        @Override
        public boolean areContentsTheSame(@NonNull CulturalMusicMainObject oldItem, @NonNull CulturalMusicMainObject newItem) {
            return Objects.equals(oldItem.image, newItem.image)
                    && Objects.equals(oldItem.name, newItem.name)
                    && Objects.equals(oldItem.category, newItem.category);
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
