package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №15 (Lambda)");

        Store store = new Store("Tech Shop Sumy");
        store.getInventory().addAll(FileStorage.loadFromJson());

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Пошук");
            System.out.println("2. Створити телефон");
            System.out.println("3. Вивести весь список");
            System.out.println("4. Сортувати інвентар (Лямбда-вирази)");
            System.out.println("5. Вихід");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": searchMenu(store); break;
                case "2": createNewObjectMenu(store); break;
                case "3": printAllObjects(store); break;
                case "4": sortMenu(store); break; 
                case "5":
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
    // ПІДМЕНЮ СОРТУВАННЯ (ЛР №15 - Лямбди)
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
            System.out.println("2. За об'ємом пам'яті (від найбільшого до найменшого)");
            System.out.println("3. За кількістю на складі (зростання)");
            System.out.println("0. Повернутися в головне меню");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Лямбда-вираз 1: Сортування за брендом
                    Comparator<StoreItem> brandComparator = (o1, o2) -> 
                        o1.getPhone().getBrand().compareToIgnoreCase(o2.getPhone().getBrand());
                    
                    store.printSortedInventory(brandComparator);
                    break;

                case "2":
                    // Лямбда-вираз 2: Сортування за пам'яттю (спадання)
                    Comparator<StoreItem> memoryComparator = (o1, o2) -> 
                        Integer.compare(o2.getPhone().getMemoryGB(), o1.getPhone().getMemoryGB());
                    
                    store.printSortedInventory(memoryComparator);
                    break;

                case "3":
                    // Лямбда-вираз 3: Сортування за кількістю
                    Comparator<StoreItem> quantityComparator = (o1, o2) -> 
                        Integer.compare(o1.getQuantity(), o2.getQuantity());
                    
                    store.printSortedInventory(quantityComparator);
                    break;

                case "0":
                    sorting = false;
                    break;

                default:
                    System.out.println("❌ Невірний вибір.");
            }
        }
    }

    // ==========================================
    // ІНШІ МЕТОДИ
    // ==========================================
    private static void createNewObjectMenu(Store store) {
        System.out.println("\n1. Звичайний | 2. Смартфон | 3. Кнопковий | 4. Ігровий | 5. Супутник");
        System.out.print("Вибір: ");
        String type = scanner.nextLine().trim();
        try {
            System.out.print("Бренд: "); String b = scanner.nextLine();
            System.out.print("Модель: "); String m = scanner.nextLine();
            System.out.print("Ціна: "); double p = Double.parseDouble(scanner.nextLine());
            System.out.print("Пам'ять: "); int mem = Integer.parseInt(scanner.nextLine());
            System.out.print("Батарея: "); int bat = Integer.parseInt(scanner.nextLine());
            System.out.print("Кількість: "); int q = Integer.parseInt(scanner.nextLine());

            Phone ph = null;
            switch (type) {
                case "1": ph = new BasicPhone(b, m, p, mem, bat, OSType.OTHER); break;
                case "2": ph = new SmartPhone(b, m, p, mem, bat, OSType.ANDROID, 6.1, true); break;
                case "3": ph = new KeypadPhone(b, m, p, mem, bat, OSType.OTHER, true); break;
                case "4": ph = new GamingPhone(b, m, p, mem, bat, OSType.ANDROID, 6.8, true, true, 2); break;
                case "5": ph = new SatellitePhone(b, m, p, mem, bat, OSType.OTHER, "Iridium", true); break;
            }
            if (ph != null) store.addNewPhone(ph, q);
        } catch (Exception e) { System.out.println("❌ Помилка вводу."); }
    }

    private static void printAllObjects(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println("Магазин порожній.");
            return;
        }
        for (StoreItem item : store.getInventory()) System.out.println(item);
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