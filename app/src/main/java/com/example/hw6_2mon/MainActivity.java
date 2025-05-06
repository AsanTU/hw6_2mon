package com.example.hw6_2mon;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hw6_2mon.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        LinearLayoutManager foodLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.foodRecyclerView.setLayoutManager(foodLayoutManager);

        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel(R.drawable.ic_burger, "Burger"));
        categoryList.add(new CategoryModel(R.drawable.ic_pizza, "Pizza"));
        categoryList.add(new CategoryModel(R.drawable.ic_chicken, "Chicken"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        binding.categoryRecyclerView.setAdapter(categoryAdapter);

        ArrayList<FoodModel> foodList = new ArrayList<>();
        foodList.add(new FoodModel(R.drawable.salad_burger, "Salad Burger", 4.5f, 6.99));
        foodList.add(new FoodModel(R.drawable.beef_burger, "Beef Burger", 4.2f, 7.49));

        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        binding.foodRecyclerView.setAdapter(foodAdapter);

        Glide.with(this).load(R.drawable.ic_avatar).circleCrop().into(binding.avatarImageView);
    }
}