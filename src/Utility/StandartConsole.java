package Utility;

import java.util.Scanner;

/**
 * Предназначен для взаимодействия со стандартной консолью.
 * Этот класс предоставляет методы для печати и чтения с консоли.
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class StandartConsole implements Console {
    // Изначальный сканер
    private Scanner defScanner = new Scanner(System.in);

    /**
     * Метод вызывается для печати объекта без переноса каретки.
     *
     * @param obj печатаемый объект
     */
    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * Метод вызывается для печати объекта с переносом каретки.
     *
     * @param obj печатаемый объект
     */
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    /**
     * Метод вызывается для печати ошибки в консоль.
     *
     * @param obj текст печатаемой ошибки
     */
    @Override
    public void printError(Object obj) {
        System.err.println("Ошибка: " + obj);
    }

    /**
     * Метод вызывается для проверки возможности чтения из консоли.
     *
     * @return возможно ли чтение (true, если можно прочитать следующую строку, иначе false)
     */
    @Override
    public boolean isCanReadln() {
        return defScanner.hasNextLine();
    }

    /**
     * Метод вызывается для чтения строки из консоли или файла.
     *
     * @return прочитанная строка
     */
    @Override
    public String readln() {
        return defScanner.nextLine();
    }
}
