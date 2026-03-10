package org.example;

/**
 * Клас для ігрових смартфонів.
 * Успадковується від SmartPhone і додає інформацію про систему охолодження.
 */
public class GamingPhone extends SmartPhone {
    private String coolingSystem;

    public GamingPhone(String brand, String model, double price, int storageCapacity,
                       OperatingSystem os, double cameraMegapixels, String coolingSystem) {
        super(brand, model, price, storageCapacity, os, cameraMegapixels);
        setCoolingSystem(coolingSystem);
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    public void setCoolingSystem(String coolingSystem) {
        if (coolingSystem == null || coolingSystem.trim().isEmpty()) {
            throw new IllegalArgumentException("Система охолодження не може бути порожньою.");
        }
        this.coolingSystem = coolingSystem;
    }

    @Override
    public String toFileString() {
        return "GamingPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" +
                getStorageCapacity() + ";" + getOs().name() + ";" + getCameraMegapixels() + ";" + coolingSystem;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", cooling='" + coolingSystem + "'}");
    }
}