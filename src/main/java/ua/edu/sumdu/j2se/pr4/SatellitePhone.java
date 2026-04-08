package ua.edu.sumdu.j2se.pr4;

/**
 * Клас, що описує супутниковий телефон.
 * Наслідується від базового Phone.
 */
public class SatellitePhone extends Phone {
    private String satelliteNetwork;
    private boolean isWaterproof;

    public SatellitePhone(String brand, String model, double price, int memoryGB, int batteryCapacity, OSType osType, String satelliteNetwork, boolean isWaterproof) {
        super(brand, model, price, memoryGB, batteryCapacity, osType);
        setSatelliteNetwork(satelliteNetwork);
        this.isWaterproof = isWaterproof;
    }

    public void setSatelliteNetwork(String network) {
        if (network == null || network.trim().isEmpty()) throw new IllegalArgumentException("Мережа не може бути порожньою.");
        this.satelliteNetwork = network.trim();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Супутниковий] Мережа: %s, Водонепроникний: %b", satelliteNetwork, isWaterproof);
    }
}