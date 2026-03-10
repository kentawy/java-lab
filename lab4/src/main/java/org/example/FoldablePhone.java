package org.example;

/**
 * Клас для складаних смартфонів.
 * Успадковується від SmartPhone і додає кількість екранів.
 */
public class FoldablePhone extends SmartPhone {
    private int numberOfScreens;

    public FoldablePhone(String brand, String model, double price, int storageCapacity,
                         OperatingSystem os, double cameraMegapixels, int numberOfScreens) {
        super(brand, model, price, storageCapacity, os, cameraMegapixels);
        setNumberOfScreens(numberOfScreens);
    }

    public int getNumberOfScreens() {
        return numberOfScreens;
    }

    public void setNumberOfScreens(int numberOfScreens) {
        if (numberOfScreens < 2) {
            throw new IllegalArgumentException("Складаний телефон повинен мати щонайменше 2 екрани.");
        }
        this.numberOfScreens = numberOfScreens;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", screens=" + numberOfScreens + "}");
    }
}