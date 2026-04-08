package ua.edu.sumdu.j2se.pr4;

public class SmartPhone extends Phone {
    private double screenSize;
    private boolean hasNFC;

    public SmartPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, double screenSize, boolean hasNFC) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        setScreenSize(screenSize);
        this.hasNFC = hasNFC;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize <= 0) throw new IllegalArgumentException("Розмір екрана має бути додатним.");
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Смартфон] Екран: %.1f\", NFC: %b", screenSize, hasNFC);
    }
}