package ua.edu.sumdu.j2se.pr4;

/**
 * Кастомний виняток для помилок валідації вхідних полів об'єкта.
 */
public class InvalidFieldValueException extends RuntimeException {
    public InvalidFieldValueException(String message) {
        super(message);
    }
}