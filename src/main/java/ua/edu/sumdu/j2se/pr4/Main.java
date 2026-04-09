package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №13");

        Store store = new Store("Tech Shop Sumy");
        store.getInventory().addAll(FileStorage.loadFromJson());

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Пошук");
            System.out.println("2. Створити телефон");
            System.out.println("3. Вивести весь список");
            System.out.println("4. СОРТУВАТИ ЗА ЦІНОЮ (Comparable)");
            System.out.println("5. Вихід");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": searchMenu(store); break;
                case "2": createNewObjectMenu(store); break;
                case "3": printAllObjects(store); break;
                case "4": store.printSortedInventory(); break;
                case "5":
                    isRunning = false;
                    FileStorage.saveToJson(store.getInventory());
                    break;
                default: System.out.println("Невірний вибір.");
            }
        }
        scanner.close();
    }

    private static void createNewObjectMenu(Store store) {
        System.out.println("\n1. Звичайний | 2. Смартфон | 3. Кнопковий | 4. Ігровий | 5. Супутник");
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
        for (StoreItem item : store.getInventory()) System.out.println(item);
    }

    private static void searchMenu(Store store) {
        System.out.print("Введіть бренд для пошуку: ");
        store.searchByBrand(scanner.nextLine().trim());
    }
}