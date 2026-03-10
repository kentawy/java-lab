package org.example;

import java.util.Objects;

/**
 * Клас, що описує мобільний телефон.
 */
public class Phone {
    // Статичне поле для підрахунку кількості створених об'єктів
    private static int totalPhonesCreated = 0;

    private String brand;
    private String model;
    private double price;
    private int storageCapacity;
    private OperatingSystem os; // Використання enum

    /**
     * Основний конструктор для створення об'єкта Phone.
     */
    public Phone(String brand, String model, double price, int storageCapacity, OperatingSystem os) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setStorageCapacity(storageCapacity);
        setOs(os);
        totalPhonesCreated++; // Збільшуємо лічильник при створенні
    }

    /**
     * Конструктор копіювання.
     * @param other Об'єкт Phone, копію якого потрібно створити
     */
    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.storageCapacity = other.storageCapacity;
        this.os = other.os;
        totalPhonesCreated++; // Збільшуємо лічильник і для копій
    }

    // Статичний метод (гетер) для отримання кількості створених об'єктів
    public static int getTotalPhonesCreated() {
        return totalPhonesCreated;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не може бути порожньою.");
        }
        this.brand = brand;
    }

    public String getModel() { return model; }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        this.model = model;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за нуль.");
        }
        this.price = price;
    }

    public int getStorageCapacity() { return storageCapacity; }

    public void setStorageCapacity(int storageCapacity) {
        if (storageCapacity <= 0) {
            throw new IllegalArgumentException("Об'єм пам'яті повинен бути більшим за нуль.");
        }
        this.storageCapacity = storageCapacity;
    }

    public OperatingSystem getOs() { return os; }

    public void setOs(OperatingSystem os) {
        if (os == null) {
            throw new IllegalArgumentException("Операційна система не може бути null.");
        }
        this.os = os;
    }

    @Override
    public String toString() {
        return String.format("Phone{brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s}",
                brand, model, price, storageCapacity, os);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
                storageCapacity == phone.storageCapacity &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(model, phone.model) &&
                os == phone.os;
    }
}