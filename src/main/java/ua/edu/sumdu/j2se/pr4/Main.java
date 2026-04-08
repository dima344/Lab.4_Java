package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Дерев'янко Д. | ІН-31/2 | Лабораторна №7");
        
        ArrayList<Phone> inventory = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nМеню (Наслідування та Поліморфізм):");
            System.out.println("1. Додати звичайний Phone");
            System.out.println("2. Додати SmartPhone");
            System.out.println("3. Додати KeypadPhone");
            System.out.println("4. Вивести все (Поліморфізм)");
            System.out.println("5. Завершити");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    inventory.add(new Phone("Nokia", "105", 20.0, 1, 800, OSType.OTHER));
                    System.out.println("Додано базовий об'єкт.");
                    break;
                case "2":
                    inventory.add(new SmartPhone("Apple", "iPhone 15", 1000.0, 128, 3200, OSType.IOS, 6.1, true));
                    System.out.println("Додано об'єкт нащадка SmartPhone.");
                    break;
                case "3":
                    inventory.add(new KeypadPhone("Samsung", "B310", 30.0, 1, 1000, OSType.OTHER, true));
                    System.out.println("Додано об'єкт нащадка KeypadPhone.");
                    break;
                case "4":
                    System.out.println("\n--- Вміст ArrayList<Phone> (Демонстрація поліморфізму) ---");
                    for (Phone p : inventory) {
                        System.out.println(p.toString());
                    }
                    break;
                case "5":
                    isRunning = false;
                    break;
            }
        }
        scanner.close();
    }
}