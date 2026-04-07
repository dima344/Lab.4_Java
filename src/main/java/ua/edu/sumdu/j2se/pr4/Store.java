package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;

/**
 * Клас Магазин, що демонструє агрегацію (містить список телефонів).
 */
public class Store {
    private String name;
    private ArrayList<Phone> inventory;

    public Store(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва магазину не може бути порожньою.");
        }
        this.name = name.trim();
        this.inventory = new ArrayList<>();
    }

    public String getName() { return name; }

    /**
     * Додає телефон до магазину.
     * @param phone об'єкт телефону
     */
    public void addPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null.");
        }
        inventory.add(phone);
    }

    /**
     * Виводить інвентар магазину.
     */
    public void printInventory() {
        System.out.println("=== Інвентар магазину: " + name + " ===");
        if (inventory.isEmpty()) {
            System.out.println("Магазин порожній.");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).toString());
        }
    }
}