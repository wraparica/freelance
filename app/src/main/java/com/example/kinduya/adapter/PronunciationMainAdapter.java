package com.example.kinduya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kinduya.R;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.GameFillChoicesEntity;

public class PronunciationMainAdapter extends ListAdapter<AppDataEntity, PronunciationViewHolder> {

    Context context;
    private ItemOnClickListener itemOnClickListener;
    public PronunciationMainAdapter(Context context, ItemOnClickListener itemOnClickListener) {
        super(AppDataEntity.DIFF_CALLBACK);
        this.context = context;
        this.itemOnClickListener = itemOnClickListener;
    }

    public interface ItemOnClickListener{
        void onItemClicked(AppDataEntity appDataEntity, int position);
    }

    @NonNull
    @Override
    public PronunciationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.body_parts_menu_layout, parent, false);
        return new PronunciationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PronunciationViewHolder holder, int position) {
        AppDataEntity item = getItem(position);
        if (item == null){
            return;
        }

        Glide.with(this.context).load(getImage(item.getImage())).into(holder.imgBodyPart);
        holder.imgBodyPart.setOnClickListener(view -> itemOnClickListener.onItemClicked(item, position));
    }

    public int getImage(String imageName) {

        return this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());
    }
}

class PronunciationViewHolder extends RecyclerView.ViewHolder {
    ImageView imgBodyPart;

    public PronunciationViewHolder(View itemView){
        super(itemView);
        imgBodyPart = itemView.findViewById(R.id.imgBodyPart);
    }
}