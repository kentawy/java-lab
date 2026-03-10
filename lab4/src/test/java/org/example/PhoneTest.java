package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки класів Phone, SmartPhone та KeypadPhone.
 */
class PhoneTest {

    @Test
    void shouldCreateBasePhone() {
        Phone phone = new Phone("Apple", "iPhone 15", 40000, 128, OperatingSystem.IOS);
        assertEquals("Apple", phone.getBrand());
        assertEquals(40000, phone.getPrice());
        assertTrue(phone.toString().contains("OS=IOS"));
    }

    @Test
    void shouldCreateSmartPhoneAndDemonstratePolymorphism() {
        // Посилання базового типу вказує на об'єкт похідного класу
        Phone smartPhone = new SmartPhone("Samsung", "S24 Ultra", 50000, 256, OperatingSystem.ANDROID, 200.0);

        // Перевіряємо, що викликається перевизначений toString()
        assertTrue(smartPhone.toString().contains("camera=200.0MP"));
        assertEquals("Samsung", smartPhone.getBrand());
    }

    @Test
    void shouldCreateKeypadPhoneAndDemonstratePolymorphism() {
        Phone keypadPhone = new KeypadPhone("Nokia", "3310", 1500, 1, OperatingSystem.OTHER, true);

        assertTrue(keypadPhone.toString().contains("flashlight=yes"));
    }

    @Test
    void shouldThrowExceptionForInvalidCameraInSmartPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SmartPhone("Xiaomi", "Mi 13", 30000, 128, OperatingSystem.ANDROID, -10.0);
        });
    }
}