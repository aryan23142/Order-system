package com.example.assign4;


class cartitem {
    private food item;
    int quantity;

    public cartitem(food item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public food getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void increaseQuantity() {
        ++this.quantity;
    }

    public int getTotalPrice() {
        return this.item.getp() * this.quantity;
    }

    public String toString() {
        String var10000 = this.item.getName();
        return var10000 + " (x" + this.quantity + ") - $" + this.getTotalPrice();
    }
}
