package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки класу Phone.
 */
class PhoneTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Phone phone = new Phone("Samsung", "S23", 30000, 256);

        // Перевірка сеттера ціни
        assertThrows(IllegalArgumentException.class, () -> {
            phone.setPrice(-100);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        // Перевірка порожньої марки в конструкторі
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "Model X", 1000, 128);
        });
    }

    @Test
    void shouldCreateObjectWhenDataIsValid() {
        Phone phone = new Phone("Apple", "iPhone 15", 40000, 128);
        assertEquals("Apple", phone.getBrand());
        assertEquals(40000, phone.getPrice());
    }
}       