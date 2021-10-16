package com.example.kinduya.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinduya.R;
import com.example.kinduya.entities.HighscoreEntity;

public class HighscoreAdapter extends ListAdapter<HighscoreEntity, HighscoreViewHolder> {

    public HighscoreAdapter() {
        super(HighscoreEntity.DIFF_CALLBACK);
    }



    @NonNull
    @Override
    public HighscoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.highscore_layout, parent, false);
        return new HighscoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighscoreViewHolder holder, int position) {
        HighscoreEntity item = getItem(position);
        if (item == null){
            return;
        }
        holder.name.setText(item.getName());
        holder.place.setText(String.valueOf(position + 1));
        holder.score.setText(String.valueOf(item.getScore()));
    }

}

class HighscoreViewHolder extends RecyclerView.ViewHolder {
    TextView name, place, score;

    public HighscoreViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.Username);
        place = itemView.findViewById(R.id.place);
        score = itemView.findViewById(R.id.score);
    }
}