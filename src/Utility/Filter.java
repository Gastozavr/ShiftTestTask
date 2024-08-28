package Utility;

import Exceptions.IllegalKeyException;
import Exceptions.MissingFilesException;
import Managers.FileManager;
import Managers.StatisticsManager;

import java.math.BigInteger;
import java.util.*;

/**
 * Предназначен для фильтрации данных из файлов по типам, с возможностью получения
 * статистики по каждому типу и указания префикса файла и пути к нему.
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Filter extends Utilite {
    //Файловый менеджер
    private FileManager fileManager;
    //Консоль, в которую выводятся информация и ошибки
    private Console console;
    //Список параметров, указанных при запуске программы
    private List<String> parametres;

    //Список параметров фильтра
    private List<String> keys = new ArrayList<>();
    //Префикс выходных файлов
    private String prefix = "";
    //Путь к выходным файлам
    private String filepath = "";
    //Список с названиями входных файлов
    private List<String> files = new ArrayList<>();
    //Флаг дописывания в конец файла
    private boolean append = false;

    //Список со строковыми данными
    private List<String> strings = new ArrayList<>();
    //Список с целочисленными данными
    private List<BigInteger> integers = new ArrayList<>();
    //Список с вещественными данными
    private List<Double> floats = new ArrayList<>();

    //Утилита определения типов данных
    private TypeChecker typeChecker = TypeChecker.getInstance();
    //Утилита статистики
    private StatisticsManager statisticsManager = StatisticsManager.getInstance();

    /**
     * Создае утилиту, сортирующую содержимое файлов.
     *
     * @param fileManager файловый менеджер
     * @param console     консоль
     * @param parametres  список с параметрами запуска программы
     */
    public Filter(FileManager fileManager, Console console, String[] parametres) {
        this.fileManager = fileManager;
        this.console = console;
        this.parametres = new ArrayList<>(Arrays.asList(parametres));

    }

    /**
     * Метод вызывается для сортировки параметров запуска программы.
     *
     * @throws IllegalKeyException   выбрасывается в случае ввода неизвестного ключа
     * @throws MissingFilesException выбрасывается в случае отсутствия введенных пользователем названий файлов
     */
    private void sortArgs() throws IllegalKeyException, MissingFilesException {
        List<String> availableKeys = Arrays.asList("-p", "-s", "-f", "-o", "-a");
        Iterator<String> iterator = parametres.iterator();
        while (iterator.hasNext()) {
            String param = iterator.next();
            if (param.charAt(0) == '-') {
                if (availableKeys.contains(param)) {
                    if (param.equals("-p")) {
                        prefix = iterator.next();
                    } else if (param.equals("-o")) {
                        filepath = iterator.next();
                    } else keys.add(param);
                } else throw new IllegalKeyException("Введен незнакомый аргумент.");
            } else files.add(param);
        }
        if (files.size() == 0) {
            throw new MissingFilesException("Не введены названия файлов.");
        }
    }

    /**
     * Метод вызывается для печати в консоль обработанных параметров.
     */
    private void getArguments() {
        console.print("Получено: ");
        for (var e : parametres) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
        console.print("Ключи: ");
        for (var e : keys) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
        console.print("Путь к файлам: ");
        console.println(filepath);
        console.print("Префикс: ");
        console.println(prefix);
        console.print("Файлы: ");
        for (var e : files) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
    }

    /**
     * Метод вызывается для сортировки данных по типам.
     *
     * @param data список входных данных
     */
    private void sortData(List<String> data) {

        for (var e : data) {
            switch (typeChecker.determineType(e)) {
                case "Integer":
                    integers.add(new BigInteger(e));
                    break;
                case "Float":
                    floats.add(Double.parseDouble(e));
                    break;
                case "String":
                    strings.add(e);
                    break;
            }
        }

    }

    /**
     * Метод вызывается для выполнения сортировки содержимого файлов по типам с возможным
     * получением статистики и записью данных в файлы.
     */
    public void solve() {
        try {
            sortArgs();
            sortData(fileManager.readFiles(files));
            if (keys.contains("-f")) {
                console.print(statisticsManager.getFullIntegersStatistics(integers));
                console.print(statisticsManager.getFullFloatsStatistics(floats));
                console.print(statisticsManager.getFullStringStatistics(strings));

            } else if (keys.contains("-s")) {
                console.print(statisticsManager.getShortNumberStatistics(integers));
                console.print(statisticsManager.getShortNumberStatistics(floats));
                console.print(statisticsManager.getShortStringStatistics(strings));
            }
            if (keys.contains("-a")) append = true;
            fileManager.write(integers, filepath, prefix, "integers.txt", append);
            fileManager.write(floats, filepath, prefix, "floats.txt", append);
            fileManager.write(strings, filepath, prefix, "strings.txt", append);
            append = false;

        } catch (IllegalKeyException | MissingFilesException e) {
            console.printError(e.getMessage());
        } catch (NoSuchElementException e) {
            console.printError("Введено недостаточное количество аргументов.");
        }
    }
}
