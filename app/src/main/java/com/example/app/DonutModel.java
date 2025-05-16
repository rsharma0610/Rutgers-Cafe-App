package com.example.app;

/**
 * Models the data for each row of the recycler view in the donut view
 */
public class DonutModel {
    String flavor;
    String type;
    String price;
    int image;

    /**
     * Parameterized constructor for the class
     * @param flavor
     * @param type
     * @param price
     * @param image
     */
    public DonutModel(String flavor, String type, String price, int image){
        this.flavor = flavor;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    /**
     * Getter method for flavor
     * @return
     */
    public String getFlavor(){
        return this.flavor;
    }

    /**
     * Getter method for type
     * @return
     */
    public String getType(){
        return this.type;
    }

    /**
     * Getter method for price
     * @return
     */
    public String getPrice(){
        return this.price;
    }

    /**
     * Getter method for image
     * @return
     */
    public int getImage(){
        return this.image;
    }
}
