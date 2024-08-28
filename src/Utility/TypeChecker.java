package Utility;

/**
 * Предназначен для определения типа данных, содержащегося в строке.
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class TypeChecker {
    private static TypeChecker instance;

    private TypeChecker() {
    }

    public static TypeChecker getInstance() {
        if (instance == null) {
            instance = new TypeChecker();
        }
        return instance;
    }

    /**
     * Определяет тип введенных данных
     *
     * @param input входные данные
     * @return тип данных, содержащийся в строке
     */
    public String determineType(String input) {
        if (isInteger(input)) {
            return "Integer";
        } else if (isFloat(input)) {
            return "Float";
        } else {
            return "String";
        }
    }

    /**
     * Проверяет, является ли введенная строка целым числом.
     *
     * @param input входные данные
     * @return true, если строка является целым числом, иначе false
     */
    private boolean isInteger(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли введенная строка числом с плавающей точкой.
     *
     * @param input входные данные
     * @return true, если строка является числом с плавающей точкой, иначе false
     */
    private boolean isFloat(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}