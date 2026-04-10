package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;
import java.util.UUID;

public abstract class Phone implements Comparable<Phone>, Identifiable {
    protected UUID uuid;
    protected String type;
    private String brand;
    private String model;
    private double price;
    private int memoryGB;
    private int batteryCapacity;
    private OSType osType;

    public Phone() {
        this.uuid = UUID.randomUUID();
    }

    public Phone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType) {
        this.uuid = UUID.randomUUID();
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setMemoryGB(memoryGB);
        setBatteryCapacity(batteryCapacity);
        setOsType(osType);
    }

    @Override
    public UUID getUuid() { return uuid; }

    // ЦЕЙ МЕТОД ПОТРІБЕН ДЛЯ GUI (MainApp.java)
    public String toShortString() {
        return String.format("%s %s | UUID: %s", brand, model, uuid.toString());
    }

    @Override
    public int compareTo(Phone other) {
        return Double.compare(this.price, other.price);
    }

    public String getType() { return type; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Бренд порожній");
        this.brand = brand.trim();
    }
    public String getModel() { return model; }
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) throw new IllegalArgumentException("Модель порожня");
        this.model = model.trim();
    }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Ціна має бути > 0");
        this.price = price;
    }
    public int getMemoryGB() { return memoryGB; }
    public void setMemoryGB(int memoryGB) { this.memoryGB = memoryGB; }
    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public OSType getOsType() { return osType; }
    public void setOsType(OSType osType) { this.osType = osType; }

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
               Objects.equals(brand, phone.brand) && Objects.equals(model, phone.model);
    }
}