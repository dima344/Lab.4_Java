package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Phone> inventory = new ArrayList<>(); // Порожня колекція на старті

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №8");
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Головне меню ---");
            System.out.println("1. Створити новий об'єкт");
            System.out.println("2. Вивести інформацію про всі об'єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu();
                    break;
                case "2":
                    printAllObjects();
                    break;
                case "3":
                    isRunning = false;
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("❌ Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    private static void createNewObjectMenu() {
        System.out.println("\n--- Виберіть тип телефону для створення ---");
        System.out.println("1. Звичайний телефон (Phone)");
        System.out.println("2. Смартфон (SmartPhone)");
        System.out.println("3. Кнопковий телефон (KeypadPhone)");
        System.out.println("4. Ігровий телефон (GamingPhone)");
        System.out.println("5. Супутниковий телефон (SatellitePhone)");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Ваш вибір: ");

        String typeChoice = scanner.nextLine().trim();

        if (typeChoice.equals("0")) return;

        try {
            System.out.print("Бренд: "); String brand = scanner.nextLine();
            System.out.print("Модель: "); String model = scanner.nextLine();
            System.out.print("Ціна ($): "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Пам'ять (GB): "); int memory = Integer.parseInt(scanner.nextLine());
            System.out.print("Батарея (mAh): "); int battery = Integer.parseInt(scanner.nextLine());
            
            OSType osType = OSType.OTHER; // Для спрощення вводу беремо дефолтне значення, або можна додати вибір

            switch (typeChoice) {
                case "1":
                    inventory.add(new Phone(brand, model, price, memory, battery, osType));
                    System.out.println("✅ Базовий телефон успішно створено.");
                    break;
                case "2":
                    System.out.print("Розмір екрана (дюйми): "); double screen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є NFC (true/false): "); boolean nfc = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new SmartPhone(brand, model, price, memory, battery, OSType.ANDROID, screen, nfc));
                    System.out.println("✅ Смартфон успішно створено.");
                    break;
                case "3":
                    System.out.print("Чи є ліхтарик (true/false): "); boolean flash = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new KeypadPhone(brand, model, price, memory, battery, osType, flash));
                    System.out.println("✅ Кнопковий телефон успішно створено.");
                    break;
                case "4":
                    System.out.print("Розмір екрана (дюйми): "); double gScreen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є охолодження (true/false): "); boolean cool = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Кількість тригерів: "); int triggers = Integer.parseInt(scanner.nextLine());
                    inventory.add(new GamingPhone(brand, model, price, memory, battery, OSType.ANDROID, gScreen, true, cool, triggers));
                    System.out.println("✅ Ігровий телефон успішно створено.");
                    break;
                case "5":
                    System.out.print("Мережа супутника: "); String net = scanner.nextLine();
                    System.out.print("Водонепроникний (true/false): "); boolean water = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new SatellitePhone(brand, model, price, memory, battery, osType, net, water));
                    System.out.println("✅ Супутниковий телефон успішно створено.");
                    break;
                default:
                    System.out.println("❌ Невірний тип. Скасування створення.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка вводу: очікувалося число або логічне значення (true/false).");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Помилка валідації: " + e.getMessage());
        }
    }

    private static void printAllObjects() {
        System.out.println("\n=== Вміст колекції (Поліморфізм) ===");
        if (inventory.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i).toString());
            }
        }
    }
}