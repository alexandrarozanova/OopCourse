package ru.academits.rozanova.arrayListHome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListHome {
    public static List<String> readFileLines(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void removeEvenNumbers(List<Integer> numbers) {
        int i = 0;

        while (i < numbers.size()) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            } else {
                i++;
            }
        }
    }

    public static List<Integer> getUniqueList(List<Integer> numbers) {
        List<Integer> uniqueNumbersList = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!uniqueNumbersList.contains(number)) {
                uniqueNumbersList.add(number);
            }
        }

        return uniqueNumbersList;
    }

    public static void main(String[] args) {
        try {
            String file = "file.txt";
            System.out.println("Файл " + file + " содержит следующие данные: " + readFileLines(file));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
        }

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Переданный список: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("Список, после удаления из него четных чисел: " + numbers);

        System.out.println("Список, содержащий уникальные элементы: " + getUniqueList(numbers));
    }
}
