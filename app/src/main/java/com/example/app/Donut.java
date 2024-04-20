package com.example.app;

/**
 * Donut class representing the donut menu items with their allocated costs, quantities, and flavors
 */
public class Donut extends MenuItem {

    private final double YEAST_PRICE = 1.79;
    private final double CAKE_PRICE = 1.89;
    private final double HOLE_PRICE = 0.39;

    private int quantity;
    private DonutFlavor flavor;
    private DonutType type;

    /**
     * Constructor method to create a donut object
     * @param quantity number of requested donuts
     * @param flavor selected flavor
     * @param type donut type
     */
    public Donut(int quantity, DonutFlavor flavor, DonutType type){
        setQuantity(quantity);
        setFlavor(flavor);
        setType(type);
    }

    /**
     * Getter method for the quantity of donuts
     * @return quantity of donuts
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter methods to set the quantity of donuts
     * @param quantity requested number of donuts
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method to return the flavor
     * @return the flavor of the donut
     */
    public DonutFlavor getFlavor() {
        return flavor;
    }

    /**
     * Setter method to set the flavor of the donut
     * @param flavor specified flavor
     */
    public void setFlavor(DonutFlavor flavor) {
        this.flavor = flavor;
    }

    /**
     * Getter method to retrieve the type of donut
     * @return the donut type
     */
    public DonutType getType() {
        return type;
    }

    /**
     * Setter method to set the donut type
     * @param type specified donut type
     */
    public void setType(DonutType type) {
        this.type = type;
    }

    /**
     * Method to retrieve the price of donut based on the type
     * @return the price
     */
    public double price(){
        DonutType type = getType();
        int quantity = getQuantity();
        if(type == DonutType.HOLE){
            return quantity * HOLE_PRICE;
        } else if (type == DonutType.CAKE) {
            return quantity * CAKE_PRICE;
        } else if (type == DonutType.YEAST) {
            return quantity * YEAST_PRICE;
        }
        return 0.0;
    }

    /**
     * To string method to return a string representation of the donut object
     * @return String representation of the donut
     */
    public String toString(){
        String donut = "DONUT, Type: " + getType() + ", Flavor: " + getFlavor() + ", Quantity: " + getQuantity();
        return donut;
    }
}
