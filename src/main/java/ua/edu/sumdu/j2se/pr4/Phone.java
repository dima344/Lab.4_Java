package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;
import java.util.UUID;

/**
 * АБСТРАКТНИЙ базовий клас Phone.
 * Реалізує Comparable (для сортування) та Identifiable (для UUID).
 */
public abstract class Phone implements Comparable<Phone>, Identifiable {
    
    // Поле UUID (Завдання 1.2)
    protected UUID uuid;
    
    protected String type;
    private String brand;
    private String model;
    private double price;
    private int memoryGB;
    private int batteryCapacity;
    private OSType osType;

    /**
     * Порожній конструктор для роботи бібліотеки Gson.
     */
    public Phone() {
        this.type = "Phone";
        this.uuid = UUID.randomUUID(); // Автоматична генерація UUID
    }

    /**
     * Основний конструктор з параметрами.
     */
    public Phone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType) {
        this.type = "Phone"; 
        this.uuid = UUID.randomUUID(); // Автоматична генерація при створенні об'єкта
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setMemoryGB(memoryGB);
        setBatteryCapacity(batteryCapacity);
        setOsType(osType);
    }

    // Реалізація інтерфейсу Identifiable (Завдання 1.1)
    @Override
    public UUID getUuid() {
        return uuid;
    }

    // Реалізація інтерфейсу Comparable (сортування за ціною)
    @Override
    public int compareTo(Phone other) {
        int res = Double.compare(this.price, other.price);
        if (res == 0) {
            return this.brand.compareToIgnoreCase(other.brand);
        }
        return res;
    }

    // --- Гетери та Сетери з валідацією ---

    public String getType() { return type; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getMemoryGB() { return memoryGB; }
    public void setMemoryGB(int memoryGB) {
        if (memoryGB <= 0) throw new IllegalArgumentException("Об'єм пам'яті повинен бути додатним.");
        this.memoryGB = memoryGB;
    }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) {
        if (batteryCapacity <= 0) throw new IllegalArgumentException("Ємність батареї повинна бути додатною.");
        this.batteryCapacity = batteryCapacity;
    }

    public OSType getOsType() { return osType; }
    public void setOsType(OSType osType) {
        if (osType == null) throw new IllegalArgumentException("Тип ОС не вказано.");
        this.osType = osType;
    }

    /**
     * Повний вивід інформації (використовується для пошуку).
     */
    @Override
    public String toString() {
        return String.format("[%s] %s %s - $%.2f | Батарея: %d mAh | UUID: %s", 
                             type, brand, model, price, batteryCapacity, uuid.toString());
    }

    /**
     * Короткий вивід для списку в GUI (Завдання 2.4).
     */
    public String toShortString() {
        return String.format("%s: %s | UUID: %s", brand, model, uuid.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 && 
               memoryGB == phone.memoryGB && 
               Objects.equals(brand, phone.brand) && 
               Objects.equals(model, phone.model);
    }
}