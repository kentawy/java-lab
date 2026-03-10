package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас-контейнер (Агрегація), що представляє магазин телефонів.
 */
public class Store {
    private String storeName;
    private List<StoreItem> inventory;

    public Store(String storeName) {
        this.storeName = storeName;
        this.inventory = new ArrayList<>();
    }

    public String getStoreName() {
        return storeName;
    }

    public List<StoreItem> getInventory() {
        return inventory;
    }

    /**
     * Додає телефон до магазину. Якщо такий вже є - збільшує кількість.
     */
    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null || quantity <= 0) {
            throw new IllegalArgumentException("Некоректні дані для додавання.");
        }

        // Шукаємо, чи є вже такий телефон у колекції
        for (StoreItem item : inventory) {
            if (item.getPhone().equals(ph)) {
                item.addQuantity(quantity);
                return; // Збільшили кількість і вийшли
            }
        }

        // Якщо не знайшли - додаємо новий запис
        inventory.add(new StoreItem(ph, quantity));
    }

    // --- МЕТОДИ ПОШУКУ (Без Stream API) ---

    public List<StoreItem> searchByBrand(String brand) {
        List<StoreItem> found = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand.trim())) {
                found.add(item);
            }
        }
        return found;
    }

    public List<StoreItem> searchByPriceRange(double min, double max) {
        List<StoreItem> found = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getPrice() >= min && item.getPhone().getPrice() <= max) {
                found.add(item);
            }
        }
        return found;
    }

    public List<StoreItem> searchByOs(OperatingSystem targetOs) {
        List<StoreItem> found = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getOs() == targetOs) {
                found.add(item);
            }
        }
        return found;
    }
}