package ua.edu.sumdu.j2se.pr4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Клас для тестування кастомних винятків.
 */
class PhoneTest {

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenNegativePrice() {
        BasicPhone phone = new BasicPhone("Apple", "iPhone 15", 999.0, 128, 3000, OSType.IOS);
        
        // Перевіряємо наш кастомний виняток (ЛР №18)
        assertThrows(InvalidFieldValueException.class, () -> {
            phone.setPrice(-50.0);
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingPhone() {
        Store store = new Store("Test Store");
        BasicPhone nonExistingPhone = new BasicPhone("Fake", "Model", 10.0, 1, 1, OSType.OTHER);
        
        // Перевіряємо, що видалення неіснуючого об'єкта кидає наш виняток (ЛР №18)
        assertThrows(ObjectNotFoundException.class, () -> {
            store.delete(nonExistingPhone);
        });
    }
}