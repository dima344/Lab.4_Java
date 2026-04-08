package ua.edu.sumdu.j2se.pr4;

public class GamingPhone extends SmartPhone {
    private boolean hasCoolingSystem;
    private int triggerButtonsCount;

    // Порожній конструктор для Gson
    public GamingPhone() {
        this.type = "GamingPhone";
    }

    public GamingPhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, double screenSize, boolean hasNFC, boolean hasCoolingSystem, int triggerButtonsCount) {
        super(brand, model, price, memoryGB, batteryCapacity, osType, screenSize, hasNFC);
        this.type = "GamingPhone";
        this.hasCoolingSystem = hasCoolingSystem;
        setTriggerButtonsCount(triggerButtonsCount);
    }

    public boolean isHasCoolingSystem() { return hasCoolingSystem; }
    public void setHasCoolingSystem(boolean hasCoolingSystem) { this.hasCoolingSystem = hasCoolingSystem; }

    public int getTriggerButtonsCount() { return triggerButtonsCount; }
    public void setTriggerButtonsCount(int count) {
        if (count < 0) throw new IllegalArgumentException("Кількість тригерів не може бути від'ємною.");
        this.triggerButtonsCount = count;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Охолодження: %b, Тригери: %d шт.", hasCoolingSystem, triggerButtonsCount);
    }
}