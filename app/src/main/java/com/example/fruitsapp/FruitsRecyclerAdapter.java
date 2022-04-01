package com.example.fruitsapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitsRecyclerAdapter extends RecyclerView.Adapter<FruitsRecyclerAdapter.FruitsRecyclerViewHolder> {
    private FruitsDB fruitsDB;
    private LayoutInflater inflater;

    public FruitsRecyclerAdapter(FruitsDB fruitsDB, LayoutInflater inflater) {
        this.fruitsDB = fruitsDB;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public FruitsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fruit_list_item, parent, false);
        return new FruitsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsRecyclerViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return fruitsDB.getDBSize();
    }

    public class FruitsRecyclerViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final ImageView avatar;
        private View selector;

        public FruitsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            avatar = itemView.findViewById(R.id.avatar);
            selector = itemView.findViewById(R.id.selector_overlay);
        }

        public void bind() {
            Fruit fruit = fruitsDB.getFruit(getAdapterPosition());
            name.setText(fruit.getName());
            description.setText(fruit.getDescription());
            avatar.setImageResource(fruit.getAvatarId());
        }
    }
}
