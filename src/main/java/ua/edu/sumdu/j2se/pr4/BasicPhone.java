package ua.edu.sumdu.j2se.pr4;

public class BasicPhone extends Phone {
    
    public BasicPhone() {
        this.type = "BasicPhone";
    }

    public BasicPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        this.type = "BasicPhone";
    }
}