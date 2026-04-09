package ua.edu.sumdu.j2se.pr4;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №11");

        // Ініціалізація магазину та завантаження даних
        Store store = new Store("Tech Shop Sumy");
        store.getInventory().addAll(FileStorage.loadFromJson());

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Головне меню ---");
            System.out.println("1. Пошук об'єкта");
            System.out.println("2. Створити новий об'єкт");
            System.out.println("3. Вивести інформацію про всі об'єкти");
            System.out.println("4. Завершити роботу та зберегти дані");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": searchMenu(store); break;
                case "2": createNewObjectMenu(store); break;
                case "3": printAllObjects(store); break;
                case "4":
                    isRunning = false;
                    FileStorage.saveToJson(store.getInventory());
                    System.out.println("Роботу завершено.");
                    break;
                default: System.out.println("❌ Невірний вибір.");
            }
        }
        scanner.close();
    }

    private static void searchMenu(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println("❌ Магазин порожній. Немає що шукати.");
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
                    System.out.print("Введіть назву бренду: ");
                    store.searchByBrand(scanner.nextLine().trim());
                    break;
                case "2":
                    try {
                        System.out.print("Мінімальна ціна: "); double min = Double.parseDouble(scanner.nextLine());
                        System.out.print("Максимальна ціна: "); double max = Double.parseDouble(scanner.nextLine());
                        store.searchByPriceRange(min, max);
                    } catch (Exception e) { System.out.println("❌ Помилка вводу."); }
                    break;
                case "3":
                    try {
                        System.out.print("Мінімальна пам'ять (GB): ");
                        store.searchByMinMemory(Integer.parseInt(scanner.nextLine().trim()));
                    } catch (Exception e) { System.out.println("❌ Помилка вводу."); }
                    break;
                case "0": searching = false; break;
                default: System.out.println("❌ Невірний вибір.");
            }
        }
    }

    private static void createNewObjectMenu(Store store) {
        System.out.println("\n--- Виберіть тип телефону ---");
        System.out.println("1. Звичайний телефон (Phone)");
        System.out.println("2. Смартфон (SmartPhone)");
        System.out.println("3. Кнопковий телефон (KeypadPhone)");
        System.out.println("4. Ігровий телефон (GamingPhone)");
        System.out.println("5. Супутниковий телефон (SatellitePhone)");
        System.out.print("Ваш вибір: ");

        String typeChoice = scanner.nextLine().trim();

        try {
            System.out.print("Бренд: "); String brand = scanner.nextLine();
            System.out.print("Модель: "); String model = scanner.nextLine();
            System.out.print("Ціна ($): "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Пам'ять (GB): "); int memory = Integer.parseInt(scanner.nextLine());
            System.out.print("Батарея (mAh): "); int battery = Integer.parseInt(scanner.nextLine());
            System.out.print("📦 Введіть кількість на склад (шт): "); int quantity = Integer.parseInt(scanner.nextLine());
            
            if (quantity <= 0) {
                System.out.println("❌ Кількість має бути більшою за нуль!");
                return;
            }

            Phone phone = null;
            switch (typeChoice) {
                case "1": phone = new Phone(brand, model, price, memory, battery, OSType.OTHER); break;
                case "2":
                    System.out.print("Розмір екрана: "); double screen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є NFC (true/false): "); boolean nfc = Boolean.parseBoolean(scanner.nextLine());
                    phone = new SmartPhone(brand, model, price, memory, battery, OSType.ANDROID, screen, nfc);
                    break;
                case "3":
                    System.out.print("Ліхтарик (true/false): "); boolean flash = Boolean.parseBoolean(scanner.nextLine());
                    phone = new KeypadPhone(brand, model, price, memory, battery, OSType.OTHER, flash);
                    break;
                case "4":
                    System.out.print("Розмір екрана: "); double gScreen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Охолодження (true/false): "); boolean cool = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Тригери: "); int triggers = Integer.parseInt(scanner.nextLine());
                    phone = new GamingPhone(brand, model, price, memory, battery, OSType.ANDROID, gScreen, true, cool, triggers);
                    break;
                case "5":
                    System.out.print("Мережа супутника: "); String net = scanner.nextLine();
                    System.out.print("Водонепроникний (true/false): "); boolean water = Boolean.parseBoolean(scanner.nextLine());
                    phone = new SatellitePhone(brand, model, price, memory, battery, OSType.OTHER, net, water);
                    break;
                default: System.out.println("❌ Невірний тип."); return;
            }
            
            store.addNewPhone(phone, quantity);

        } catch (Exception e) {
            System.out.println("❌ Помилка: Невірний формат даних. Створення скасовано.");
        }
    }

    private static void printAllObjects(Store store) {
        System.out.println("\n=== Вміст магазину ===");
        if (store.getInventory().isEmpty()) {
            System.out.println("Порожньо.");
        } else {
            for (int i = 0; i < store.getInventory().size(); i++) {
                System.out.println((i + 1) + ". " + store.getInventory().get(i).toString());
            }
        }
    }
}