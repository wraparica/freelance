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
import com.example.kinduya.entities.VocabularyWordsMenuObject;
import com.example.kinduya.entities.VocabularyWordsMenuObject;

public class VocabularyWordsMenuAdapter extends ListAdapter<VocabularyWordsMenuObject, VocabularyWordsMenuViewHolder> {
    Context context;
    public VocabularyWordsMenuAdapter(ItemOnClickListener itemOnClickListener, Context context) {
        super(VocabularyWordsMenuObject.DIFF_CALLBACK);
        this.itemOnClickListener = itemOnClickListener;
        this.context = context;
    }
    private ItemOnClickListener itemOnClickListener;
    public interface ItemOnClickListener{
        void onItemClicked(VocabularyWordsMenuObject VocabularyWordsMenuObject, int position);
    }

    @NonNull
    @Override
    public VocabularyWordsMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vocabulary_words_menu_layout, parent, false);
        return new VocabularyWordsMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyWordsMenuViewHolder holder, int position) {
        VocabularyWordsMenuObject item = getItem(position);
        if (item == null){
            return;
        }

        Glide.with(this.context).load(getImage(item.getImage())).into(holder.icon);
        holder.icon.setOnClickListener(view -> itemOnClickListener.onItemClicked(item, position));
    }   

    public int getImage(String imageName) {

        return this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());
    }
}

class VocabularyWordsMenuViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;

    public VocabularyWordsMenuViewHolder(View itemView){
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
    }
}