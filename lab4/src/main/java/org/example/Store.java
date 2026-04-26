package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null || quantity <= 0) {
            throw new IllegalArgumentException("Некоректні дані для додавання.");
        }

        for (StoreItem item : inventory) {
            if (item.getPhone().equals(ph)) {
                item.addQuantity(quantity);
                return;
            }
        }
        inventory.add(new StoreItem(ph, quantity));
    }

    public void update(Phone existingObject, Phone newObject) throws ObjectNotFoundException {
        Optional<StoreItem> itemToUpdate = inventory.stream()
                .filter(item -> item.getPhone().equals(existingObject))
                .findFirst();

        if (itemToUpdate.isPresent()) {
            itemToUpdate.get().setPhone(newObject);
        } else {
            throw new ObjectNotFoundException("Об'єкт для оновлення не знайдено: " + existingObject);
        }
    }

    public void delete(Phone phone) throws ObjectNotFoundException {
        boolean removed = inventory.removeIf(item -> item.getPhone().equals(phone));
        if (!removed) {
            throw new ObjectNotFoundException("Об'єкт для видалення не знайдено: " + phone);
        }
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
