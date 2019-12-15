package com.macpherson.brandon.mainproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/*
RecyclerView Adapter
*/
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<model> list;

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView item, location;
        public ImageView photo;

        public Holder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            location = itemView.findViewById(R.id.location);
            photo = itemView.findViewById(R.id.photo);
        }
    }

    public Adapter(List<model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.item.setText(list.get(position).getItem());
        holder.location.setText(list.get(position).getLocation());
        holder.photo.setBackgroundResource(list.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

