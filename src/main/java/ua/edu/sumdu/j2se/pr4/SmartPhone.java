package ua.edu.sumdu.j2se.pr4;

public class SmartPhone extends Phone {
    private double screenSize;
    private boolean hasNFC;

    // Порожній конструктор для Gson
    public SmartPhone() {
        this.type = "SmartPhone";
    }

    public SmartPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, double screenSize, boolean hasNFC) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        this.type = "SmartPhone";
        setScreenSize(screenSize);
        this.hasNFC = hasNFC;
    }

    public double getScreenSize() { return screenSize; }
    public void setScreenSize(double screenSize) {
        if (screenSize <= 0) throw new IllegalArgumentException("Розмір екрана має бути додатним.");
        this.screenSize = screenSize;
    }

    public boolean isHasNFC() { return hasNFC; }
    public void setHasNFC(boolean hasNFC) { this.hasNFC = hasNFC; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Екран: %.1f\", NFC: %b", screenSize, hasNFC);
    }
}