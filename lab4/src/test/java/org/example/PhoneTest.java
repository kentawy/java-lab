package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки класу Phone.
 */
class PhoneTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Phone phone = new Phone("Samsung", "S23", 30000, 256, OperatingSystem.ANDROID);

        assertThrows(IllegalArgumentException.class, () -> {
            phone.setPrice(-100);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "Model X", 1000, 128, OperatingSystem.OTHER);
        });
    }

    @Test
    void shouldCreateObjectWhenDataIsValid() {
        Phone phone = new Phone("Apple", "iPhone 15", 40000, 128, OperatingSystem.IOS);
        assertEquals("Apple", phone.getBrand());
        assertEquals(40000, phone.getPrice());
        assertEquals(OperatingSystem.IOS, phone.getOs());
    }

    @Test
    void shouldCorrectlyCopyObject() {
        Phone original = new Phone("Google", "Pixel 8", 35000, 256, OperatingSystem.ANDROID);
        Phone copy = new Phone(original);

        assertEquals(original.getBrand(), copy.getBrand());
        assertEquals(original.getPrice(), copy.getPrice());
        assertNotSame(original, copy); // Перевіряємо, що це різні об'єкти в пам'яті
    }

    @Test
    void staticCounterShouldIncrement() {
        int initialCount = Phone.getTotalPhonesCreated();
        new Phone("Nokia", "3310", 1000, 1, OperatingSystem.OTHER);
        assertEquals(initialCount + 1, Phone.getTotalPhonesCreated());
    }
}