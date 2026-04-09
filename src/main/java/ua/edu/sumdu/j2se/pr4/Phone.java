package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

/**
 * АБСТРАКТНИЙ базовий клас, що описує мобільний телефон.
 * Реалізує інтерфейс Comparable для сортування за ціною.
 */
public abstract class Phone implements Comparable<Phone> {
    protected String type; 
    private String brand;
    private String model;
    private double price;
    private int memoryGB;
    private int batteryCapacity;
    private OSType osType;

    public Phone() {
        this.type = "Phone";
    }

    public Phone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType) {
        this.type = "Phone"; 
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setMemoryGB(memoryGB);
        setBatteryCapacity(batteryCapacity);
        setOsType(osType);
    }

    // РЕАЛІЗАЦІЯ ІНТЕРФЕЙСУ Comparable
    @Override
    public int compareTo(Phone other) {
        // Сортуємо за ціною (від найменшої до найбільшої)
        int priceComparison = Double.compare(this.price, other.price);
        
        // Якщо ціна однакова, сортуємо за брендом за алфавітом
        if (priceComparison == 0) {
            return this.brand.compareToIgnoreCase(other.brand);
        }
        return priceComparison;
    }

    public String getType() { return type; }

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
        return String.format("[%s] %s %s (%s) - $%.2f", type, brand, model, osType, price);
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