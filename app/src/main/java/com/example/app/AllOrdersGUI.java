package com.example.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AllOrdersGUI extends AppCompatActivity {
    private Button remove, export;
    private ListView display;
    private Spinner orderNumbers;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_orders_gui);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        remove = findViewById(R.id.removeOrder);
        export = findViewById(R.id.export);

        display = findViewById(R.id.allOrdersDisplay);

        orderNumbers = findViewById(R.id.orderNumbers);

        price = findViewById(R.id.orderPrice);

        populateOrderNumbers();

        orderNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Integer selectedNumber = (Integer) parentView.getItemAtPosition(position);
                if (selectedNumber != null) {
                    displayOrder(selectedNumber);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });
    }

    private void populateOrderNumbers() {
        List<Integer> numbers = new ArrayList<>();
        AllOrdersSingleton allOrdersSingleton = AllOrdersSingleton.getInstance();
        for (Order order : allOrdersSingleton.getOrderList()) {
            numbers.add(order.getOrderNumber());
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumbers.setAdapter(adapter);
    }

    private void displayOrder(int newValue) {
        AllOrdersSingleton allOrdersSingleton = AllOrdersSingleton.getInstance();
        for (Order order : allOrdersSingleton.getOrderList()) {
            if (order.getOrderNumber() == newValue) {
                populateOrderView(order);
            }
        }

    }
    private void populateOrderView(Order order){
        ArrayList<String> orderList = new ArrayList<>();
        for(MenuItem menuItem : order.getItems()){
            orderList.add(menuItem.toString());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, orderList);
        display.setAdapter(arrayAdapter);
        populateCostField(order);
    }
    @SuppressLint("SetTextI18n")
    private void populateCostField(Order order){
        double cost = order.price();
        double salesTax = order.taxPrice();
        double total = cost + salesTax;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedTotalCost = df.format(total);
        price.setText("$" + formattedTotalCost);
    }
    private void remove(){
        Integer selectedOrderNumber = (Integer) orderNumbers.getSelectedItem();
        AllOrdersSingleton allOrdersSingleton = AllOrdersSingleton.getInstance();
        for(Order order : allOrdersSingleton.getOrderList()){
            if(order.getOrderNumber() == selectedOrderNumber){
                allOrdersSingleton.getOrderList().remove(order);
                populateOrderNumbers();
                display.setAdapter(null);
                price.setText("");
                return;
            }
        }
    }
}