package org.example;

import java.util.Objects;

/**
 * Клас, що описує мобільний телефон.
 * Містить перевірку вхідних даних.
 */
public class Phone {
    private String brand;
    private String model;
    private double price;
    private int storageCapacity; // 4-й атрибут

    /**
     * Конструктор для створення об'єкта Phone.
     * @param brand Марка телефону
     * @param model Модель телефону
     * @param price Ціна (повинна бути > 0)
     * @param storageCapacity Об'єм пам'яті (повинен бути > 0)
     */
    public Phone(String brand, String model, double price, int storageCapacity) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setStorageCapacity(storageCapacity);
    }

    public String getBrand() { return brand; }

    /**
     * Встановлює марку телефону.
     * @param brand Назва марки
     * @throws IllegalArgumentException якщо рядок порожній
     */
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не може бути порожньою.");
        }
        this.brand = brand;
    }

    public String getModel() { return model; }

    /**
     * Встановлює модель телефону.
     * @param model Назва моделі
     * @throws IllegalArgumentException якщо рядок порожній
     */
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        this.model = model;
    }

    public double getPrice() { return price; }

    /**
     * Встановлює ціну телефону.
     * @param price Значення ціни
     * @throws IllegalArgumentException якщо ціна <= 0
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за нуль.");
        }
        this.price = price;
    }

    public int getStorageCapacity() { return storageCapacity; }

    /**
     * Встановлює об'єм пам'яті.
     * @param storageCapacity Значення в ГБ
     * @throws IllegalArgumentException якщо об'єм <= 0
     */
    public void setStorageCapacity(int storageCapacity) {
        if (storageCapacity <= 0) {
            throw new IllegalArgumentException("Об'єм пам'яті повинен бути більшим за нуль.");
        }
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String toString() {
        return String.format("Phone{brand='%s', model='%s', price=%.2f, storage=%dGB}",
                brand, model, price, storageCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
                storageCapacity == phone.storageCapacity &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(model, phone.model);
    }
}