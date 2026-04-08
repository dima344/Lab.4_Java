package ua.edu.sumdu.j2se.pr4;

public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    public KeypadPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, boolean hasFlashlight) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        this.hasFlashlight = hasFlashlight;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Кнопковий] Ліхтарик: %b", hasFlashlight);
    }
}