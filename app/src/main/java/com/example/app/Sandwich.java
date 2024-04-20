package com.example.app;

import com.example.app.MenuItem;

import java.util.List;

/**
 * Sandwich class representing sandwich objects
 * @Author Rohan Sharma and Joseph Veliz
 */
public class Sandwich extends MenuItem {
    private Bread bread;
    private Protein protein;
    private List<SandwichAddOns> addOns;

    /**
     * Constructor to create a sandwich object
     * @param bread Bread option
     * @param protein Protein option
     * @param addOns Add ons selected
     */
    public Sandwich(Bread bread, Protein protein, List<SandwichAddOns> addOns){
        setBread(bread);
        setProtein(protein);
        setAddOns(addOns);
    }

    /**
     * Getter method to retrieve the bread option
     * @return the bread option
     */
    public Bread getBread() {
        return bread;
    }

    /**
     * Setter method to set the bread option
     * @param bread desired bread option
     */
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /**
     * Getter method to retrieve the protein option
     * @return protein option
     */
    public Protein getProtein() {
        return protein;
    }

    /**
     * Setter method to set the protein option
     * @param protein desired protein
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    /**
     * Getter method to retrieve the sandwich add-ons
     * @return The add-ons
     */
    public List<SandwichAddOns> getAddOns() {
        return addOns;
    }

    /**
     * Setter method to set the add-ons for the sandwich
     * @param addOns desired add-ons
     */
    public void setAddOns(List<SandwichAddOns> addOns) {
        this.addOns = addOns;
    }

    /**
     * Method to retrieve the price of the sandwich
     * @return the total price
     */
    public double price(){
        double proteinPrice = 0;
        Protein protein = getProtein();
        if(protein == Protein.BEEF){
            proteinPrice = 10.99;
        } else if (protein == Protein.CHICKEN) {
            proteinPrice = 8.99;
        } else if (protein == Protein.FISH) {
            proteinPrice = 9.99;
        }
        double addOnPrice = this.addOnPrice();
        double totalPrice = Math.round((proteinPrice + addOnPrice)*100.0) / 100.0;
        return totalPrice;
    }

    /**
     * Method to add to the price
     * @return
     */
    private double addOnPrice(){
        double price = 0;
        for(SandwichAddOns addOns: getAddOns()){
            if(addOns == SandwichAddOns.LETTUCE || addOns == SandwichAddOns.TOMATOES || addOns == SandwichAddOns.ONIONS){
                price += 0.3;
            }
            if(addOns == SandwichAddOns.CHEESE){
                price += 1.0;
            }
        }
        return  price;
    }

    /**
     * To string method to provide a string representation of the sandwich option
     * @return String of the sandwich
     */
    @Override
    public String toString(){
        String sandwich = "SANDWICH, Bread: " + getBread() + ", Protein: " + getProtein() + ", Add-Ons: " + getAddOns();
        return sandwich;
    }
}
