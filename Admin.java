package com.example.assign4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Admin {
    public static List<food> menu = new ArrayList();
    static List<Order> vipOrders = new ArrayList();
    static List<Order> regularOrders = new ArrayList();
    static List<Order> previousOrders = new ArrayList();
    static int total = 0;

    public Admin() {
    }

    public void display() {
        System.out.println("Welcome Admin");
        System.out.println("1. View menu");
        System.out.println("2. Add item");
        System.out.println("3. Update item");
        System.out.println("4. Delete item");
        System.out.println("5. View orders");
        System.out.println("6. Amount");
        System.out.println("7. Exit");
    }

    public static void add(food item) {
        menu.add(item);
    }

    public void view() {
        System.out.println(total);
    }

    public static void viewmenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is currently empty.");
        } else {
            System.out.println("Current Menu:");
            Iterator var0 = menu.iterator();

            while(var0.hasNext()) {
                food item = (food)var0.next();
                System.out.println(item);
            }
        }

    }

    public static food find(String name) {
        Iterator var1 = menu.iterator();

        food item;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            item = (food)var1.next();
        } while(!item.getName().equalsIgnoreCase(name));

        return item;
    }

    public static boolean findn(String name) {
        Iterator var1 = menu.iterator();

        food item;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            item = (food)var1.next();
        } while(!item.getName().equalsIgnoreCase(name));

        return true;
    }

    public static void sortByPrice() {
        if (menu.isEmpty()) {
            System.out.println("Menu is currently empty.");
        } else {
            menu.sort(Comparator.comparingInt(food::getp));
            System.out.println("Menu sorted by price:");
            viewmenu();
        }

    }

    public static void searchbyc(String category) {
        List<food> categoryItems = new ArrayList();
        Iterator var2 = menu.iterator();

        food item;
        while(var2.hasNext()) {
            item = (food)var2.next();
            if (item.getCategory().equalsIgnoreCase(category)) {
                categoryItems.add(item);
            }
        }

        if (categoryItems.isEmpty()) {
            System.out.println("No items found in the category \"" + category + "\".");
        } else {
            System.out.println("Items in category \"" + category + "\":");
            var2 = categoryItems.iterator();

            while(var2.hasNext()) {
                item = (food)var2.next();
                System.out.println(item);
            }
        }

    }

    public static void addOrder(Order order, boolean isVip) {
        if (isVip) {
            vipOrders.add(order);
            saveOrderHistory();
        } else {
            regularOrders.add(order);
            saveOrderHistory();
        }


    }

    public static void vieworders() {
        if (vipOrders.isEmpty() && regularOrders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            Iterator var0 = vipOrders.iterator();

            Order ord;
            while(var0.hasNext()) {
                ord = (Order)var0.next();
                checkOrderAvailability(ord);
            }

            var0 = regularOrders.iterator();

            while(var0.hasNext()) {
                ord = (Order)var0.next();
                checkOrderAvailability(ord);
            }

            System.out.println("Orders:");

            int i;
            for(i = 0; i < vipOrders.size(); ++i) {
                System.out.println(i + 1 + ". " + String.valueOf(vipOrders.get(i)));
            }

            for(i = 0; i < regularOrders.size(); ++i) {
                PrintStream var10000 = System.out;
                int var10001 = vipOrders.size() + i + 1;
                var10000.println("" + var10001 + ". " + String.valueOf(regularOrders.get(i)));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter order number to update status or 0 to return: ");
            int orderNumber = scanner.nextInt();
            scanner.nextLine();
            if (orderNumber > 0 && orderNumber <= vipOrders.size() + regularOrders.size()) {
                boolean isVip = orderNumber <= vipOrders.size();
                Order order;
                if (isVip) {
                    order = (Order)vipOrders.get(orderNumber - 1);
                } else {
                    order = (Order)regularOrders.get(orderNumber - vipOrders.size() - 1);
                }

                System.out.println("1. Mark as Received");
                System.out.println("2. Out for delivery");
                System.out.println("3. Delivered");
                System.out.println("4. Deny");
                System.out.print("Choose an option: ");
                int statusChoice = scanner.nextInt();
                scanner.nextLine();
                switch (statusChoice) {
                    case 1:
                        order.setStatus("Received");
                        break;
                    case 2:
                        order.setStatus("Out for delivery");
                        break;
                    case 3:
                        order.setStatus("Delivered");
                        total += order.getTotalAmount();
                        if (isVip) {
                            vipOrders.remove(orderNumber - 1);
                        } else {
                            regularOrders.remove(orderNumber - vipOrders.size() - 1);
                        }

                        previousOrders.add(order);
                        break;
                    case 4:
                        order.setStatus("Denied");
                        if (isVip) {
                            vipOrders.remove(orderNumber - 1);
                        } else {
                            regularOrders.remove(orderNumber - vipOrders.size() - 1);
                        }

                        System.out.println("Issuing a refund of " + order.getTotalAmount());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                System.out.println("Order status updated to: " + order.getStatus());
            }

        }
    }

    private static void checkOrderAvailability(Order order) {
        Iterator var1 = order.getItems().iterator();

        while(var1.hasNext()) {
            cartitem item = (cartitem)var1.next();
            food menuItem = find(item.getItem().getName());
            if (menuItem == null || menuItem.getAval() == 0) {
                order.setStatus("Denied");
                break;
            }
        }

    }


    public static void saveOrderHistory() {
        String filename = "bot.txt";


        System.out.println("VIP Orders size: " + vipOrders.size());
        System.out.println("Regular Orders size: " + regularOrders.size());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            System.out.println("Saving order history to: " + new File(filename).getAbsolutePath());


            writer.write("VIP Orders:\n");
            if (vipOrders.isEmpty()) {
                writer.write("No VIP orders available.\n");
            } else {
                for (Order order : vipOrders) {
                    writer.write("Order Details:\n");
                    writer.write("Customer Name: " + order.getCustomerName() + "\n");
                    writer.write("Address: " + order.getAddress() + "\n");
                    writer.write("Items:\n");
                    for (cartitem item : order.getItems()) {
                        writer.write("- " + item.toString() + "\n");
                    }
                    writer.write("Total Amount: $" + order.getTotalAmount() + "\n");
                    writer.write("Special Instructions: " + (order.getins().isEmpty() ? "None" : order.getins()) + "\n");
                    writer.write("Status: " + order.getStatus() + "\n");
                    writer.write("#####################\n");
                }
            }


            writer.write("Regular Orders:\n");
            if (regularOrders.isEmpty()) {
                writer.write("No regular orders available.\n");
            } else {
                for (Order order : regularOrders) {
                    writer.write("Order Details:\n");
                    writer.write("Customer Name: " + order.getCustomerName() + "\n");
                    writer.write("Address: " + order.getAddress() + "\n");
                    writer.write("Items:\n");
                    for (cartitem item : order.getItems()) {
                        writer.write("- " + item.toString() + "\n");
                    }
                    writer.write("Total Amount: $" + order.getTotalAmount() + "\n");
                    writer.write("Special Instructions: " + (order.getins().isEmpty() ? "None" : order.getins()) + "\n");
                    writer.write("Status: " + order.getStatus() + "\n");
                    writer.write("#####################\n");
                }
            }
            writer.close();

            System.out.println("Order history saved successfully in " + filename);
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
            e.printStackTrace();
        }
    }




    public static void viewPreviousOrdersByName(String customerName) {
        System.out.println("Previous Orders for " + customerName + ":");
        boolean found = false;
        Iterator var2 = previousOrders.iterator();

        while(var2.hasNext()) {
            Order order = (Order)var2.next();
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(order);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No previous orders found for " + customerName + ".");
        }

    }
}
