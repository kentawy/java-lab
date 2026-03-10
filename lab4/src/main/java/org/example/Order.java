package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що описує замовлення клієнта.
 * Демонструє відношення агрегації (містить колекцію об'єктів Phone).
 */
public class Order {
    private String orderId;
    private List<Phone> phones;

    /**
     * Конструктор для створення замовлення.
     * @param orderId Унікальний ідентифікатор замовлення
     */
    public Order(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID замовлення не може бути порожнім.");
        }
        this.orderId = orderId;
        this.phones = new ArrayList<>();
    }

    /**
     * Додає телефон до замовлення (Агрегація).
     * @param phone Об'єкт телефону
     */
    public void addPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null.");
        }
        this.phones.add(phone);
    }

    /**
     * Виводить деталі замовлення та рахує загальну суму (без використання Stream API).
     */
    public void printOrderDetails() {
        System.out.println("\n--- Деталі замовлення #" + orderId + " ---");
        double total = 0.0;

        if (phones.isEmpty()) {
            System.out.println("Замовлення порожнє.");
            return;
        }

        for (Phone p : phones) {
            System.out.println("- " + p.toString());
            total += p.getPrice();
        }
        System.out.printf("Загальна сума замовлення: %.2f\n", total);
    }
}