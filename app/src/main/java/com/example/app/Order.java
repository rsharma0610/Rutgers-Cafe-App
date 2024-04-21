package com.example.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Order class representing the order object containing a size number, size identifier, and list of items
 * @Author Rohan Sharma and Joseph Veliz
 */
public class Order {
    public static int number = 1;
    private int orderNumber;
    private List<MenuItem> items;

    /**
     * Constructor method for a new order
     */
    public Order(){
        setItems(new ArrayList<>());
        setOrderNumber(number);
        number += 1;
    }

    /**
     * Getter method for the items list
     * @return Menu Item list
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Setter method to set the list of items
     * @param items Menu Item list
     */
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    /**
     * Getter method to retrieve the order number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Setter method to set the order number
     * @param orderNumber order number
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Method to return the price of the order
     * @return
     */
    public double price() {
        double total = 0.0;
        for (MenuItem item : getItems()) {
            total += item.price();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));

        return total;
    }
    public double taxPrice() {
        double total = 0.0;
        for (MenuItem item : getItems()) {
            total += item.price();
        }
        total = total * 0.06625;
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        return total;
    }
}


