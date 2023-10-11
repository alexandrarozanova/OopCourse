package ru.academits.rozanova.hash_table_main;

import ru.academits.rozanova.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>(4);

        hashTable1.add(9);
        hashTable1.add(10);
        hashTable1.add(30);
        hashTable1.add(4);

        System.out.println("Хэш-таблица 1: " + hashTable1 + ", размер таблицы: " + hashTable1.size());

        HashTable<Integer> hashTable2 = new HashTable<>();

        hashTable2.addAll(hashTable1);

        System.out.println("Хэш-таблица 2: " + hashTable2 + ", размер таблицы: " + hashTable2.size());

        if (hashTable1.contains(5)) {
            System.out.println("Хэш-таблица 1 содержит число 5.");
        } else {
            System.out.println("Хэш-таблица 1 не содержит число 5.");
        }

        Integer[] array = new Integer[4];
        System.out.println("Хэш-таблица 1 в виде массива: " + Arrays.toString(hashTable1.toArray(array)));

        System.out.println("Хэш-таблица 2 в виде массива: " + Arrays.toString(hashTable2.toArray()));

        hashTable2.remove(30);
        System.out.println("Удаление числа 30 из второй хэш-таблицы: " + hashTable2 + ", размер таблицы: " + hashTable2.size());

        if (hashTable2.containsAll(hashTable1)) {
            System.out.println("Все числа из хэш-таблицы 1 присутствуют в хэш-таблице 2.");
        } else {
            System.out.println("Не все числа из хэш-таблицы 1 присутствуют в хэш-таблице 2.");
        }

        hashTable1.removeAll(hashTable2);
        System.out.println("Хэш-таблица 1 после удаления всех чисел, присутствующих в хэш-таблице 2: " + hashTable1 + ", размер таблицы: " + hashTable1.size());

        hashTable2.retainAll(hashTable1);
        System.out.println("Хэш-таблица 2 после удаления всех чисел, не присутствующих в хэш-таблице 1: " + hashTable2 + ", размер таблицы: " + hashTable2.size());

        hashTable1.clear();
        System.out.println("Хэш-таблица 1 после удаления всех чисел: " + hashTable1);
    }
}