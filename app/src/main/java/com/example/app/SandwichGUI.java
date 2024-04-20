package com.example.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class SandwichGUI extends AppCompatActivity {
    private RadioButton bagel, wheat, sourdough, beef, fish, chicken;
    private CheckBox lettuce, tomatoes, cheese, onion;
    private ImageView sandwichBanner;
    private RadioGroup breadGroup, proteinGroup;

    private TextView sandwichSubtotal;
    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sandwich_gui);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bagel = findViewById(R.id.bagel);
        wheat = findViewById(R.id.wheat);
        sourdough = findViewById(R.id.sourdough);
        beef = findViewById(R.id.beef);
        fish = findViewById(R.id.fish);
        chicken = findViewById(R.id.chicken);
        lettuce = findViewById(R.id.lettuce);
        tomatoes = findViewById(R.id.tomatoes);
        cheese = findViewById(R.id.cheese);
        onion = findViewById(R.id.onion);
        sandwichBanner = findViewById(R.id.sandwichBanner);
        sandwichSubtotal = findViewById(R.id.sandwichSubtotal);
        breadGroup = findViewById(R.id.breadGroup);
        proteinGroup = findViewById(R.id.proteinGroup);
        order = findViewById(R.id.addToOrderSandwich);
        // Image change event listeners
        bagel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sandwichBanner.setImageResource(R.drawable.bagel);
            }
        });

        wheat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sandwichBanner.setImageResource(R.drawable.wheat);
            }
        });

        sourdough.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sandwichBanner.setImageResource(R.drawable.sourdough);
            }
        });
        // Event listeners for updating subtotal
        // Event listeners for CheckBoxes
        lettuce.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextField());
        tomatoes.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextField());
        cheese.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextField());
        onion.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextField());
        // Event listeners for RadioGroups
        breadGroup.setOnCheckedChangeListener((group, checkedId) -> updateTextField());
        proteinGroup.setOnCheckedChangeListener((group, checkedId) -> updateTextField());
        //Order event listener
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddToOrder();
            }
        });

    }
    private List<SandwichAddOns> getAddOns(){
        List<SandwichAddOns> addOns= new ArrayList<>();
        if(lettuce.isChecked()){
            addOns.add(SandwichAddOns.LETTUCE);
        }
        if(tomatoes.isChecked()){
            addOns.add(SandwichAddOns.TOMATOES);
        }
        if(cheese.isChecked()){
            addOns.add(SandwichAddOns.CHEESE);
        }
        if(onion.isChecked()){
            addOns.add(SandwichAddOns.ONIONS);
        }
        return  addOns;
    }

    private  Protein getProtein(){
        if(beef.isChecked()){
            return Protein.BEEF;
        }
        if(fish.isChecked()){
            return  Protein.FISH;
        }
        if(chicken.isChecked()){
            return  Protein.CHICKEN;
        }
        return null;
    }

    private Bread getBread(){
        if(bagel.isChecked()){
            return Bread.BAGEL;
        }
        if(wheat.isChecked()){
            return  Bread.WHEATBREAD;
        }
        if(sourdough.isChecked()){
            return  Bread.SOURDOUGH;
        }
        return null;
    }

    private MenuItem sandwichMaker(){
        Bread bread = getBread();
        Protein protein = getProtein();
        List<SandwichAddOns> addOns = getAddOns();
        return new Sandwich(bread, protein, addOns);
    }
    @SuppressLint("SetTextI18n")
    private void updateTextField() {
        if (breadGroup.getCheckedRadioButtonId() != -1 && proteinGroup.getCheckedRadioButtonId() != -1) {
            // One option from each group is selected
            MenuItem sandwich = sandwichMaker();
            double price = sandwich.price();
            price = Math.round(price * 100.0) / 100.0;
            sandwichSubtotal.setText("$" + Double.toString(price));
        } else {
            // At least one option is not selected
            sandwichSubtotal.setText("");
        }
    }

    private void handleAddToOrder(){
        if(breadGroup.getCheckedRadioButtonId() == -1){
            noBreadSelectedAlert();
            return;
        }
        if(proteinGroup.getCheckedRadioButtonId() == -1){
            noProteinSelectedAlert();
            return;
        }
        MenuItem sandwich = sandwichMaker();
        //Implement later with Singelton design
    }
    private void noBreadSelectedAlert() {
        Toast.makeText(this, "Please select a type of bread.", Toast.LENGTH_SHORT).show();
    }

    private void noProteinSelectedAlert() {
        Toast.makeText(this, "Please select a protein.", Toast.LENGTH_SHORT).show();
    }
}