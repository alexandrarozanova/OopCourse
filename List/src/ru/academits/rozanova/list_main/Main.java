package ru.academits.rozanova.list_main;

import ru.academits.rozanova.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(1);
        list.addFirst(0);
        list.addFirst(7);
        list.addFirst(null);
        list.addFirst(9);

        System.out.println("Созданный односвязный список: " + list + ", размер списка: " + list.getSize());

        System.out.println("Значение первого элемента списка: " + list.getFirst());

        System.out.println("Значение третьего элемента списка: " + list.get(2));

        System.out.println("Изменение четвертого элемента списка: " + list.set(3, 3) + ", новое значение четвертого элемента списка: " + list.get(3));

        System.out.println("Удаление пятого элемента списка: " + list.remove(4));

        System.out.println("Список после изменений: " + list + ", размер списка: " + list.getSize());

        list.addFirst(10);
        System.out.println("Добавление элемента 10 в начало списка: " + list);

        list.add(5, 4);
        System.out.println("Добавление элемента 4 по индексу 5: " + list);

        System.out.println("Удаление узла 7 из списка: " + list.remove(Integer.valueOf(7)));

        System.out.println("Список после изменений: " + list + ", размер списка: " + list.getSize());

        System.out.println("Удаление первого элемента: " + list.removeFirst());

        System.out.println("Список после изменений: " + list + ", размер списка: " + list.getSize());

        list.reverse();
        System.out.println("Разворот списка: " + list);

        SinglyLinkedList<Integer> listCopy = list.getCopy();
        System.out.println("Копия списка: " + listCopy);
    }
}