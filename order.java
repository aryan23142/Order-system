package com.example.assign4;

import java.util.ArrayList;
import java.util.List;

class Order {
    private String customerName;
    private String address;
    private List<cartitem> items;
    private int totalAmount;
    private String status;
    private String special;

    public Order(String customerName, String address, List<cartitem> items, int totalAmount, String special) {
        this.customerName = customerName;
        this.address = address;
        this.items = new ArrayList(items);
        this.totalAmount = totalAmount;
        this.status = "Pending";
        this.special = special;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getins(){
        return special;
    }

    public List<cartitem> getItems() {
        return this.items;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Order from " + this.customerName + ", Address: " + this.address + ", Total Amount: $" + this.totalAmount + ", Status: " + this.status;
    }
}
