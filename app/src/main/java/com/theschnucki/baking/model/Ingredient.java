package com.theschnucki.baking.model;

/**
 * Created by theSchnucki on 05.06.2018.
 */
public class Ingredient {

    private String quantity;
    private String measure;
    private String name;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
