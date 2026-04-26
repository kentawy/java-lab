package org.example;

public class GamingPhone extends SmartPhone {
    private String coolingSystem;

    public GamingPhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, double cameraMegapixels, String coolingSystem) throws InvalidFieldValueException {
        super(brand, model, price, storageCapacity, os, cameraMegapixels);
        this.coolingSystem = coolingSystem;
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    @Override
    public String toFileString() {
        return "GamingPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" + getStorageCapacity() + ";" + getOs().name() + ";" + getCameraMegapixels() + ";" + coolingSystem;
    }

    @Override
    public String toString() {
        return String.format("GamingPhone{brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s, camera=%.1fMP, cooling='%s'}",
                getBrand(), getModel(), getPrice(), getStorageCapacity(), getOs(), getCameraMegapixels(), coolingSystem);
    }
}
