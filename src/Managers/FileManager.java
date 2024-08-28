package Managers;

import Utility.Console;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Предназначен для взаимодействия с файлами.
 * Обеспечивает чтение и запись данных из/в файлы, а также вывод сообщений и ошибок в консоль.
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class FileManager {

    //Консоль для вывода сообщений и чтения пользовательского ввода
    private Console console;

    /**
     * Создает менеджер, управляющий файлами.
     *
     * @param console консоль
     */
    public FileManager(Console console) {
        this.console = console;
    }

    /**
     * Считывает содержимое переданных файлов.
     * Если файл не найден или нет прав доступа к файлу, выводит сообщение об ошибке.
     *
     * @param files список строк названий файлов
     * @return список строк, считанных из файлов
     */
    public List<String> readFiles(List<String> files) {
        List<BufferedReader> readers = new ArrayList<>();
        List<String> data = new ArrayList<>();
        int errors = 0;
        try {
            for (String fileName : files) {
                try {
                    if (new File(fileName).exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(fileName));
                        readers.add(reader);
                    } else {
                        console.printError("файл " + fileName + " не найден.");
                        errors += 1;
                    }

                } catch (IOException e) {
                    errors += 1;
                    console.printError("недостаточно прав для чтения файла " + fileName + ".");
                }
            }
            if (errors == files.size()) {
                console.printError("нет доступных файлов для чтения, дальнейшее выполнение невозможно.");
                System.exit(1);
            }

            boolean hasMoreLines;
            do {
                hasMoreLines = false;
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        data.add(line);
                        hasMoreLines = true;
                    }
                }
            } while (hasMoreLines);
            if (errors < files.size() & data.size() == 0) {
                console.printError("доступные для чтения файлы пусты, дальнейшее выполнение невозможно.");
                System.exit(1);
            }
        } catch (IOException e) {
            console.printError("возникла ошибка чтения.");

        } finally {
            // Закрываем все открытые файлы
            for (BufferedReader reader : readers) {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    /**
     * Переводит список в строку, объединяя элементы с символом новой строки.
     *
     * @param collection список входных данных
     * @return строка, содержащая элементы списка, разделенные новой строкой
     */
    private String collectionToString(List<?> collection) {
        Iterator<?> iterator = collection.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Записывает данные в файл.
     * Если файл не найден или нет прав доступа, выводит сообщение об ошибке.
     *
     * @param data     входные данные
     * @param filepath путь к файлу
     * @param prefix   префикс для названия файла
     * @param fileName название файла
     * @param append   флаг дописывания в конец файла
     */
    public void write(List<?> data, String filepath, String prefix, String fileName, boolean append) {
        if (data.size() > 0) {
            FileWriter writer = null;
            String file = !filepath.isEmpty() ? filepath + "/" : "";
            file += prefix + fileName;
            String info = collectionToString(data);
            try {
                writer = new FileWriter(file, append);
                try {
                    writer.write(info);
                    writer.flush();
                } catch (IOException e) {
                    console.printError("Неожиданная ошибка сохранения в файл " + fileName + ".");
                }
            } catch (IOException e) {
                console.printError("Не удалось записать в " + file + ".");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        console.printError("Ошибка закрытия файла.");
                    }
                }
            }
        }
    }
}
