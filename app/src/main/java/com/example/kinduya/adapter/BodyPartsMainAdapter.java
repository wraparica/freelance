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

import com.example.kinduya.R;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.GameFillChoicesEntity;

public class BodyPartsMainAdapter extends ListAdapter<AppDataEntity, BodyPartsViewHolder> {

    Context context;
    private BodyPartsOnClickListener bodyPartsOnClickListener;
    public BodyPartsMainAdapter(Context context, BodyPartsOnClickListener bodyPartsOnClickListener) {
        super(AppDataEntity.DIFF_CALLBACK);
        this.context = context;
        this.bodyPartsOnClickListener = bodyPartsOnClickListener;
    }

    public interface BodyPartsOnClickListener{
            void onBodyPartsClicked(AppDataEntity appDataEntity);
    }

    @NonNull
    @Override
    public BodyPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.body_parts_menu_layout, parent, false);
        return new BodyPartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartsViewHolder holder, int position) {
        AppDataEntity item = getItem(position);
        if (item == null){
            return;
        }
        int id = context.getResources().getIdentifier(
                item.getImage(), "drawable", context.getPackageName());
        holder.imgBodyPart.setBackgroundResource(id);
        holder.imgBodyPart.setOnClickListener(view -> bodyPartsOnClickListener.onBodyPartsClicked(item));
    }
}

class BodyPartsViewHolder extends RecyclerView.ViewHolder {
    ImageView imgBodyPart;

    public BodyPartsViewHolder(View itemView){
        super(itemView);
        imgBodyPart = itemView.findViewById(R.id.imgBodyPart);
    }
}