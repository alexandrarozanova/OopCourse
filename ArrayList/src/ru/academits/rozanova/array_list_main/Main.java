package ru.academits.rozanova.array_list_main;

import ru.academits.rozanova.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers1 = new ArrayList<>();

        numbers1.add(1);
        numbers1.add(5);
        numbers1.add(3);
        numbers1.add(5);

        System.out.println("Первый список: " + numbers1 + ", размер списка: " + numbers1.size());

        ArrayList<Integer> numbers2 = new ArrayList<>(numbers1);
        System.out.println("Второй список: " + numbers2 + ", размер списка: " + numbers2.size());

        ArrayList<Integer> numbers3 = new ArrayList<>(15);

        numbers3.addAll(numbers1);
        numbers3.addAll(2, numbers2);

        System.out.println("Третий список: " + numbers3 + ", размер списка: " + numbers3.size());

        Integer[] numbersArray = {10, 11, 12, 13, 14, 15};

        ArrayList<Integer> numbers4 = new ArrayList<>(numbersArray);
        System.out.println("Четвертый список: " + numbers4 + ", размер списка: " + numbers4.size());

        if (numbers1.contains(7)) {
            System.out.println("Число 7 находится в первом списке.");
        } else {
            System.out.println("Число 7 не находится в первом списке.");
        }

        numbers4.remove(Integer.valueOf(10));
        System.out.println("Удаление числа 10 из четвертого списка: " + numbers4 + ", размер списка: " + numbers4.size());

        if (numbers3.containsAll(numbers2)) {
            System.out.println("Все числа второго списка находятся в третьем.");
        } else {
            System.out.println("Не все числа второго списка находятся в третьем.");
        }

        numbers3.removeAll(numbers1);
        System.out.println("Третий список после удаления из него всех чисел первого списка: " + numbers3 + ", размер списка: " + numbers3.size());

        numbers3.addAll(numbers1);
        numbers3.addAll(numbers4);

        System.out.println("Третий список после добавления в него чисел первого и четвертого списка: " + numbers3 + ", размер списка: " + numbers3.size());

        numbers3.retainAll(numbers4);
        System.out.println("Третий список после удаления из него чисел, которых нет в четвертом списке: " + numbers3 + ", размер списка: " + numbers3.size());

        numbers2.clear();
        System.out.println("Второй список после удаления из него всех чисел: " + numbers2);

        numbers2.addAll(numbers1);
        System.out.println("Второй список после добавления в него чисел первого списка: " + numbers2);

        System.out.println("Второй элемент второго списка: " + numbers2.get(1));

        numbers2.set(1, 7);
        System.out.println("Второй список после замены второго элемента: " + numbers2);

        numbers2.add(2, 8);
        System.out.println("Второй список после добавления элемента по третьему индексу: " + numbers2 + ", размер списка: " + numbers2.size());

        numbers2.remove(3);
        System.out.println("Второй список после удаления элемента по четвертому индексу: " + numbers2 + ", размер списка: " + numbers2.size());

        System.out.println("Индекс числа 7 второго списка: " + numbers2.indexOf(7));

        System.out.println("Последний индекс числа 5 первого списка: " + numbers1.lastIndexOf(5));

        numbers2.trimToSize();
        System.out.println("Второй список после урезания внутреннего массива до размера списка: " + numbers2);
    }
}