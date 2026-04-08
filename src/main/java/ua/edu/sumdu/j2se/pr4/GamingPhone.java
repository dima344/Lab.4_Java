package ua.edu.sumdu.j2se.pr4;

/**
 * Клас, що описує ігровий смартфон.
 * Наслідується від SmartPhone.
 */
public class GamingPhone extends SmartPhone {
    private boolean hasCoolingSystem;
    private int triggerButtonsCount;

    public GamingPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, double screenSize, boolean hasNFC, boolean hasCoolingSystem, int triggerButtonsCount) {
        super(brand, model, price, memoryGB, batteryCapacity, osType, screenSize, hasNFC);
        this.hasCoolingSystem = hasCoolingSystem;
        setTriggerButtonsCount(triggerButtonsCount);
    }

    public void setTriggerButtonsCount(int count) {
        if (count < 0) throw new IllegalArgumentException("Кількість тригерів не може бути від'ємною.");
        this.triggerButtonsCount = count;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Ігровий] Охолодження: %b, Тригери: %d шт.", hasCoolingSystem, triggerButtonsCount);
    }
}