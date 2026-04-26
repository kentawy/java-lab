package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки класів Store та StoreItem.
 */
class StoreTest {

    private Store store;
    private Phone testPhone;

    // Цей метод виконується перед КОЖНИМ тестом, створюючи "чистий" магазин
    @BeforeEach
    void setUp() throws InvalidFieldValueException {
        store = new Store("Test Store");
        // Phone тепер абстрактний, тому створюємо об'єкт конкретного класу
        testPhone = new SmartPhone("Apple", "iPhone 13", 30000, 128, OperatingSystem.IOS, 12.0);
    }

    @Test
    void shouldAddNewPhoneToInventory() {
        // Додаємо 5 айфонів
        store.addNewPhone(testPhone, 5);

        List<StoreItem> inventory = store.getInventory();
        assertEquals(1, inventory.size(), "В інвентарі має бути 1 запис (тип телефону)");
        assertEquals(5, inventory.get(0).getQuantity(), "Кількість має дорівнювати 5");
    }

    @Test
    void shouldIncreaseQuantityWhenAddingExistingPhone() {
        // Додаємо 5 айфонів
        store.addNewPhone(testPhone, 5);
        // Додаємо ЩЕ 3 ТАКИХ САМИХ айфони
        store.addNewPhone(testPhone, 3);

        List<StoreItem> inventory = store.getInventory();
        // Розмір інвентарю не має змінитись (це той самий товар)
        assertEquals(1, inventory.size(), "Новий запис не повинен створюватися для існуючого телефону");
        // Але кількість має стати 5 + 3 = 8
        assertEquals(8, inventory.get(0).getQuantity(), "Кількість має збільшитись до 8");
    }

    @Test
    void shouldCorrectlySearchByBrand() throws InvalidFieldValueException {
        store.addNewPhone(testPhone, 10);
        store.addNewPhone(new SmartPhone("Samsung", "S24", 40000, 256, OperatingSystem.ANDROID, 50), 5);

        List<StoreItem> appleResults = store.searchByBrand("Apple");
        assertEquals(1, appleResults.size(), "Повинен знайтись 1 запис Apple");
        assertEquals("iPhone 13", appleResults.get(0).getPhone().getModel());

        List<StoreItem> nokiaResults = store.searchByBrand("Nokia");
        assertTrue(nokiaResults.isEmpty(), "Для Nokia результат має бути порожнім");
    }

    @Test
    void shouldThrowExceptionWhenAddingNegativeQuantityToStoreItem() {
        StoreItem item = new StoreItem(testPhone, 10);

        // Перевіряємо, що при спробі додати від'ємну кількість викидається помилка
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            item.addQuantity(-5);
        });

        assertTrue(exception.getMessage().contains("позитивну кількість"));
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingObject() {
        // Створюємо телефон, якого НЕМАЄ в магазині
        Phone nonExistingPhone;
        try {
            nonExistingPhone = new SmartPhone("Nokia", "3310", 1000, 1, OperatingSystem.OTHER, 0);
        } catch (InvalidFieldValueException e) {
            fail("Test setup failed: " + e.getMessage());
            return;
        }

        // Перевіряємо, що спроба видалити його кине ObjectNotFoundException
        assertThrows(ObjectNotFoundException.class, () -> {
            store.delete(nonExistingPhone);
        });
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionForEmptyBrand() {
        // Перевіряємо, що конструктор Phone кине InvalidFieldValueException при порожньому бренді
        assertThrows(InvalidFieldValueException.class, () -> {
            new SmartPhone("", "Some Model", 100, 1, OperatingSystem.ANDROID, 10);
        });
    }
}
