package com.example.assign4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class coustomer {
    private List<cartitem> cart;

    public coustomer(List<cartitem> cart) {
        this.cart = cart;
    }

    public void displayc() {
        System.out.println("Welcome");
        System.out.println("1. Browse menu");
        System.out.println("2. View cart");
        System.out.println("3. Add items");
        System.out.println("4. Checkout");
        System.out.println("5. View order status");
        System.out.println("6. Previous orders");
        System.out.println("7. Write a review");
        System.out.println("8. Reviews");
        System.out.println("9. Exit");
    }

    public void checkout() {
        if (this.cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checking out.");
        } else {
            int totalAmount = 0;

            cartitem item;
            for(Iterator var2 = this.cart.iterator(); var2.hasNext(); totalAmount += item.getTotalPrice()) {
                item = (cartitem)var2.next();
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Any special instructions (or leave blank): ");
            String specialInstructions = scanner.nextLine();
            System.out.print("Would you like to become a VIP member for 200 Rs? (yes/no): ");
            String vipChoice = scanner.nextLine();
            Order order = new Order(customerName, address, new ArrayList(this.cart), totalAmount, specialInstructions);
            if (vipChoice.equalsIgnoreCase("yes")) {
                Admin.total += 200;
                System.out.println("Congratulations! You are now a VIP member.");
                Admin.addOrder(order, true);
            } else {
                Admin.addOrder(order, false);
            }

            System.out.println("Order placed successfully!");
            System.out.println(order);
            this.cart.clear();
        }
    }

    public void viewOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name to check order status: ");
        String customerName = scanner.nextLine();
        boolean hasOrders = false;
        System.out.println("Your Orders:");
        Iterator var4 = Admin.vipOrders.iterator();

        Order order;
        while(var4.hasNext()) {
            order = (Order)var4.next();
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(order);
                hasOrders = true;
            }
        }

        var4 = Admin.regularOrders.iterator();

        while(var4.hasNext()) {
            order = (Order)var4.next();
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(order);
                hasOrders = true;
            }
        }

        if (!hasOrders) {
            System.out.println("You have no orders.");
        } else {
            System.out.print("Enter order number to cancel or 0 to return: ");
            int orderNumber = scanner.nextInt();
            scanner.nextLine();
            if (orderNumber > 0 && orderNumber <= Admin.vipOrders.size() + Admin.regularOrders.size()) {
                if (orderNumber <= Admin.vipOrders.size()) {
                    Admin.vipOrders.remove(orderNumber - 1);
                } else {
                    Admin.regularOrders.remove(orderNumber - Admin.vipOrders.size() - 1);
                }

                System.out.println("Order cancelled successfully.");
            } else if (orderNumber == 0) {
                System.out.println("Returning to Customer menu.");
            } else {
                System.out.println("Invalid order number.");
            }

        }
    }

    public void viewPreviousOrders() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name to view previous orders: ");
        String customerName = scanner.nextLine();
        Admin.viewPreviousOrdersByName(customerName);
        System.out.print("Enter order number to reorder or 0 to return: ");
        int orderNumber = scanner.nextInt();
        scanner.nextLine();
        if (orderNumber > 0 && orderNumber <= Admin.previousOrders.size()) {
            Order orderToReorder = (Order)Admin.previousOrders.get(orderNumber - 1);
            List<cartitem> newCart = new ArrayList(orderToReorder.getItems());
            int totalAmount = orderToReorder.getTotalAmount();
            System.out.print("Enter your name: ");
            String reorderName = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Any special instructions (or leave blank): ");
            String specialInstructions = scanner.nextLine();
            System.out.print("Would you like to become a VIP member for 200 Rs? (yes/no): ");
            String vipChoice = scanner.nextLine();
            Order reorder = new Order(reorderName, address, newCart, totalAmount, specialInstructions);
            if (vipChoice.equalsIgnoreCase("yes")) {
                Admin.total += 200;
                System.out.println("Congratulations! You are now a VIP member.");
                Admin.addOrder(reorder, true);
            } else {
                Admin.addOrder(reorder, false);
            }

            System.out.println("Order placed successfully!");
            System.out.println(reorder);
        } else if (orderNumber == 0) {
            System.out.println("Returning to Customer menu.");
        } else {
            System.out.println("Invalid order number.");
        }

    }
}
