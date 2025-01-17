package com.example.assign4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CanteenApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        ObservableList<food> menuItems = FXCollections.observableArrayList(Admin.menu);

        Button viewMenuButton = new Button("View Menu");
        Button viewOrdersButton = new Button("View Orders");

        VBox loginLayout = new VBox(20, viewMenuButton, viewOrdersButton);
        loginLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        viewMenuButton.setOnAction(e -> openMenu(primaryStage, menuItems));
        viewOrdersButton.setOnAction(e -> openOrders(primaryStage));

        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setTitle("Canteen Management System");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void openMenu(Stage primaryStage, ObservableList<food> menuItems) {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label title = new Label("Canteen Menu");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        menuLayout.getChildren().add(title);

        for (food item : menuItems) {
            Label menuItem = new Label(item.getName() + " - Rs " + item.getp() + " - " + (item.getAval() == 1 ? "Available" : "Not Available"));
            menuLayout.getChildren().add(menuItem);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(primaryStage));
        menuLayout.getChildren().add(backButton);

        Scene menuScene = new Scene(menuLayout, 400, 300);
        primaryStage.setScene(menuScene);
    }

    private void openOrders(Stage primaryStage) {
        VBox ordersLayout = new VBox(20);
        ordersLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label title = new Label("Pending Orders");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        ordersLayout.getChildren().add(title);

        if (Admin.regularOrders.isEmpty() && Admin.vipOrders.isEmpty()) {
            ordersLayout.getChildren().add(new Label("No pending orders."));
        } else {
            for (Order order : Admin.vipOrders) {
                Label orderLabel = new Label(
                    "Order #" + " - Total Rs: " + order.getTotalAmount() + " - " + order.getStatus()
                );
                ordersLayout.getChildren().add(orderLabel);
            }
            for(Order obr : Admin.regularOrders){
                Label ordes = new Label("Order #" + " - Total Rs: " + obr.getTotalAmount() + " - " + obr.getStatus()
                );
                ordersLayout.getChildren().add(ordes);
            }


        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(primaryStage));
        ordersLayout.getChildren().add(backButton);

        Scene ordersScene = new Scene(ordersLayout, 400, 300);
        primaryStage.setScene(ordersScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
