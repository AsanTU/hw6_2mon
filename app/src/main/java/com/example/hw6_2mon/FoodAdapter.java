package com.example.hw6_2mon;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw6_2mon.databinding.ItemFoodBinding;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodModel> list;
    private OnFoodClickListener listener;
    private int selectedPosition = -1;

    public interface OnFoodClickListener{
        void onFoodClick(int position);
    }

    public FoodAdapter(ArrayList<FoodModel> list, OnFoodClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setSelectedPosition(int position){
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemFoodBinding binding;


        public ViewHolder(@NonNull ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FoodModel food, boolean isSelected){
            binding.foodImage.setImageResource(food.getImage());
            binding.foodName.setText(food.getName());
            binding.foodPrice.setText("$" + food.getPrice());
            binding.foodRating.setRating(food.getRating());

            binding.getRoot().setBackgroundColor(isSelected ? Color.LTGRAY : Color.TRANSPARENT);
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
        FoodModel food = list.get(position);
        holder.bind(food, position == selectedPosition);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onFoodClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
