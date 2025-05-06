package com.example.hw6_2mon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw6_2mon.databinding.ItemFoodBinding;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodModel> list;

    public FoodAdapter(ArrayList<FoodModel> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemFoodBinding binding;


        public ViewHolder(@NonNull ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFoodBinding binding = ItemFoodBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodModel model = list.get(position);
        holder.binding.foodImage.setImageResource(model.getImage());
        holder.binding.foodName.setText(model.getName());
        holder.binding.foodPrice.setText("$" + model.getPrice());
        holder.binding.foodRating.setRating(model.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
