package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

public class Phone {
    private String brand;
    private String model;
    private double price;
    private int memoryGB;

    public Phone(String brand, String model, double price, int memoryGB) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.memoryGB = memoryGB;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getMemoryGB() { return memoryGB; }
    public void setMemoryGB(int memoryGB) { this.memoryGB = memoryGB; }

    @Override
    public String toString() {
        return "Phone{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + 
               ", price=$" + price + ", memoryGB=" + memoryGB + "GB" + '}';
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