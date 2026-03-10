package org.example;

/**
 * Клас-обгортка, що зберігає телефон та його кількість у магазині.
 */
public class StoreItem {
    private Phone phone;
    private int quantity;

    public StoreItem(Phone phone, int quantity) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною.");
        }
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Можна додавати лише позитивну кількість.");
        }
        this.quantity += amount;
    }

    /**
     * Форматує запис для виведення в консоль.
     */
    @Override
    public String toString() {
        return phone.toString() + " | Кількість у магазині: " + quantity + " шт.";
    }

    /**
     * Форматує запис для збереження у файл (додає кількість у кінець рядка).
     */
    public String toFileString() {
        return phone.toFileString() + ";" + quantity;
    }
}
