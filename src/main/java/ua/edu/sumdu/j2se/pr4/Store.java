package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;

/**
 * Клас-контейнер Магазин, що агрегує товари (StoreItem).
 */
public class Store {
    private String storeName;
    private ArrayList<StoreItem> inventory;

    public Store(String storeName) {
        this.storeName = storeName;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<StoreItem> getInventory() {
        return inventory;
    }

    /**
     * Додає телефон у магазин. Якщо такий телефон вже є, збільшує кількість.
     */
    public void addNewPhone(Phone ph, int quantity) {
        for (StoreItem item : inventory) {
            // Використовуємо перевизначений метод equals() з класу Phone
            if (item.getPhone().equals(ph)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("🔄 Такий телефон вже є в базі. Кількість збільшено на " + quantity + ".");
                return;
            }
        }
        // Якщо не знайдено, додаємо новий
        inventory.add(new StoreItem(ph, quantity));
        System.out.println("✅ Нова модель телефону додана в магазин.");
    }

    // ==========================================
    // МЕТОДИ ПОШУКУ (Перенесені з Main)
    // ==========================================

    public void searchByBrand(String brand) {
        System.out.println("\n--- Результати пошуку за брендом '" + brand + "' ---");
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand)) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів не знайдено.");
    }

    public void searchByPriceRange(double min, double max) {
        System.out.println("\n--- Результати пошуку за ціною від $" + min + " до $" + max + " ---");
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getPrice() >= min && item.getPhone().getPrice() <= max) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів не знайдено.");
    }

    public void searchByMinMemory(int minMemory) {
        System.out.println("\n--- Результати пошуку: Пам'ять від " + minMemory + "GB ---");
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getMemoryGB() >= minMemory) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів не знайдено.");
    }
}