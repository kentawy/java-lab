package org.example;

public class SmartPhone extends Phone {
    private double cameraMegapixels;

    public SmartPhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, double cameraMegapixels) throws InvalidFieldValueException {
        super(brand, model, price, storageCapacity, os);
        this.cameraMegapixels = cameraMegapixels;
    }

    public double getCameraMegapixels() {
        return cameraMegapixels;
    }

    @Override
    public String toFileString() {
        return "SmartPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" + getStorageCapacity() + ";" + getOs().name() + ";" + cameraMegapixels;
    }

    @Override
    public String toString() {
        return String.format("SmartPhone{brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s, camera=%.1fMP}",
                getBrand(), getModel(), getPrice(), getStorageCapacity(), getOs(), cameraMegapixels);
    }
}
