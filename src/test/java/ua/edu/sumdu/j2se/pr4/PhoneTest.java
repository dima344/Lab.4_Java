package ua.edu.sumdu.j2se.pr4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Клас для тестування валідації класу Phone.
 */
class PhoneTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        // Створюємо валідний об'єкт
        Phone phone = new Phone("Apple", "iPhone 15", 999.0, 128, 3000);
        
        // Перевіряємо, чи вилетить помилка, якщо поставити від'ємну ціну
        assertThrows(IllegalArgumentException.class, () -> {
            phone.setPrice(-500.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        // Перевіряємо, чи вилетить помилка при спробі створити телефон з пустим брендом
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "Galaxy", 500.0, 64, 4000);
        });
    }
}