package ua.edu.sumdu.j2se.pr4;

public class SatellitePhone extends Phone {
    private String satelliteNetwork;
    private boolean isWaterproof;

    // Порожній конструктор для Gson
    public SatellitePhone() {
        this.type = "SatellitePhone";
    }

    public SatellitePhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, String satelliteNetwork, boolean isWaterproof) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        this.type = "SatellitePhone";
        setSatelliteNetwork(satelliteNetwork);
        this.isWaterproof = isWaterproof;
    }

    public String getSatelliteNetwork() { return satelliteNetwork; }
    public void setSatelliteNetwork(String network) {
        if (network == null || network.trim().isEmpty()) throw new IllegalArgumentException("Мережа не може бути порожньою.");
        this.satelliteNetwork = network.trim();
    }

    public boolean isWaterproof() { return isWaterproof; }
    public void setWaterproof(boolean isWaterproof) { this.isWaterproof = isWaterproof; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Мережа: %s, Водонепроникний: %b", satelliteNetwork, isWaterproof);
    }
}