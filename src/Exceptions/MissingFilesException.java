package Exceptions;

/**
 * Сигнализирует о том, что пользователь не ввел названия входных файлов.
 * <p>
 * Исключение {@code MissingFilesException} выбрасывается в случае,
 * если пользователь не указал названия файлов для обработки.
 * </p>
 */
public class MissingFilesException extends Exception {
    /**
     * Создает {@code MissingFilesException}, с подробным сообщением.
     *
     * @param message уточнение проблемы
     */
    public MissingFilesException(String message) {
        super(message);
    }
}
