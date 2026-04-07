package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("--- Система управління базою телефонів ---");

        while (isRunning) {
            System.out.println("\nОберіть дію:");
            System.out.println("1. Створити новий об'єкт (Телефон)");
            System.out.println("2. Вивести інформацію про всі об'єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewPhone(scanner, phones);
                    break;
                case "2":
                    printAllPhones(phones);
                    break;
                case "3":
                    isRunning = false;
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("Помилка: Невірний пункт меню. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    private static void createNewPhone(Scanner scanner, ArrayList<Phone> phones) {
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();

            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();

            System.out.print("Введіть ціну ($): ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введіть об'єм пам'яті (GB): ");
            int memory = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введіть ємність батареї (mAh): ");
            int battery = Integer.parseInt(scanner.nextLine().trim());

            // Спроба створення об'єкта. Якщо дані невірні - вилетить IllegalArgumentException
            Phone newPhone = new Phone(brand, model, price, memory, battery);
            phones.add(newPhone);
            System.out.println("✅ Телефон успішно додано!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка: Очікувалось число, а введено текст або порожній рядок.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Помилка валідації: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Невідома помилка: " + e.getMessage());
        }
    }

    private static void printAllPhones(ArrayList<Phone> phones) {
        if (phones.isEmpty()) {
            System.out.println("База даних порожня.");
        } else {
            System.out.println("\n--- Список телефонів ---");
            for (int i = 0; i < phones.size(); i++) {
                System.out.println((i + 1) + ". " + phones.get(i).toString());
            }
        }
    }
}