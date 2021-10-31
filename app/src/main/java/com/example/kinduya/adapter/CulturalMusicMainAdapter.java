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
import com.example.kinduya.entities.CulturalMusicMainObject;
import com.example.kinduya.entities.CulturalMusicMainObject;

public class CulturalMusicMainAdapter extends ListAdapter<CulturalMusicMainObject, CulturalMusicMainViewHolder> {
    Context context;
    public CulturalMusicMainAdapter(ItemOnClickListener itemOnClickListener, Context context) {
        super(CulturalMusicMainObject.DIFF_CALLBACK);
        this.itemOnClickListener = itemOnClickListener;
        this.context = context;
    }
    private ItemOnClickListener itemOnClickListener;
    public interface ItemOnClickListener{
        void onItemClicked(CulturalMusicMainObject culturalMusicMainObject, int position);
    }

    @NonNull
    @Override
    public CulturalMusicMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vocabulary_words_menu_layout, parent, false);
        return new CulturalMusicMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CulturalMusicMainViewHolder holder, int position) {
        CulturalMusicMainObject item = getItem(position);
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

class CulturalMusicMainViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;

    public CulturalMusicMainViewHolder(View itemView){
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
    }
}