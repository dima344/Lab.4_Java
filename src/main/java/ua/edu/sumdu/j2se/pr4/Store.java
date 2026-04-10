package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Comparator; // Додано імпорт

public class Store {
    private String storeName;
    private ArrayList<StoreItem> inventory;

    public Store(String storeName) {
        this.storeName = storeName;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<StoreItem> getInventory() { return inventory; }

    public void addNewPhone(Phone ph, int quantity) {
        for (StoreItem item : inventory) {
            if (item.getPhone().equals(ph)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("🔄 Кількість оновлена.");
                return;
            }
        }
        inventory.add(new StoreItem(ph, quantity));
        System.out.println("✅ Товар додано.");
    }

    /**
     * Виводить відсортований інвентар, використовуючи переданий Comparator.
     * @param comparator критерій сортування
     */
    public void printSortedInventory(Comparator<StoreItem> comparator) {
        if (inventory.isEmpty()) {
            System.out.println("Магазин порожній. Немає що сортувати.");
            return;
        }

        // Сортуємо копію списку, щоб не псувати початковий порядок у базі
        ArrayList<StoreItem> sortedList = new ArrayList<>(inventory);
        
        // Використовуємо дозволений метод sort
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
        if (!found) System.out.println("Об'єктів не знайдено.");
    }

    public void searchByPriceRange(double min, double max) {
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getPrice() >= min && item.getPhone().getPrice() <= max) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів не знайдено.");
    }

    public void searchByMinMemory(int minMemory) {
        boolean found = false;
        for (StoreItem item : inventory) {
            if (item.getPhone().getMemoryGB() >= minMemory) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів не знайдено.");
    }
}