package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main extends AppCompatActivity {
    private ImageView coffeeImage;
    private ImageView sandwichImage;
    private ImageView donutImage;
    private Button currOrder, allOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        coffeeImage = findViewById(R.id.coffeeImage);
        coffeeImage.setImageResource(R.drawable.coffee_main);

        sandwichImage = findViewById(R.id.sandwichImage);
        sandwichImage.setImageResource(R.drawable.sandwich_main);

        donutImage = findViewById(R.id.donutImage);
        donutImage.setImageResource(R.drawable.donut_main);

        currOrder = findViewById(R.id.currOrder);
        allOrders = findViewById(R.id.allOrders);

        coffeeImage.setOnClickListener(v -> openingCoffeeGUI());
        sandwichImage.setOnClickListener(v -> openingSandwichGUI());
        donutImage.setOnClickListener(v -> openingDonutGUI());
        currOrder.setOnClickListener(v -> openingCurrentOrderGUI());
        allOrders.setOnClickListener(v -> openingAllOrdersGUI());

    }
    public void openingCoffeeGUI(){
        Intent intent = new Intent(this, CoffeeGUI.class);
//        myIntent.putExtra("key", value); //Optional parameters for transferring data between GUIs
        main.this.startActivity(intent);
    }
    public void openingSandwichGUI(){
        Intent intent = new Intent(this, SandwichGUI.class);
//        myIntent.putExtra("key", value); //Optional parameters for transferring data between GUIs
        main.this.startActivity(intent);
    }
    public void openingDonutGUI(){
        Intent intent = new Intent(this, DonutGUI.class);
//        myIntent.putExtra("key", value); //Optional parameters for transferring data between GUIs
        main.this.startActivity(intent);
    }
    public void openingCurrentOrderGUI(){
        Intent intent = new Intent(this, CurrentOrderGUI.class);
//        myIntent.putExtra("key", value); //Optional parameters for transferring data between GUIs
        main.this.startActivity(intent);
    }
    public void openingAllOrdersGUI(){
        Intent intent = new Intent(this, AllOrdersGUI.class);
//        myIntent.putExtra("key", value); //Optional parameters for transferring data between GUIs
        main.this.startActivity(intent);
    }
}