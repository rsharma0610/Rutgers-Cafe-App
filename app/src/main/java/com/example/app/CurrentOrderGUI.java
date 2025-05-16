package com.example.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.material3.TextFieldColors;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * The current order class controls the current order screen
 */
public class CurrentOrderGUI extends AppCompatActivity {
    private ListView display;
    private Button remove, order;
    private TextView subtotal, tax, total;

    int selectedIndex = -1;

    /**
     * This method is run when the view is created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_order_gui);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        display = findViewById(R.id.currentOrderDisplay);

        remove = findViewById(R.id.removeCurrentOrder);
        order = findViewById(R.id.placeCurrentOrder);

        subtotal = findViewById(R.id.currSubtotal);
        tax = findViewById(R.id.currSalexTax);
        total = findViewById(R.id.currTotal);

        display.setOnItemClickListener((parent, view, position, id) -> {
            // Store the index of the selected item
            selectedIndex = position;
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

        populateOrderView();
    }

    /**
     * Populates the list view with the items in the order
     */
    private void populateOrderView(){
        ArrayList<String> orderList = new ArrayList<>();
        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();
        for(MenuItem menuItem : currentOrderSingleton.getCurrentOrder().getItems()){
            orderList.add(menuItem.toString());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, orderList);

        display.setAdapter(arrayAdapter);
        populatePriceFields();
    }

    /**
     * Removes the selected item from the order
     */
    private void removeItem(){
        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();
        if(currentOrderSingleton.getCurrentOrder().getItems().isEmpty()){
            emptyOrderAlert();
            return;
        }
        currentOrderSingleton.getCurrentOrder().getItems().remove(selectedIndex);
        populateOrderView();
    }

    /**
     * Alert to signal that the order is empty
     */
    private void emptyOrderAlert(){
        Toast.makeText(this, "There are no items to remove.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Fills the price text fields based on the items in the order
     */
    @SuppressLint("SetTextI18n")
    private void populatePriceFields(){
        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();
        double cost = currentOrderSingleton.getCurrentOrder().price();
        double taxCost = currentOrderSingleton.getCurrentOrder().taxPrice();
        double totalCost = cost + taxCost;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedTotalCost = df.format(totalCost);
        subtotal.setText("$" + Double.toString(cost));
        tax.setText("$" + Double.toString(taxCost));
        total.setText("$" + formattedTotalCost);
    }

    /**
     * Places the order adding it to the global orders array list
     */
    private void placeOrder(){
        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();

        AllOrdersSingleton allOrdersSingleton = com.example.app.AllOrdersSingleton.getInstance();
        if(currentOrderSingleton.getCurrentOrder().getItems().isEmpty()){
            emptyOrderAlertTwo();
            return;
        }
        allOrdersSingleton.getOrderList().add(currentOrderSingleton.getCurrentOrder());

        currentOrderSingleton.resetCurrentOrder();
        populateOrderView();
    }

    /**
     * Signals that there are no items in the order
     */
    private void emptyOrderAlertTwo(){
        Toast.makeText(this, "There are no items in the order.", Toast.LENGTH_SHORT).show();
    }
}