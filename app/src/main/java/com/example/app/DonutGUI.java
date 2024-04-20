package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DonutGUI extends AppCompatActivity {
    private Spinner type;
    private ImageView donutBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donut_gui);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        type = findViewById(R.id.donutType);
        donutBanner = findViewById(R.id.donutBanner);

        String[] sizeOptions = {"yeast donuts", "cake donuts", "donut holes"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(sizeAdapter);

        type.setSelection(1);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                donutImageSetter();
            }
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }
    private void donutImageSetter(){
        String donutType = type.getSelectedItem().toString();
        switch (donutType) {
            case "yeast donuts":
                donutBanner.setImageResource(R.drawable.yeast_donut);
                break;
            case "cake donuts":
                donutBanner.setImageResource(R.drawable.cake_donut);
                break;
            case "donut holes":
                donutBanner.setImageResource(R.drawable.donut_holes);
                break;
        }
    }
}