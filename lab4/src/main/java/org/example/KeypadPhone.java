package org.example;

public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    public KeypadPhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, boolean hasFlashlight) throws InvalidFieldValueException {
        super(brand, model, price, storageCapacity, os);
        this.hasFlashlight = hasFlashlight;
    }

    public boolean hasFlashlight() {
        return hasFlashlight;
    }

    @Override
    public String toFileString() {
        return "KeypadPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" + getStorageCapacity() + ";" + getOs().name() + ";" + hasFlashlight;
    }

    @Override
    public String toString() {
        return String.format("KeypadPhone{brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s, flashlight=%b}",
                getBrand(), getModel(), getPrice(), getStorageCapacity(), getOs(), hasFlashlight);
    }
}
