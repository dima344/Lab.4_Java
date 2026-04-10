package ua.edu.sumdu.j2se.pr4;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №17 (Update & Delete)");

        Store store = new Store("Tech Shop Sumy");
        store.getInventory().addAll(FileStorage.loadFromJson());

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Пошук об'єкта");
            System.out.println("2. Створити новий об'єкт (Create)");
            System.out.println("3. Вивести інформацію про всі об'єкти (Read)");
            System.out.println("4. Сортувати інвентар");
            System.out.println("5. Модифікувати телефон (Update)"); // ЛР 17
            System.out.println("6. Видалити телефон (Delete)");     // ЛР 17
            System.out.println("0. Завершити роботу та зберегти дані");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": searchMenu(store); break;
                case "2": createNewObjectMenu(store); break;
                case "3": printAllObjects(store); break;
                case "4": sortMenu(store); break;
                case "5": updateMenu(store); break; // ЛР 17
                case "6": deleteMenu(store); break; // ЛР 17
                case "0":
                    isRunning = false;
                    FileStorage.saveToJson(store.getInventory());
                    System.out.println("Роботу завершено.");
                    break;
                default: System.out.println("❌ Невірний вибір.");
            }
        }
        scanner.close();
    }

    // ==========================================
    // МЕТОДИ ЛР №17 (UPDATE ТА DELETE)
    // ==========================================

    private static void updateMenu(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println("❌ Магазин порожній. Немає що модифікувати.");
            return;
        }
        printAllObjects(store);
        System.out.print("\nВведіть порядковий номер телефону для модифікації: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index < 0 || index >= store.getInventory().size()) {
                System.out.println("❌ Невірний номер.");
                return;
            }

            Phone existingPhone = store.getInventory().get(index).getPhone();

            System.out.println("Що ви хочете змінити у " + existingPhone.getBrand() + " " + existingPhone.getModel() + "?");
            System.out.println("1. Змінити ціну");
            System.out.println("2. Змінити об'єм пам'яті");
            System.out.print("Вибір: ");
            String attrChoice = scanner.nextLine().trim();

            // Витягуємо поточні дані старого телефону
            String type = existingPhone.getType();
            String brand = existingPhone.getBrand();
            String model = existingPhone.getModel();
            double price = existingPhone.getPrice();
            int memory = existingPhone.getMemoryGB();
            int battery = existingPhone.getBatteryCapacity();
            OSType os = existingPhone.getOsType();

            // Змінюємо обраний атрибут
            if (attrChoice.equals("1")) {
                System.out.print("Введіть нову ціну ($): ");
                price = Double.parseDouble(scanner.nextLine().trim());
            } else if (attrChoice.equals("2")) {
                System.out.print("Введіть новий об'єм пам'яті (GB): ");
                memory = Integer.parseInt(scanner.nextLine().trim());
            } else {
                System.out.println("❌ ОПЕРАЦІЯ СКАСОВАНА: Невірний атрибут.");
                return;
            }

            // Створюємо новий об'єкт потрібного класу з оновленими даними
            Phone newPhone = null;
            switch (type) {
                case "BasicPhone": newPhone = new BasicPhone(brand, model, price, memory, battery, os); break;
                case "SmartPhone": 
                    SmartPhone sp = (SmartPhone) existingPhone;
                    newPhone = new SmartPhone(brand, model, price, memory, battery, os, sp.getScreenSize(), sp.isHasNFC()); 
                    break;
                case "KeypadPhone": 
                    KeypadPhone kp = (KeypadPhone) existingPhone;
                    newPhone = new KeypadPhone(brand, model, price, memory, battery, os, kp.isHasFlashlight()); 
                    break;
                case "GamingPhone": 
                    GamingPhone gp = (GamingPhone) existingPhone;
                    newPhone = new GamingPhone(brand, model, price, memory, battery, os, gp.getScreenSize(), gp.isHasNFC(), gp.isHasCoolingSystem(), gp.getTriggerButtonsCount()); 
                    break;
                case "SatellitePhone": 
                    SatellitePhone sat = (SatellitePhone) existingPhone;
                    newPhone = new SatellitePhone(brand, model, price, memory, battery, os, sat.getSatelliteNetwork(), sat.isWaterproof()); 
                    break;
                default: newPhone = new BasicPhone(brand, model, price, memory, battery, os);
            }

            // Викликаємо метод update у класі Store
            boolean isUpdated = store.update(existingPhone, newPhone);
            if (isUpdated) {
                System.out.println("✅ Телефон успішно модифіковано!");
            } else {
                System.out.println("❌ Не вдалося оновити телефон (об'єкт не знайдено).");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка вводу: очікувалося число.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Помилка валідації: " + e.getMessage());
        }
    }

    private static void deleteMenu(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println("❌ Магазин порожній. Немає що видаляти.");
            return;
        }
        printAllObjects(store);
        System.out.print("\nВведіть порядковий номер телефону для видалення: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index < 0 || index >= store.getInventory().size()) {
                System.out.println("❌ Невірний номер.");
                return;
            }

            Phone targetPhone = store.getInventory().get(index).getPhone();
            
            System.out.print("⚠️ Ви впевнені, що хочете видалити " + targetPhone.getBrand() + " " + targetPhone.getModel() + "? (y/n): ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("y")) {
                // Викликаємо метод delete у класі Store
                boolean isDeleted = store.delete(targetPhone);
                if (isDeleted) {
                    System.out.println("✅ Телефон успішно видалено з колекції!");
                } else {
                    System.out.println("❌ Не вдалося видалити (об'єкт не знайдено).");
                }
            } else {
                System.out.println("⛔ Видалення скасовано.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка вводу: очікувалося число.");
        }
    }

    // ==========================================
    // ІНШІ МЕТОДИ (З минулих ЛР)
    // ==========================================

    private static void sortMenu(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println("❌ Магазин порожній. Немає що сортувати.");
            return;
        }
        boolean sorting = true;
        while (sorting) {
            System.out.println("\nОберіть критерій сортування:");
            System.out.println("1. За брендом (від А до Я)");
            System.out.println("2. За об'ємом пам'яті (спадання)");
            System.out.println("3. За кількістю на складі (зростання)");
            System.out.println("0. Повернутися в головне меню");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    Comparator<StoreItem> brandComparator = (o1, o2) -> o1.getPhone().getBrand().compareToIgnoreCase(o2.getPhone().getBrand());
                    store.printSortedInventory(brandComparator);
                    break;
                case "2":
                    Comparator<StoreItem> memoryComparator = (o1, o2) -> Integer.compare(o2.getPhone().getMemoryGB(), o1.getPhone().getMemoryGB());
                    store.printSortedInventory(memoryComparator);
                    break;
                case "3":
                    Comparator<StoreItem> quantityComparator = (o1, o2) -> Integer.compare(o1.getQuantity(), o2.getQuantity());
                    store.printSortedInventory(quantityComparator);
                    break;
                case "0": sorting = false; break;
                default: System.out.println("❌ Невірний вибір.");
            }
        }
    }

    private static void createNewObjectMenu(Store store) {
        System.out.println("\n1. Звичайний | 2. Смартфон | 3. Кнопковий | 4. Ігровий | 5. Супутник");
        System.out.print("Вибір: ");
        String type = scanner.nextLine().trim();
        try {
            System.out.print("Бренд: "); String b = scanner.nextLine();
            System.out.print("Модель: "); String m = scanner.nextLine();
            System.out.print("Ціна ($): "); double p = Double.parseDouble(scanner.nextLine());
            System.out.print("Пам'ять (GB): "); int mem = Integer.parseInt(scanner.nextLine());
            System.out.print("Батарея (mAh): "); int bat = Integer.parseInt(scanner.nextLine());
            System.out.print("📦 Введіть кількість на склад (шт): "); int q = Integer.parseInt(scanner.nextLine());
            
            if (q <= 0) {
                System.out.println("❌ Кількість має бути більшою за нуль!");
                return;
            }

            Phone ph = null;
            switch (type) {
                case "1": ph = new BasicPhone(b, m, p, mem, bat, OSType.OTHER); break;
                case "2":
                    System.out.print("Розмір екрана: "); double screen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Чи є NFC (true/false): "); boolean nfc = Boolean.parseBoolean(scanner.nextLine());
                    ph = new SmartPhone(b, m, p, mem, bat, OSType.ANDROID, screen, nfc);
                    break;
                case "3":
                    System.out.print("Ліхтарик (true/false): "); boolean flash = Boolean.parseBoolean(scanner.nextLine());
                    ph = new KeypadPhone(b, m, p, mem, bat, OSType.OTHER, flash);
                    break;
                case "4":
                    System.out.print("Розмір екрана: "); double gScreen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Охолодження (true/false): "); boolean cool = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Тригери: "); int triggers = Integer.parseInt(scanner.nextLine());
                    ph = new GamingPhone(b, m, p, mem, bat, OSType.ANDROID, gScreen, true, cool, triggers);
                    break;
                case "5":
                    System.out.print("Мережа супутника: "); String net = scanner.nextLine();
                    System.out.print("Водонепроникний (true/false): "); boolean water = Boolean.parseBoolean(scanner.nextLine());
                    ph = new SatellitePhone(b, m, p, mem, bat, OSType.OTHER, net, water);
                    break;
                default: System.out.println("❌ Невірний тип."); return;
            }
            store.addNewPhone(ph, q);
        } catch (Exception e) { System.out.println("❌ Помилка вводу."); }
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

    private static void searchMenu(Store store) {
        System.out.println("1. За брендом | 2. За ціною | 3. За пам'яттю");
        System.out.print("Вибір: ");
        String c = scanner.nextLine().trim();
        try {
            if (c.equals("1")) {
                System.out.print("Бренд: "); store.searchByBrand(scanner.nextLine().trim());
            } else if (c.equals("2")) {
                System.out.print("Мін: "); double min = Double.parseDouble(scanner.nextLine());
                System.out.print("Макс: "); double max = Double.parseDouble(scanner.nextLine());
                store.searchByPriceRange(min, max);
            } else if (c.equals("3")) {
                System.out.print("Мін пам'ять: "); store.searchByMinMemory(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) { System.out.println("Помилка."); }
    }
}