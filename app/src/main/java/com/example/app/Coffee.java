package com.example.app;

import java.util.List;

/**
 * Coffee class that handles the quantities, sizes, and addIns of the coffee menu items
 * @Author Rohan Sharma
 */
public class Coffee extends MenuItem{
    private int quantity;
    private CoffeeSize size;
    private final double addInPrice = 0.3;

    private List<CoffeeAddIns> addIns;

    public Coffee(int quantity, CoffeeSize size, List<CoffeeAddIns> addIns){
        setQuantity(quantity);
        setSize(size);
        setAddIns(addIns);
    }
    public double price(){
        double sizePrice = 1.99 + (getSize().ordinal() * 0.5);
        double addInsPrice = getAddIns().size() * addInPrice;
        return (sizePrice + addInsPrice) * quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CoffeeSize getSize() {
        return size;
    }

    public void setSize(CoffeeSize size) {
        this.size = size;
    }

    public List<CoffeeAddIns> getAddIns() {
        return addIns;
    }

    public void setAddIns(List<CoffeeAddIns> addIns) {
        this.addIns = addIns;
    }

    @Override
    public String toString(){
        return "COFFEE, Size:" + getSize() + "     Add-ins:" + getAddIns() + " Quantity:" + getQuantity();
    }
}
