package org.example;

/**
 * Похідний клас для смартфонів.
 * Демонструє успадкування від базового класу Phone.
 */
public class SmartPhone extends Phone {
    private double cameraMegapixels;

    public SmartPhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, double cameraMegapixels) {
        // Виклик конструктора базового класу
        super(brand, model, price, storageCapacity, os);
        setCameraMegapixels(cameraMegapixels);
    }

    @Override
    public String toFileString() {
        return "SmartPhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" +
                getStorageCapacity() + ";" + getOs().name() + ";" + cameraMegapixels;
    }

    public double getCameraMegapixels() {
        return cameraMegapixels;
    }

    public void setCameraMegapixels(double cameraMegapixels) {
        if (cameraMegapixels <= 0) {
            throw new IllegalArgumentException("Камера повинна мати позитивне значення мегапікселів.");
        }
        this.cameraMegapixels = cameraMegapixels;
    }

    /**
     * Перевизначення методу для демонстрації поліморфізму.
     */
    @Override
    public String toString() {
        // Беремо рядок базового класу і додаємо специфічну інформацію
        return super.toString().replace("}", ", camera=" + cameraMegapixels + "MP}");
    }
}