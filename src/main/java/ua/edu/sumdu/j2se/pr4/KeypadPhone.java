package ua.edu.sumdu.j2se.pr4;

public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    // Порожній конструктор для Gson
    public KeypadPhone() {
        this.type = "KeypadPhone";
    }

    public KeypadPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, boolean hasFlashlight) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        this.type = "KeypadPhone";
        this.hasFlashlight = hasFlashlight;
    }

    public boolean isHasFlashlight() { return hasFlashlight; }
    public void setHasFlashlight(boolean hasFlashlight) { this.hasFlashlight = hasFlashlight; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Ліхтарик: %b", hasFlashlight);
    }
}