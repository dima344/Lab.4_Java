package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Comparator;

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
    // МЕТОДИ ЛР №18: Кидаємо ObjectNotFoundException
    // ==========================================

    public boolean update(Phone existingPhone, Phone newPhone) {
        for (StoreItem item : inventory) {
            if (item.getPhone().equals(existingPhone)) {
                item.setPhone(newPhone);
                return true;
            }
        }
        // Замість повернення false кидаємо наш власний виняток
        throw new ObjectNotFoundException("Не вдалося оновити: телефон не знайдено в базі.");
    }

    public boolean delete(Phone targetPhone) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getPhone().equals(targetPhone)) {
                inventory.remove(i);
                return true;
            }
        }
        // Замість повернення false кидаємо наш власний виняток
        throw new ObjectNotFoundException("Не вдалося видалити: телефон не знайдено в базі.");
    }

    // ==========================================
    // СОРТУВАННЯ ТА ПОШУК
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