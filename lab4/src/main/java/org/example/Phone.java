package org.example;

import java.util.Objects;
import java.util.UUID;

/**
 * Базовий клас, що описує мобільний телефон.
 */
public abstract class Phone implements Comparable<Phone>, Identifiable {
    private UUID uuid;
    private String brand;
    private String model;
    private double price;
    private int storageCapacity;
    private OperatingSystem os;

    public Phone(String brand, String model, double price, int storageCapacity, OperatingSystem os) {
        this.uuid = UUID.randomUUID();
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setStorageCapacity(storageCapacity);
        setOs(os);
    }

    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }
        this.uuid = UUID.randomUUID(); // Копія отримує свій унікальний ID
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.storageCapacity = other.storageCapacity;
        this.os = other.os;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    // Для завантаження з файлу (щоб ID не змінювався при кожному перезапуску)
    protected void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public String toFileString() {
        return "Phone;" + brand + ";" + model + ";" + price + ";" + storageCapacity + ";" + os.name() + ";" + uuid.toString();
    }

    @Override
    public String toString() {
        return String.format("Phone{ID='%s', brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s}",
                uuid.toString(), brand, model, price, storageCapacity, os);
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

    @Override
    public int compareTo(Phone other) {
        return this.brand.compareTo(other.brand);
    }
}