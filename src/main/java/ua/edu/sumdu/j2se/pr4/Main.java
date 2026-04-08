package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №10 (Пошук)");

        // Завантаження даних з файлу JSON (ЛР №9)
        ArrayList<Phone> inventory = FileStorage.loadFromJson();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Головне меню ---");
            System.out.println("1. Пошук об'єкта"); // НОВИЙ ПУНКТ
            System.out.println("2. Створити новий об'єкт");
            System.out.println("3. Вивести інформацію про всі об'єкти");
            System.out.println("4. Завершити роботу та зберегти дані");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    searchMenu(inventory); // Виклик підменю пошуку
                    break;
                case "2":
                    createNewObjectMenu(inventory);
                    break;
                case "3":
                    printAllObjects(inventory);
                    break;
                case "4":
                    isRunning = false;
                    FileStorage.saveToJson(inventory);
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("❌ Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    // ==========================================
    // БЛОК ПОШУКУ (Додано для ЛР №10)
    // ==========================================

    private static void searchMenu(ArrayList<Phone> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("❌ Колекція порожня. Немає що шукати.");
            return;
        }

        boolean searching = true;
        while (searching) {
            System.out.println("\n--- Підменю: Пошук об'єктів ---");
            System.out.println("1. Пошук за брендом");
            System.out.println("2. Пошук за діапазоном ціни");
            System.out.println("3. Пошук за мінімальним об'ємом пам'яті");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Введіть назву бренду (наприклад, Apple або Samsung): ");
                    String targetBrand = scanner.nextLine().trim();
                    searchByBrand(inventory, targetBrand);
                    break;
                case "2":
                    try {
                        System.out.print("Введіть мінімальну ціну: ");
                        double minPrice = Double.parseDouble(scanner.nextLine().trim());
                        System.out.print("Введіть максимальну ціну: ");
                        double maxPrice = Double.parseDouble(scanner.nextLine().trim());
                        searchByPriceRange(inventory, minPrice, maxPrice);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Помилка вводу: очікувалося число.");
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Введіть мінімальний об'єм пам'яті (GB): ");
                        int minMemory = Integer.parseInt(scanner.nextLine().trim());
                        searchByMinMemory(inventory, minMemory);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Помилка вводу: очікувалося ціле число.");
                    }
                    break;
                case "0":
                    searching = false;
                    break;
                default:
                    System.out.println("❌ Невірний критерій пошуку.");
            }
        }
    }

    // МЕТОД ПОШУКУ №1: За брендом
    private static void searchByBrand(ArrayList<Phone> inventory, String brand) {
        System.out.println("\n--- Результати пошуку за брендом '" + brand + "' ---");
        boolean found = false;

        // Заборонено Stream API, тому використовуємо звичайний цикл
        for (Phone phone : inventory) {
            // Використовуємо equalsIgnoreCase для нечутливості до регістру
            if (phone.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(phone.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Об'єктів, що відповідають критерію, не знайдено.");
        }
    }

    // МЕТОД ПОШУКУ №2: За діапазоном ціни
    private static void searchByPriceRange(ArrayList<Phone> inventory, double min, double max) {
        System.out.println("\n--- Результати пошуку за ціною від $" + min + " до $" + max + " ---");
        boolean found = false;

        for (Phone phone : inventory) {
            if (phone.getPrice() >= min && phone.getPrice() <= max) {
                System.out.println(phone.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Об'єктів у цьому ціновому діапазоні не знайдено.");
        }
    }

    // МЕТОД ПОШУКУ №3: За мінімальною пам'яттю
    private static void searchByMinMemory(ArrayList<Phone> inventory, int minMemory) {
        System.out.println("\n--- Результати пошуку: Пам'ять від " + minMemory + "GB ---");
        boolean found = false;

        for (Phone phone : inventory) {
            if (phone.getMemoryGB() >= minMemory) {
                System.out.println(phone.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Телефонів з таким об'ємом пам'яті не знайдено.");
        }
    }

    // ==========================================
    // СТАРИЙ БЛОК КОДУ (Створення та Вивід)
    // ==========================================

    private static void createNewObjectMenu(ArrayList<Phone> inventory) {
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
            OSType osType = OSType.OTHER; 

            switch (typeChoice) {
                case "1":
                    inventory.add(new Phone(brand, model, price, memory, battery, osType));
                    break;
                case "2":
                    System.out.print("Розмір екрана (дюйми): "); double screen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є NFC (true/false): "); boolean nfc = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new SmartPhone(brand, model, price, memory, battery, OSType.ANDROID, screen, nfc));
                    break;
                case "3":
                    System.out.print("Чи є ліхтарик (true/false): "); boolean flash = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new KeypadPhone(brand, model, price, memory, battery, osType, flash));
                    break;
                case "4":
                    System.out.print("Розмір екрана (дюйми): "); double gScreen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є охолодження (true/false): "); boolean cool = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Кількість тригерів: "); int triggers = Integer.parseInt(scanner.nextLine());
                    inventory.add(new GamingPhone(brand, model, price, memory, battery, OSType.ANDROID, gScreen, true, cool, triggers));
                    break;
                case "5":
                    System.out.print("Мережа супутника: "); String net = scanner.nextLine();
                    System.out.print("Водонепроникний (true/false): "); boolean water = Boolean.parseBoolean(scanner.nextLine());
                    inventory.add(new SatellitePhone(brand, model, price, memory, battery, osType, net, water));
                    break;
                default:
                    System.out.println("❌ Невірний тип.");
                    return;
            }
            System.out.println("✅ Об'єкт успішно створено.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка вводу: очікувалося число або true/false.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Помилка валідації: " + e.getMessage());
        }
    }

    private static void printAllObjects(ArrayList<Phone> inventory) {
        System.out.println("\n=== Вміст колекції ===");
        if (inventory.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i).toString());
            }
        }
    }
}