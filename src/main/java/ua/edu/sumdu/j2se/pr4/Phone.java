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
    private int batteryCapacity; // Нове поле

    public Phone(String brand, String model, double price, int memoryGB, int batteryCapacity) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setMemoryGB(memoryGB);
        setBatteryCapacity(batteryCapacity);
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім.");
        }
        this.brand = brand.trim();
    }

    public String getModel() { return model; }
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        this.model = model.trim();
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за нуль.");
        }
        this.price = price;
    }

    public int getMemoryGB() { return memoryGB; }
    public void setMemoryGB(int memoryGB) {
        if (memoryGB <= 0) {
            throw new IllegalArgumentException("Об'єм пам'яті повинен бути більшим за нуль.");
        }
        this.memoryGB = memoryGB;
    }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) {
        if (batteryCapacity <= 0) {
            throw new IllegalArgumentException("Ємність батареї повинна бути більшою за нуль.");
        }
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return String.format("Phone [Бренд: %s, Модель: %s, Ціна: $%.2f, Пам'ять: %dGB, Батарея: %dmAh]", 
                              brand, model, price, memoryGB, batteryCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
               memoryGB == phone.memoryGB &&
               batteryCapacity == phone.batteryCapacity &&
               Objects.equals(brand, phone.brand) &&
               Objects.equals(model, phone.model);
    }
}