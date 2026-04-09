package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Collections;

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
     * Сортування за допомогою Comparable
     */
    public void printSortedInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Магазин порожній.");
            return;
        }

        // Сортуємо копію списку, щоб не псувати порядок у базі
        ArrayList<StoreItem> sortedList = new ArrayList<>(inventory);
        sortedList.sort((a, b) -> a.getPhone().compareTo(b.getPhone()));

        System.out.println("\n--- ВІДСОРТОВАНИЙ ІНВЕНТАР (За ціною) ---");
        for (StoreItem item : sortedList) {
            System.out.println(item.toString());
        }
    }

    public void searchByBrand(String brand) {
        for (StoreItem item : inventory) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand)) System.out.println(item);
        }
    }
}