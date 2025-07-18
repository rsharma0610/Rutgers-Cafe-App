package com.example.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the coffee view
 */
public class CoffeeGUI extends AppCompatActivity {
    private Spinner size;
    private Spinner quantity;
    private CheckBox sweetCream, mocha, frenchVanilla, caramel, irishCream;
    //private ImageView coffeeBanner;
    private Button order;
    private TextView coffeeSubtotal;

    /**
     * This method is called when the coffee view is first created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coffee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Connect all variables to their ids in the xml file
        order = findViewById(R.id.addToOrderCoffee);



        size = findViewById(R.id.coffeeSize);
        quantity = findViewById(R.id.coffeeQuantity);

        sweetCream = findViewById(R.id.sweetCream);
        mocha = findViewById(R.id.mocha);
        frenchVanilla = findViewById(R.id.frenchVanilla);
        caramel = findViewById(R.id.caramel);
        irishCream = findViewById(R.id.irishCream);

        coffeeSubtotal = findViewById(R.id.coffeeSubtotal);
        //Populate image and spinner views


        String[] sizeOptions = {"Short", "Tall", "Grande", "Venti"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(sizeAdapter);

        String[] quantityOptions = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantityOptions);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantityAdapter);

        // Set default values for the spinners
        size.setSelection(0); // Select "Short" by default
        quantity.setSelection(0); // Select "1" by default
        //Event listeners for the views
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTextArea();
            }
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTextArea();
            }
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
        sweetCream.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextArea());
        mocha.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextArea());
        frenchVanilla.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextArea());
        caramel.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextArea());
        irishCream.setOnCheckedChangeListener((buttonView, isChecked) -> updateTextArea());

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddToOrder();
            }
        });
    }

    /**
     * Retrieves the selected cup size
     * @param cupSize
     * @return
     */
    private CoffeeSize getCupSize(String cupSize){
        switch (cupSize) {
            case "Short":
                return CoffeeSize.SHORT;
            case "Tall":
                return CoffeeSize.TALL;
            case "Grande":
                return CoffeeSize.GRANDE;
            case "Venti":
                return CoffeeSize.VENTI;
        }
        return null;
    }

    /**
     * Retrieves the selected add-ins
     * @return
     */
    private List<CoffeeAddIns> getAddIns(){
        List<CoffeeAddIns> addOns= new ArrayList<>();
        if(sweetCream.isChecked()){
            addOns.add(CoffeeAddIns.SWEET_CREAM);
        }
        if(mocha.isChecked()){
            addOns.add(CoffeeAddIns.MOCHA);
        }
        if(frenchVanilla.isChecked()){
            addOns.add(CoffeeAddIns.FRENCH_VANILLA);
        }
        if(caramel.isChecked()){
            addOns.add(CoffeeAddIns.CARAMEL);
        }
        if(irishCream.isChecked()){
            addOns.add(CoffeeAddIns.IRISH_CREAM);
        }
        return  addOns;
    }

    /**
     * Makes the coffee menu item object
     * @return
     */
    private Coffee coffeeMaker(){
        String selectedQuantity = quantity.getSelectedItem().toString();
        int coffeeCount = Integer.parseInt(selectedQuantity);
        CoffeeSize cupSize = getCupSize(size.getSelectedItem().toString());
        List<CoffeeAddIns> addIns = getAddIns();
        return new Coffee(coffeeCount, cupSize, addIns);
    }

    /**
     * UPdates the price fields based on the coffee order specs
     */
    @SuppressLint("SetTextI18n")
    private void updateTextArea() {
        MenuItem coffee = coffeeMaker();
        double cost = coffee.price();
        cost = Math.round(cost * 100.0) / 100.0;
        coffeeSubtotal.setText("$" + Double.toString(cost));
    }

    /**
     * Adds the coffee object to the order array list
     */
    private void handleAddToOrder(){
        MenuItem coffee = coffeeMaker();
        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();
        currentOrderSingleton.getCurrentOrder().getItems().add(coffee);
        orderPlacedAlert();
    }

    /**
     * Alert to signal that the coffee was added to the order
     */
    private void orderPlacedAlert(){
        Toast.makeText(this, "Coffee added to order.", Toast.LENGTH_SHORT).show();
    }

}