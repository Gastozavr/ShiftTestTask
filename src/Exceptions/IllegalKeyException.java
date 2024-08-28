package Exceptions;

/**
 * Сигнализирует о том, что введен неизвестный параметр программы.
 * <p>
 * Исключение {@code IllegalKeyException} выбрасывается в случае,
 * если был введен неизвестный или некорректный ключ программы.
 * </p>
 */
public class IllegalKeyException extends Exception {
    /**
     * Создает {@code IllegalKeyException}, с подробным сообщением.
     *
     * @param message уточнение проблемы
     */
    public IllegalKeyException(String message) {
        super(message);
    }
}
