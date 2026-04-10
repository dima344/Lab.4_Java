package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Comparator;

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
     * Додає телефон у магазин (Create).
     */
    public void addNewPhone(Phone ph, int quantity) {
        for (StoreItem item : inventory) {
            if (item.getPhone().equals(ph)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("🔄 Такий телефон вже є в базі. Кількість збільшено.");
                return;
            }
        }
        inventory.add(new StoreItem(ph, quantity));
        System.out.println("✅ Нова модель телефону додана в магазин.");
    }

    // ==========================================
    // МЕТОДИ ЛР №17 (Update & Delete)
    // ==========================================

    /**
     * Модифікація об'єкта у колекції (Update).
     * @param existingPhone об'єкт, який потрібно знайти (оригінал)
     * @param newPhone новий об'єкт із зміненими даними
     * @return true, якщо оновлено, false, якщо не знайдено
     */
    public boolean update(Phone existingPhone, Phone newPhone) {
        for (StoreItem item : inventory) {
            if (item.getPhone().equals(existingPhone)) {
                item.setPhone(newPhone); // Замінюємо посилання на новий об'єкт
                return true;
            }
        }
        return false;
    }

    /**
     * Видалення об'єкта з колекції (Delete).
     * @param targetPhone об'єкт, який потрібно видалити
     * @return true, якщо видалено, false, якщо не знайдено
     */
    public boolean delete(Phone targetPhone) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getPhone().equals(targetPhone)) {
                inventory.remove(i); // Видаляємо елемент з масиву
                return true;
            }
        }
        return false;
    }

    // ==========================================
    // МЕТОДИ СОРТУВАННЯ ТА ПОШУКУ (З минулих ЛР)
    // ==========================================

    public void printSortedInventory(Comparator<StoreItem> comparator) {
        if (inventory.isEmpty()) {
            System.out.println("Магазин порожній. Немає що сортувати.");
            return;
        }
        ArrayList<StoreItem> sortedList = new ArrayList<>(inventory);
        sortedList.sort(comparator);

        System.out.println("\n--- ВІДСОРТОВАНИЙ ІНВЕНТАР ---");
        for (StoreItem item : sortedList) {
            System.out.println(item.toString());
        }
    }

    public void searchByBrand(String brand) {
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("❌ Об'єктів не знайдено.");
    }

    public void searchByPriceRange(double min, double max) {
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getPrice() >= min && item.getPhone().getPrice() <= max) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("❌ Об'єктів не знайдено.");
    }

    public void searchByMinMemory(int minMemory) {
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getMemoryGB() >= minMemory) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("❌ Об'єктів не знайдено.");
    }
}