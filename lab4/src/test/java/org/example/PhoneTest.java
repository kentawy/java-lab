package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки базового класу Phone та його сетерів.
 */
class PhoneTest {

    @Test
    void constructorShouldSetAllFieldsCorrectly() {
        try {
            // Створюємо об'єкт через конкретний підклас, оскільки Phone - абстрактний
            Phone phone = new SmartPhone("Samsung", "Galaxy S21", 25000, 128, OperatingSystem.ANDROID, 64.0);

            assertEquals("Samsung", phone.getBrand());
            assertEquals("Galaxy S21", phone.getModel());
            assertEquals(25000, phone.getPrice());
            assertEquals(128, phone.getStorageCapacity());
            assertEquals(OperatingSystem.ANDROID, phone.getOs());
        } catch (InvalidFieldValueException e) {
            fail("Конструктор не повинен кидати виняток при коректних даних: " + e.getMessage());
        }
    }

    @Test
    void setBrandShouldThrowInvalidFieldValueExceptionForNullOrEmpty() throws InvalidFieldValueException {
        Phone phone = new SmartPhone("Test", "Test", 1, 1, OperatingSystem.OTHER, 1);

        // Перевірка на null
        assertThrows(InvalidFieldValueException.class, () -> phone.setBrand(null));

        // Перевірка на порожній рядок
        assertThrows(InvalidFieldValueException.class, () -> phone.setBrand(""));

        // Перевірка на рядок з пробілів
        assertThrows(InvalidFieldValueException.class, () -> phone.setBrand("   "));
    }

    @Test
    void setPriceShouldThrowInvalidFieldValueExceptionForZeroOrNegative() throws InvalidFieldValueException {
        Phone phone = new SmartPhone("Test", "Test", 1, 1, OperatingSystem.OTHER, 1);

        assertThrows(InvalidFieldValueException.class, () -> phone.setPrice(0));

        assertThrows(InvalidFieldValueException.class, () -> phone.setPrice(-100));
    }

    @Test
    void copyConstructorShouldCopyBaseFields() throws InvalidFieldValueException {
        Phone original = new SmartPhone("Apple", "iPhone 12", 28000, 256, OperatingSystem.IOS, 12.0);
        Phone copy = new Phone(original) {}; // Використовуємо анонімний клас для копії

        // Перевіряємо, що базові поля були скопійовані правильно
        assertEquals(original.getBrand(), copy.getBrand());
        assertEquals(original.getModel(), copy.getModel());
        assertEquals(original.getPrice(), copy.getPrice());
        assertEquals(original.getStorageCapacity(), copy.getStorageCapacity());
        assertEquals(original.getOs(), copy.getOs());

        // Перевіряємо, що це різні об'єкти в пам'яті
        assertNotSame(original, copy);
    }
}
