package com.example.assign4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        Admin.add(new food(200, "Pizza", "food", 1));
        Admin.add(new food(150, "Burger", "food", 1));
        Admin.add(new food(50, "Coke", "drink", 1));
        Admin.add(new food(25, "DD", "drink", 0));
        List<cartitem> cart = new ArrayList<>();
        List<String> review = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to Byte Me");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. GUI");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = 0;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                Admin admin = new Admin();

                while (true) {
                    admin.display();
                    System.out.print("Choose an option: ");
                    int subchoice = 0;

                    if (scanner.hasNextInt()) {
                        subchoice = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    if (subchoice == 1) {
                        admin.viewmenu();
                    } else if (subchoice == 2) {
                        System.out.print("Enter food name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter food price: ");
                        int price = 0;
                        if (scanner.hasNextInt()) {
                            price = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid price. Please enter a number.");
                            scanner.nextLine();
                            continue;
                        }
                        System.out.print("Enter food category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter availability (1 for available, 0 for not available): ");
                        int aval = 0;
                        if (scanner.hasNextInt()) {
                            aval = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid availability. Please enter 1 or 0.");
                            scanner.nextLine();
                            continue;
                        }

                        Admin.add(new food(price, name, category, aval));
                        System.out.println("Food item added successfully.");
                    } else if (subchoice == 3) {
                        System.out.print("Enter the name of the food item to update: ");
                        String name = scanner.nextLine();

                        food itemu = Admin.find(name);
                        if (itemu != null) {
                            System.out.print("Enter new price: ");
                            int newPrice = 0;
                            if (scanner.hasNextInt()) {
                                newPrice = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("Invalid price. Please enter a number.");
                                scanner.nextLine();
                                continue;
                            }
                            System.out.print("Enter new category: ");
                            String newCategory = scanner.nextLine();
                            System.out.print("Enter new availability (1 for available, 0 for not available): ");
                            int newAval = 0;
                            if (scanner.hasNextInt()) {
                                newAval = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("Invalid availability. Please enter 1 or 0.");
                                scanner.nextLine();
                                continue;
                            }

                            itemu.setp(newPrice);
                            itemu.setCategory(newCategory);
                            itemu.setAval(newAval);
                            System.out.println("Food item updated successfully.");
                        } else {
                            System.out.println("No such food item exists.");
                        }
                    } else if (subchoice == 4) {
                        System.out.print("Enter the name of the food item to remove: ");
                        String name = scanner.nextLine();

                        food itemr = Admin.find(name);
                        if (itemr != null) {
                            Admin.menu.remove(itemr);
                            System.out.println("Food item removed successfully.");
                        } else {
                            System.out.println("No such food item exists.");
                        }
                    } else if(subchoice == 5){
                        admin.vieworders();
                    }else if(subchoice==6){
                        admin.view();
                    }else if (subchoice == 7) {
                        System.out.println("Returning to Main menu.");
                        break;
                    } else {
                        System.out.println("Invalid option. Returning to Main menu.");
                        break;
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();


                String USER_FILE = "User.txt";
                boolean userExists = false;


                try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                            System.out.println("Welcome back, " + username + "!");
                            userExists = true;
                            break;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error reading the user file: " + e.getMessage());
                }


                if (!userExists) {
                    System.out.println("Hello new user, " + username + "!");
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
                        writer.write(username + "," + password);
                        writer.newLine();
                        System.out.println("Your account has been created.");

                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Error writing to the user file: " + e.getMessage());
                    }
                }



                coustomer cos = new coustomer(cart);
                while (true) {
                    cos.displayc();
                    System.out.print("Choose an option: ");
                    int subchoice = 0;

                    if (scanner.hasNextInt()) {
                        subchoice = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    if (subchoice == 1) {
                        Admin.viewmenu();
                        System.out.print("Choose sorting option (1 to sort, 0 to return): ");

                        int sortOption = 0;
                        if (scanner.hasNextInt()) {
                            sortOption = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();
                            continue;
                        }

                        if (sortOption == 1) {
                            System.out.println("Sorting Options:");
                            System.out.println("1. Sort by price");
                            System.out.println("2. Search name");
                            System.out.println("3. Sort by category");
                            System.out.print("Choose an option: ");

                            int sortingChoice = 0;
                            if (scanner.hasNextInt()) {
                                sortingChoice = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("Invalid input. Returning to Customer menu.");
                                scanner.nextLine();
                                continue;
                            }

                            if (sortingChoice == 1) {
                                System.out.println("Sorting by price-");
                                Admin.sortByPrice();
                            } else if (sortingChoice == 2) {
                                System.out.println("Search name-");
                                System.out.print("Enter the name of food- ");
                                String name = scanner.nextLine();
                                food item = Admin.find(name);
                                if (item != null) {
                                    System.out.println(item);
                                } else {
                                    System.out.println("No food item with that name found.");
                                }
                            } else if (sortingChoice == 3) {
                                System.out.println("Enter the category of food - ");
                                String cat = scanner.nextLine();
                                Admin.searchbyc(cat);
                            } else {
                                System.out.println("Invalid sorting option. Returning to Customer menu.");
                            }
                        } else {
                            System.out.println("Returning to Customer menu.");
                        }
                    } else if (subchoice == 2) {
                        if (cart.isEmpty()) {
                            System.out.println("Your cart is empty.");
                        } else {
                            while (true) {
                                System.out.println("Your Cart:");
                                int totalAmount = 0;
                                for (int i = 0; i < cart.size(); i++) {
                                    System.out.println((i + 1) + ". " + cart.get(i));
                                    totalAmount += cart.get(i).getTotalPrice();
                                }
                                System.out.println("Total Amount: $" + totalAmount);

                                System.out.println("\nCart Options:");
                                System.out.println("1. Modify Quantity");
                                System.out.println("2. Remove Item");
                                System.out.println("3. Return to Customer Menu");
                                System.out.print("Choose an option: ");

                                int cartChoice = 0;
                                if (scanner.hasNextInt()) {
                                    cartChoice = scanner.nextInt();
                                    scanner.nextLine();
                                } else {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scanner.nextLine();
                                    continue;
                                }

                                if (cartChoice == 1) {
                                    System.out.print("Enter the item number to modify quantity: ");
                                    int itemNumber = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (itemNumber >= 0 && itemNumber < cart.size()) {
                                        cartitem selectedItem = cart.get(itemNumber);
                                        System.out.print("Enter new quantity for " + selectedItem.getItem().getName() + ": ");
                                        int newQuantity = scanner.nextInt();
                                        scanner.nextLine();

                                        if (newQuantity > 0) {
                                            selectedItem.quantity = newQuantity;
                                            System.out.println("Quantity updated successfully.");
                                        } else {
                                            System.out.println("Invalid quantity. Quantity must be greater than 0.");
                                        }
                                    } else {
                                        System.out.println("Invalid item number.");
                                    }

                                } else if (cartChoice == 2) {
                                    System.out.print("Enter the item number to remove: ");
                                    int itemNumber = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (itemNumber >= 0 && itemNumber < cart.size()) {
                                        cartitem removedItem = cart.remove(itemNumber);
                                        System.out.println("Removed " + removedItem.getItem().getName() + " from the cart.");
                                    } else {
                                        System.out.println("Invalid item number.");
                                    }

                                } else if (cartChoice == 3) {
                                    break;
                                } else {
                                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                                }
                            }
                        }
                    } else if (subchoice == 3) {
                        System.out.print("Enter the name of the food to order: ");
                        String name = scanner.nextLine();
                        food itemo = Admin.find(name);
                        int l = itemo.getAval();
                        if(l == 0){
                            System.out.println("out of stock");
                            break;
                        }
                        if (itemo != null) {
                            boolean itemFound = false;
                            for (cartitem c : cart) {
                                if (c.getItem().equals(itemo)) {
                                    c.increaseQuantity();
                                    itemFound = true;
                                    break;
                                }
                            }
                            if (!itemFound) {
                                cart.add(new cartitem(itemo, 1));
                            }
                            System.out.println("Item added to cart successfully.");
                        } else {
                            System.out.println("No such food item exists.");
                        }
                    } else if (subchoice == 4) {
                        cos.checkout();
                    }else if(subchoice==5){
                        cos.viewOrderStatus();
                    }else if(subchoice==6){
                        cos.viewPreviousOrders();

                    }else if(subchoice == 7){
                        System.out.println("Enter the name of food: ");
                        String name = scanner.nextLine();
                        if(Admin.findn(name)==true){
                            String rev;
                            System.out.println("Enter your review: ");
                            String reev = scanner.nextLine();
                            rev = name + ":" + reev;
                            review.add(rev);

                        }
                        else{
                            System.out.println("No such item found");
                        }

                    }else if(subchoice == 8){
                        System.out.println("Reviews:- ");
                        for(String str:review){
                            System.out.println(str);
                        }
                    }else if (subchoice == 9) {
                        System.out.println("Returning to Main menu.");
                        break;
                    } else {
                        System.out.println("Invalid option. Please choose between 1 to 5.");
                    }
                }
            } else if(choice == 3){
                CanteenApp.main(args);

            }else if (choice == 4) {
                System.out.println("Exiting Byte Me");
                exit = true;
            } else {
                System.out.println("Invalid choice. Please choose between 1 to 3.");
            }
        }
        scanner.close();
    }
}
