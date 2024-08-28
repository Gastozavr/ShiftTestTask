package Managers;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.List;
import java.util.OptionalLong;

/**
 * Предназначен для ведения статистики.
 * Обеспечивает методы для получения полной и краткой статистики по числовым и строковым данным.
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class StatisticsManager {
    private static StatisticsManager instance;

    private StatisticsManager() {
    }

    public static StatisticsManager getInstance() {
        if (instance == null) {
            instance = new StatisticsManager();
        }
        return instance;
    }

    /**
     * Метод вызывается для получения полной статистики по вещественным числам.
     * Статистика включает в себя количество чисел, минимальное и максимальное число, сумму и среднее арифметическое.
     *
     * @param numbers список входных вещественных чисел
     * @return строка с полной статистикой по введенным вещественным числам
     */
    public String getFullFloatsStatistics(List<Double> numbers) {
        if (numbers.isEmpty()) return "";
        double minNumber = numbers.stream().mapToDouble(Number::doubleValue).min().orElse(Double.NaN);
        double maxNumber = numbers.stream().mapToDouble(Number::doubleValue).max().orElse(Double.NaN);
        double sumNumber = numbers.stream().mapToDouble(Number::doubleValue).sum();
        double avgNumber = sumNumber / numbers.size();
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32;1mСтатистика по вещественным числам:\n");
        sb.append("Количество чисел: ");
        sb.append(numbers.size());
        sb.append("\n");
        sb.append("Минимальное число: ");
        sb.append(minNumber);
        sb.append("\n");
        sb.append("Максимальное число: ");
        sb.append(maxNumber);
        sb.append("\n");
        sb.append("Сумма чисел: ");
        sb.append(sumNumber);
        sb.append("\n");
        sb.append("Среднее арифметическое: ");
        sb.append(avgNumber);
        sb.append("\n\n\u001B[0m");
        return sb.toString();
    }

    /**
     * Метод вызывается для получения полной статистики по вещественным числам.
     * Статистика включает в себя количество чисел, минимальное и максимальное число, сумму и среднее арифметическое.
     *
     * @param numbers список входных вещественных чисел
     * @return строка с полной статистикой по введенным вещественным числам
     */
    public String getFullIntegersStatistics(List<BigInteger> numbers) {
        if (numbers.isEmpty()) return "";
        BigInteger minNumber = numbers.stream().min(BigInteger::compareTo).orElse(BigInteger.ZERO);
        BigInteger maxNumber = numbers.stream().max(BigInteger::compareTo).orElse(BigInteger.ZERO);
        BigInteger sumNumber = numbers.stream().reduce(BigInteger.ZERO, BigInteger::add);
        double avgNumber = sumNumber.divide(BigInteger.valueOf(numbers.size())).doubleValue();


        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32;1mСтатистика по целым числам:\n");
        sb.append("Количество чисел: ");
        sb.append(numbers.size());
        sb.append("\n");
        sb.append("Минимальное число: ");
        sb.append(minNumber);
        sb.append("\n");
        sb.append("Максимальное число: ");
        sb.append(maxNumber);
        sb.append("\n");
        sb.append("Сумма чисел: ");
        sb.append(sumNumber);
        sb.append("\n");
        sb.append("Среднее арифметическое: ");
        sb.append(avgNumber);
        sb.append("\n\n\u001B[0m");
        return sb.toString();
    }

    /**
     * Метод вызывается для получения краткой статистики по числовым данным.
     * Статистика включает только количество чисел.
     *
     * @param numbers список входных числовых данных
     * @return строка с краткой статистикой по введенным числовым данным
     */
    public String getShortNumberStatistics(List<? extends Number> numbers) {
        if (numbers.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32;1m");
        if (numbers.get(0) instanceof BigInteger) {
            sb.append("Статистика по целым числам:\n");
        } else if (numbers.get(0) instanceof Double) {
            sb.append("Статистика по вещественным числам:\n");
        }
        sb.append("Количество чисел: ");
        sb.append(numbers.size());
        sb.append("\n\n\u001B[0m");
        return sb.toString();
    }

    /**
     * Метод вызывается для получения полной статистики по строковым данным.
     * Статистика включает в себя количество строк, длину самой короткой и самой длинной строки.
     *
     * @param strings список входных строковых данных
     * @return строка с полной статистикой по введенным строковым данным
     */
    public String getFullStringStatistics(List<String> strings) {
        if (strings.isEmpty()) return "";
        int minLengthString = strings.stream().mapToInt(String::length).min().orElse(0);
        int maxLengthString = strings.stream().mapToInt(String::length).max().orElse(0);
        int countString = strings.size();

        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32;1m");
        sb.append("Статистика по строкам:\n");
        sb.append("Количество строк: ");
        sb.append(countString);
        sb.append("\n");
        sb.append("Длина самой короткой строки: ");
        sb.append(minLengthString);
        sb.append("\n");
        sb.append("Длина самой длинной строки: ");
        sb.append(maxLengthString);
        sb.append("\n\n\u001B[0m");
        return sb.toString();
    }

    /**
     * Метод вызывается для получения краткой статистики по строковым данным.
     * Статистика включает только количество строк.
     *
     * @param strings список входных строковых данных
     * @return строка с краткой статистикой по введенным строковым данным
     */
    public String getShortStringStatistics(List<String> strings) {
        if (strings.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32;1m");
        sb.append("Статистика по строкам:\n");
        sb.append("Количество строк: ");
        sb.append(strings.size());
        sb.append("\n\n\u001B[0m");
        return sb.toString();
    }
}