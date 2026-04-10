package ua.edu.sumdu.j2se.pr4;

/**
 * Кастомний виняток для ситуацій, коли об'єкт не знайдено у колекції.
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}