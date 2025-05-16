package com.example.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The controller for the donut order screen view
 */
public class DonutGUI extends AppCompatActivity implements RecyclerViewInterface {
    ArrayList<DonutModel> donutModels = new ArrayList<>();
    int[] donutImages = {R.drawable.cake_donut, R.drawable.cake_donut, R.drawable.cake_donut, R.drawable.cake_donut,
    R.drawable.yeast_donut, R.drawable.yeast_donut, R.drawable.yeast_donut, R.drawable.yeast_donut,
    R.drawable.donut_holes, R.drawable.donut_holes, R.drawable.donut_holes, R.drawable.donut_holes};

    /**
     * Method that is called when the donut view is created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpDonutModels();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, donutModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Creates the donut models to populate the recycler view
     */
    private void setUpDonutModels(){
        String[] flavors = getResources().getStringArray(R.array.flavors);
        String[] types = getResources().getStringArray(R.array.types);
        String[] prices = getResources().getStringArray(R.array.prices);

        for(int i = 0; i < flavors.length; i++){
            donutModels.add(new DonutModel(flavors[i], types[i], prices[i], donutImages[i]));
        }
    }


    /**
     * Creates an on click action that adds selected donut to the global current order object
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        DonutModel donut = donutModels.get(position);
        String flavor = donut.getFlavor();
        String type = donut.getType();
        String price = donut.getPrice();
        //Toast.makeText(getApplicationContext(), flavor + " " + type + " " + price, Toast.LENGTH_SHORT).show();

        DonutFlavor flavorOrder = getFlavor(flavor);
        DonutType typeOrder = getType(type);
        MenuItem donutOrder = new Donut(1, flavorOrder, typeOrder);

        CurrentOrderSingleton currentOrderSingleton = CurrentOrderSingleton.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to add the selected " + flavor + ", " + type + " donut to your order?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentOrderSingleton.getCurrentOrder().getItems().add(donutOrder);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Gets the selected donut flavor
     * @param flavor
     * @return
     */
    private DonutFlavor getFlavor(String flavor){
        switch (flavor) {
            case "Chocolate":
                return DonutFlavor.CHOCOLATE;
            case "Vanilla":
                return DonutFlavor.VANILLA;
            case "Strawberry":
                return DonutFlavor.STRAWBERRY;
            case "Glazed":
                return DonutFlavor.GLAZED;
        }
        return null;
    }

    /**
     * Gets the selected type of donut
     * @param type
     * @return
     */
    private DonutType getType(String type){
        switch (type) {
            case "Cake":
                return DonutType.CAKE;
            case "Yeast":
                return DonutType.YEAST;
            case "Donut Holes":
                return DonutType.HOLE;
        }
        return null;
    }
}