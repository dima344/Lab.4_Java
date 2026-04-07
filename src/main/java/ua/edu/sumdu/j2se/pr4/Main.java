package ua.edu.sumdu.j2se.pr4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Інформаційна шапка програми ---");
        System.out.println("Студент: Дерев'янко Д. | Група: ІН-31/2 | Практична №6");
        System.out.println("Тема: Статичні члени, агрегація, enum\n");

        Store myStore = new Store("Tech Shop Sumy");
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nОберіть дію:");
            System.out.println("1. Додати готовий телефон (Android)");
            System.out.println("2. Створити копію (через конструктор копіювання)");
            System.out.println("3. Вивести інвентар магазину (Агрегація)");
            System.out.println("4. Показати загальну кількість створених об'єктів (Статика)");
            System.out.println("5. Завершити роботу");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Phone p1 = new Phone("Samsung", "S24", 1200.0, 256, 5000, OSType.ANDROID);
                    myStore.addPhone(p1);
                    System.out.println("✅ Телефон додано.");
                    break;
                case "2":
                    try {
                        Phone original = new Phone("Apple", "iPhone 15", 999.0, 128, 3200, OSType.IOS);
                        Phone copy = new Phone(original); // Конструктор копіювання
                        myStore.addPhone(copy);
                        System.out.println("✅ Копію успішно створено та додано.");
                    } catch (Exception e) {
                        System.out.println("❌ Помилка: " + e.getMessage());
                    }
                    break;
                case "3":
                    myStore.printInventory();
                    break;
                case "4":
                    // Виклик статичного методу
                    System.out.println("📊 Всього телефонів створено у пам'яті: " + Phone.getTotalPhonesCreated());
                    break;
                case "5":
                    isRunning = false;
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("❌ Невірний пункт меню.");
            }
        }
        scanner.close();
    }
}