package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void shouldSortPhonesByBrand() {
        Phone p1 = new SmartPhone("ZTE", "Axon 30", 15000, 128, OperatingSystem.ANDROID, 64.0);
        Phone p2 = new SmartPhone("Apple", "iPhone 15", 40000, 128, OperatingSystem.IOS, 48.0);
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p1) < 0);
        assertEquals(0, p1.compareTo(p1));
    }

    @Test
    void shouldCreateSmartPhoneAndDemonstratePolymorphism() {
        Phone smartPhone = new SmartPhone("Samsung", "S24 Ultra", 50000, 256, OperatingSystem.ANDROID, 200.0);
        assertTrue(smartPhone.toString().contains("camera=200.0MP"));
    }

    @Test
    void shouldCreateGamingPhone() {
        Phone gamingPhone = new GamingPhone("Asus", "ROG Phone 8", 45000, 512, OperatingSystem.ANDROID, 50.0, "AeroActive Cooler");
        assertTrue(gamingPhone.toString().contains("cooling='AeroActive Cooler'"));
    }

    @Test
    void shouldCreateFoldablePhone() {
        Phone foldPhone = new FoldablePhone("Samsung", "Z Fold 5", 60000, 512, OperatingSystem.ANDROID, 50.0, 2);
        assertTrue(foldPhone.toString().contains("screens=2"));
    }

    @Test
    void shouldThrowExceptionForInvalidScreensInFoldable() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FoldablePhone("Motorola", "Razr", 40000, 256, OperatingSystem.ANDROID, 32.0, 1);
        });
    }
}
