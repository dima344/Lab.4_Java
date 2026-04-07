package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

/**
 * Клас, що описує мобільний телефон.
 */
public class Phone {
    private String brand;
    private String model;
    private double price;
    private int memoryGB;
    private int batteryCapacity;
    private OSType osType; // Нове поле enum

    // СТАТИЧНЕ ПОЛЕ (Лічильник)
    private static int totalPhonesCreated = 0;

    /**
     * Основний конструктор.
     */
    public Phone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setMemoryGB(memoryGB);
        setBatteryCapacity(batteryCapacity);
        setOsType(osType);
        
        totalPhonesCreated++; // Збільшуємо лічильник при створенні
    }

    /**
     * Конструктор копіювання.
     */
    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null");
        }
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.memoryGB = other.memoryGB;
        this.batteryCapacity = other.batteryCapacity;
        this.osType = other.osType;
        
        totalPhonesCreated++; // Збільшуємо лічильник і для копій
    }

    // Статичний метод для отримання лічильника
    public static int getTotalPhonesCreated() {
        return totalPhonesCreated;
    }

    // Гетери та Сетери
    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Бренд не може бути порожнім.");
        this.brand = brand.trim();
    }

    public String getModel() { return model; }
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) throw new IllegalArgumentException("Модель не може бути порожньою.");
        this.model = model.trim();
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Ціна повинна бути більшою за нуль.");
        this.price = price;
    }

    public int getMemoryGB() { return memoryGB; }
    public void setMemoryGB(int memoryGB) {
        if (memoryGB <= 0) throw new IllegalArgumentException("Об'єм пам'яті повинен бути більшим за нуль.");
        this.memoryGB = memoryGB;
    }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) {
        if (batteryCapacity <= 0) throw new IllegalArgumentException("Ємність батареї повинна бути більшою за нуль.");
        this.batteryCapacity = batteryCapacity;
    }

    public OSType getOsType() { return osType; }
    public void setOsType(OSType osType) {
        if (osType == null) throw new IllegalArgumentException("OS Type не може бути null.");
        this.osType = osType;
    }

    @Override
    public String toString() {
        return String.format("Phone [Бренд: %s, Модель: %s, ОС: %s, Ціна: $%.2f]", brand, model, osType, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 && memoryGB == phone.memoryGB &&
               batteryCapacity == phone.batteryCapacity && osType == phone.osType &&
               Objects.equals(brand, phone.brand) && Objects.equals(model, phone.model);
    }
}