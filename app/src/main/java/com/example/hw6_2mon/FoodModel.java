package com.example.hw6_2mon;

public class FoodModel {
    int image;
    String name;
    float rating;
    double price;

    public FoodModel(int image, String name, float rating, double price) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }
}
