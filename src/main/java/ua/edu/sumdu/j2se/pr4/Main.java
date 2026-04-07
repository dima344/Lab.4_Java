package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // 3 готових об'єкти
        phones.add(new Phone("Apple", "iPhone 15", 999, 128));
        phones.add(new Phone("Samsung", "S24", 850, 256));
        phones.add(new Phone("Google", "Pixel 8", 700, 128));

        System.out.println("Введіть дані ще для 2 телефонів:");
        for (int i = 1; i <= 2; i++) {
            System.out.print("Бренд: "); String b = scanner.nextLine();
            System.out.print("Модель: "); String m = scanner.nextLine();
            System.out.print("Ціна: "); double p = Double.parseDouble(scanner.nextLine());
            System.out.print("Пам'ять: "); int mem = Integer.parseInt(scanner.nextLine());
            phones.add(new Phone(b, m, p, mem));
        }

        System.out.println("\nСписок телефонів:");
        for (Phone p : phones) System.out.println(p);
        
        scanner.close();
    }
}