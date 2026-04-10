package ua.edu.sumdu.j2se.pr4.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ua.edu.sumdu.j2se.pr4.*;

import java.util.ArrayList;
import java.util.UUID;

public class MainApp extends Application {
    private ArrayList<Phone> database = new ArrayList<>();
    private ListView<String> listView = new ListView<>(); // Для виведення списку

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Система керування телефонами (JavaFX)");

        // ==========================================
        // БЛОК 1: ФОРМА СТВОРЕННЯ (Ліва частина)
        // ==========================================
        VBox createBox = new VBox(10);
        createBox.setPadding(new Insets(10));
        createBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");

        Label createLabel = new Label("Створення нового об'єкта:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Звичайний", "Смартфон", "Кнопковий");
        typeBox.setValue("Звичайний");

        TextField brandField = new TextField(); brandField.setPromptText("Введіть Бренд (напр. Apple)");
        TextField modelField = new TextField(); modelField.setPromptText("Введіть Модель");
        TextField priceField = new TextField(); priceField.setPromptText("Введіть Ціну (число)");

        Button btnAdd = new Button("Додати");

        // Логіка кнопки "Додати"
        btnAdd.setOnAction(e -> {
            try {
                String brand = brandField.getText();
                String model = modelField.getText();
                double price = Double.parseDouble(priceField.getText());
                
                Phone newPhone = null;
                switch (typeBox.getValue()) {
                    case "Звичайний":
                        newPhone = new BasicPhone(brand, model, price, 128, 4000, OSType.OTHER);
                        break;
                    case "Смартфон":
                        newPhone = new SmartPhone(brand, model, price, 256, 5000, OSType.ANDROID, 6.5, true);
                        break;
                    case "Кнопковий":
                        newPhone = new KeypadPhone(brand, model, price, 32, 1000, OSType.OTHER, true);
                        break;
                }

                if (newPhone != null) {
                    database.add(newPhone);
                    updateListView(); // Оновлюємо список на екрані
                    showAlert(Alert.AlertType.INFORMATION, "Успіх", "Об'єкт створено!\nUUID: " + newPhone.getUuid());
                    brandField.clear(); modelField.clear(); priceField.clear();
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Помилка вводу", "Ціна має бути числом!");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Помилка", ex.getMessage());
            }
        });

        createBox.getChildren().addAll(createLabel, typeBox, brandField, modelField, priceField, btnAdd);

        // ==========================================
        // БЛОК 2: ПОШУК ЗА UUID (Права верхня частина)
        // ==========================================
        VBox searchBox = new VBox(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");

        Label searchLabel = new Label("Пошук за UUID:");
        TextField uuidField = new TextField(); uuidField.setPromptText("Введіть UUID...");
        Button btnSearch = new Button("Знайти");
        TextArea resultArea = new TextArea(); 
        resultArea.setEditable(false);
        resultArea.setPromptText("Тут буде детальна інформація про знайдений об'єкт...");
        resultArea.setPrefRowCount(4);

        // Логіка кнопки "Знайти"
        btnSearch.setOnAction(e -> {
            String uuidInput = uuidField.getText().trim();
            try {
                UUID searchUuid = UUID.fromString(uuidInput);
                boolean found = false;

                for (Phone p : database) {
                    if (p.getUuid().equals(searchUuid)) {
                        resultArea.setText("ЗНАЙДЕНО ОБ'ЄКТ:\n" + p.toString());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    resultArea.setText("Помилка: Об'єкт з таким UUID не знайдено.");
                }
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Помилка: Некоректний формат UUID!");
            }
        });

        searchBox.getChildren().addAll(searchLabel, uuidField, btnSearch, resultArea);

        // ==========================================
        // БЛОК 3: КОЛЕКЦІЯ (Нижня частина)
        // ==========================================
        VBox listBox = new VBox(10);
        listBox.setPadding(new Insets(10));
        Label listLabel = new Label("Всі об'єкти (Короткий формат):");
        listBox.getChildren().addAll(listLabel, listView);

        // Головний Layout (Розташування)
        HBox topBox = new HBox(20); // Створення зліва, пошук справа
        topBox.getChildren().addAll(createBox, searchBox);
        HBox.setHgrow(searchBox, Priority.ALWAYS);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(topBox, listBox);

        // Налаштування та показ вікна
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для оновлення списку на екрані (завдання 2.4 - короткий вивід)
    private void updateListView() {
        listView.getItems().clear();
        for (Phone p : database) {
            listView.getItems().add(p.toShortString());
        }
    }

    // Метод для показу віконець з помилками
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args); // Стандартний запуск JavaFX
    }
}