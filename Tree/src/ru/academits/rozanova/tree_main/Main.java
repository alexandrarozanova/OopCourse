package ru.academits.rozanova.tree_main;

import ru.academits.rozanova.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(9);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(20);
        tree.add(13);
        tree.add(11);
        tree.add(12);

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        System.out.println("Обход дерева в ширину:");
        tree.traverseInWidth(consumer);

        System.out.println();

        System.out.println("Размер дерева: " + tree.getSize());

        System.out.println("Удаление листа \"1\":");
        tree.remove(1);
        tree.traverseInWidth(consumer);

        System.out.println();

        System.out.println("Удаление узла с одним ребенком \"11\":");
        tree.remove(11);
        tree.traverseInWidth(consumer);

        System.out.println();

        System.out.println("Удаление узла с двумя детьми \"10\":");
        tree.remove(10);
        tree.traverseInWidth(consumer);

        System.out.println();

        if (tree.getItem(5)) {
            System.out.println("Узел \"5\" присутствует в дереве.");
        } else {
            System.out.println("Узел \"5\" не присутствует в дереве.");
        }

        System.out.println("Обход дерева в глубину:");
        tree.traverseInDepth(consumer);

        System.out.println();

        System.out.println("Рекурсивный обход дерева в глубину:");
        tree.traverseInDepthRecursive(consumer);
    }
}