package com.example.hw6_2mon;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

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
    private FoodAdapter foodAdapter;

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
        foodList.add(new FoodModel(R.drawable.chicken_burger, "Chicken Burger", 4.3f, 6.49));
        foodList.add(new FoodModel(R.drawable.fish_burger, "Fish Burger", 4.0f, 5.99));
        foodList.add(new FoodModel(R.drawable.vegguei_burger, "Veggie Burger", 4.6f, 6.75));
        foodList.add(new FoodModel(R.drawable.cheese_burger, "Cheese Burger", 4.8f, 7.25));
        foodList.add(new FoodModel(R.drawable.spicy_burger, "Spicy Burger", 4.1f, 6.89));
        foodList.add(new FoodModel(R.drawable.double_beef_burger, "Double Beef Burger", 4.7f, 8.99));

        foodAdapter = new FoodAdapter(foodList, position -> {
            FoodModel selectedFood = foodList.remove(position);
            foodList.add(0, selectedFood);
            foodAdapter.setSelectedPosition(0);
            foodAdapter.notifyDataSetChanged();
        });
        binding.foodRecyclerView.setAdapter(foodAdapter);

        Glide.with(this).load(R.drawable.ic_avatar).circleCrop().into(binding.avatarImageView);

        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim().toLowerCase();
                for (int i = 0; i < foodList.size(); i++) {
                    if (foodList.get(i).getName().toLowerCase().contains(query)){
                        FoodModel food = foodList.remove(i);
                        foodList.add(0, food);
                        foodAdapter.setSelectedPosition(0);
                        foodAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}