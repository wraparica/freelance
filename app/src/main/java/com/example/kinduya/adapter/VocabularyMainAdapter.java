package com.example.kinduya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;
import com.example.kinduya.entities.AppDataEntity;

public class VocabularyMainAdapter extends ListAdapter<AppDataEntity, VocabularyMainViewHolder> {
    Context context;
    int category;
    public VocabularyMainAdapter(ItemOnClickListener itemOnClickListener, int category) {
        super(AppDataEntity.DIFF_CALLBACK);
        this.itemOnClickListener = itemOnClickListener;
        this.category = category;
    }
    private ItemOnClickListener itemOnClickListener;
    public interface ItemOnClickListener{
        void onItemClicked(AppDataEntity appDataEntity, int position);
    }

    @NonNull
    @Override
    public VocabularyMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vocabulary_layout, parent, false);
        return new VocabularyMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyMainViewHolder holder, int position) {
        AppDataEntity item = getItem(position);
        if (item == null){
            return;
        }
        if (category == 5){
            holder.tvVocabulary.setText(item.getEnglish().toUpperCase());
        }else {
            holder.tvVocabulary.setText(item.getEnglish_phrase().toUpperCase());
        }

        holder.tvVocabulary.setOnClickListener(view -> itemOnClickListener.onItemClicked(item, position));
    }

    public int getImage(String imageName) {

        return this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());
    }
}

class VocabularyMainViewHolder extends RecyclerView.ViewHolder {
    TextView tvVocabulary;

    public VocabularyMainViewHolder(View itemView){
        super(itemView);
        tvVocabulary = itemView.findViewById(R.id.tvVocabularyWord);
    }
}