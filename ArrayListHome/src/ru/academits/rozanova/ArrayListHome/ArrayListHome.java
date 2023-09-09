package ru.academits.rozanova.ArrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome {
    public static List<String> readFileLines(String fileName) {
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }

        return lines;
    }

    public static void removeListEvenNumbers(List<Integer> numbers) {
        int i = 0;

        while (i < numbers.size()) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            } else {
                i++;
            }
        }
    }

    public static List<Integer> removeListDuplicateNumbers(List<Integer> numbers) {
        List<Integer> uniqueNumbersList = new ArrayList<>();

        int i = 0;

        while (i < numbers.size()) {
            if (!(uniqueNumbersList.contains(numbers.get(i)))) {
                uniqueNumbersList.add(numbers.get(i));
            }

            i++;
        }

        return uniqueNumbersList;
    }

    public static void main(String[] args) {
        String file = "file.txt";

        System.out.println(readFileLines(file));

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 10));

        removeListEvenNumbers(numbers);
        System.out.println(numbers);

        System.out.println(removeListDuplicateNumbers(numbers));
    }
}