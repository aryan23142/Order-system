package com.example.assign4;

public class food {
    private int price;
    private String name;
    private String category;
    private int aval;

    public food(int price, String name, String category, int aval) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.aval = aval;
    }

    public int getp() {
        return this.price;
    }

    public void setp(int p) {
        this.price = p;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAval() {
        return this.aval;
    }

    public void setAval(int aval) {
        this.aval = aval;
    }

    public String toString() {
        return "Name: " + this.name + ", Price: $" + this.price + ", Avalability: " + this.aval;
    }
}
