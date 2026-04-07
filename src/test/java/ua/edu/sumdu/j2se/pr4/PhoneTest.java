package ua.edu.sumdu.j2se.pr4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Phone phone = new Phone("Apple", "iPhone 15", 999.0, 128, 3000, OSType.IOS);
        assertThrows(IllegalArgumentException.class, () -> phone.setPrice(-500.0));
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> new Phone("", "Galaxy", 500.0, 64, 4000, OSType.ANDROID));
    }
}