package org.example;

/**
 * Похідний клас для кнопкових телефонів.
 */
public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    public KeypadPhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, boolean hasFlashlight) {
        super(brand, model, price, storageCapacity, os);
        this.hasFlashlight = hasFlashlight;
    }

    public boolean isHasFlashlight() {
        return hasFlashlight;
    }

    public void setHasFlashlight(boolean hasFlashlight) {
        this.hasFlashlight = hasFlashlight;
    }

    @Override
    public String toFileString() {
        return "KeypadPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" +
                getStorageCapacity() + ";" + getOs().name() + ";" + hasFlashlight;
    }

    /**
     * Перевизначення методу для демонстрації поліморфізму.
     */
    @Override
    public String toString() {
        return super.toString().replace("}", ", flashlight=" + (hasFlashlight ? "yes" : "no") + "}");
    }
}