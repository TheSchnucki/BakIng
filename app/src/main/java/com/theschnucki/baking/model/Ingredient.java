package com.theschnucki.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by theSchnucki on 05.06.2018.
 */
public class Ingredient implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.name);
    }

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {
        this.quantity = in.readString();
        this.measure = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
