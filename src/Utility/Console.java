package Utility;

import java.util.Scanner;

/**
 * Интерфейс {@code Console} должен быть реализован классом, предназначенным для работы с консолью.
 */
public interface Console {
    /**
     * Печатает объект без переноса строки.
     *
     * @param obj печатаемый объект
     */
    void print(Object obj);

    /**
     * Печатает объект с переносом строки.
     *
     * @param obj печатаемый объект
     */
    void println(Object obj);

    /**
     * Печатает текст ошибки.
     *
     * @param obj текст печатаемой ошибки
     */
    void printError(Object obj);

    /**
     * Проверяет, возможно ли чтение из консоли.
     *
     * @return {@code true}, если можно прочитать следующую строку, иначе {@code false}
     */
    boolean isCanReadln();

    /**
     * Считывает строку из консоли.
     *
     * @return считанная строка
     */
    String readln();

}
