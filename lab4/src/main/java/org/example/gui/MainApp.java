package org.example.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.*;

import java.util.UUID;

public class MainApp extends Application {

    private final Store store = new Store("GUI TechStore");

    private ListView<String> phoneListView;
    private TextArea resultArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Store UUID Search (JavaFX)");

        // === Ліва панель: Додавання товару ===
        VBox addPane = new VBox(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        Label addTitle = new Label("Додати новий телефон:");
        addTitle.setStyle("-fx-font-weight: bold;");

        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("SmartPhone", "KeypadPhone");
        typeCombo.setValue("SmartPhone");

        TextField brandField = new TextField();
        brandField.setPromptText("Бренд (Brand)");

        TextField modelField = new TextField();
        modelField.setPromptText("Модель (Model)");

        TextField priceField = new TextField();
        priceField.setPromptText("Ціна (Price)");

        Button addButton = new Button("Додати");

        addPane.getChildren().addAll(addTitle, typeCombo, brandField, modelField, priceField, addButton);

        // === Права верхня панель: Короткий список ===
        VBox listPane = new VBox(5);
        listPane.setPadding(new Insets(10));
        Label listTitle = new Label("Колекція (короткий вивід):");
        listTitle.setStyle("-fx-font-weight: bold;");
        
        phoneListView = new ListView<>();
        phoneListView.setPrefHeight(150);
        listPane.getChildren().addAll(listTitle, phoneListView);

        // === Права нижня панель: Пошук за UUID ===
        VBox searchPane = new VBox(10);
        searchPane.setPadding(new Insets(10));
        searchPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        
        Label searchTitle = new Label("Пошук за UUID:");
        searchTitle.setStyle("-fx-font-weight: bold;");

        HBox searchBox = new HBox(10);
        TextField uuidField = new TextField();
        uuidField.setPromptText("Введіть UUID...");
        uuidField.setPrefWidth(250);
        Button searchButton = new Button("Знайти");
        searchBox.getChildren().addAll(uuidField, searchButton);

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(100);
        resultArea.setWrapText(true);

        searchPane.getChildren().addAll(searchTitle, searchBox, resultArea);

        // --- ДІЇ КНОПОК ---

        addButton.setOnAction(e -> {
            try {
                String brand = brandField.getText();
                String model = modelField.getText();
                double price = Double.parseDouble(priceField.getText());
                
                Phone phone;
                if (typeCombo.getValue().equals("SmartPhone")) {
                    phone = new SmartPhone(brand, model, price, 128, OperatingSystem.ANDROID, 12.0);
                } else {
                    phone = new KeypadPhone(brand, model, price, 4, OperatingSystem.OTHER, true);
                }
                
                store.addNewPhone(phone, 1);
                updateListView();
                showAlert(Alert.AlertType.INFORMATION, "Успіх", "Товар додано! UUID: " + phone.getUuid());

                brandField.clear();
                modelField.clear();
                priceField.clear();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Помилка", "Введіть коректну ціну!");
            } catch (IllegalArgumentException ex) {
                showAlert(Alert.AlertType.ERROR, "Помилка", ex.getMessage());
            }
        });

        searchButton.setOnAction(e -> {
            String uuidStr = uuidField.getText().trim();
            if (uuidStr.isEmpty()) {
                resultArea.setText("Введіть UUID для пошуку.");
                return;
            }

            try {
                UUID targetUuid = UUID.fromString(uuidStr);
                StoreItem found = store.searchByUuid(targetUuid);
                
                if (found != null) {
                    resultArea.setText("ЗНАЙДЕНО ПОВНУ ІНФОРМАЦІЮ:\n" + found);
                } else {
                    resultArea.setText("Не знайдено об'єкт із таким UUID.");
                }
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Помилка: Некоректний формат UUID!");
            }
        });

        // === Компонування вікна ===
        VBox rightPane = new VBox(10);
        rightPane.getChildren().addAll(listPane, searchPane);

        BorderPane root = new BorderPane();
        root.setLeft(addPane);
        root.setCenter(rightPane);

        Scene scene = new Scene(root, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateListView() {
        phoneListView.getItems().clear();
        for (StoreItem item : store.getInventory()) {
            Phone p = item.getPhone();
            // Короткий вивід: назва + UUID
            String shortInfo = String.format("%s: %s %s | UUID: %s", 
                p.getClass().getSimpleName(), p.getBrand(), p.getModel(), p.getUuid().toString());
            phoneListView.getItems().add(shortInfo);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
