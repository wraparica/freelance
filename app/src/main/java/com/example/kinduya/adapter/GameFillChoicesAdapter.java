package com.example.kinduya.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinduya.R;
import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;

public class GameFillChoicesAdapter extends ListAdapter<GameFillChoicesEntity, ViewHolder> {

    public GameFillChoicesAdapter() {
        super(GameFillChoicesEntity.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_fill_choices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameFillChoicesEntity item = getItem(position);
        if (item == null){
            return;
        }
        holder.button.setText(item.getChoices());
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    Button button;

    public ViewHolder(View itemView){
        super(itemView);
        button = itemView.findViewById(R.id.choices);
    }
}