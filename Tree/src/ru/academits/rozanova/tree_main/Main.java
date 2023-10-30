package ru.academits.rozanova.tree_main;

import ru.academits.rozanova.tree.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        binaryTree.add(8);
        binaryTree.add(3);
        binaryTree.add(10);
        binaryTree.add(1);
        binaryTree.add(6);
        binaryTree.add(9);
        binaryTree.add(14);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(20);
        binaryTree.add(13);
        binaryTree.add(11);
        binaryTree.add(12);

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        System.out.println("Обход дерева в ширину:");
        binaryTree.traverseInWidth(consumer);

        System.out.println();

        System.out.println("Размер дерева: " + binaryTree.getSize());

        System.out.println("Удаление листа \"1\":");
        if (binaryTree.remove(1)) {
            binaryTree.traverseInWidth(consumer);

            System.out.println();
        } else {
            System.out.println("Удаление не выполнено, лист отсутствует в дереве.");
        }

        System.out.println("Удаление узла с одним ребенком \"11\":");
        if (binaryTree.remove(11)) {
            binaryTree.traverseInWidth(consumer);

            System.out.println();
        } else {
            System.out.println("Удаление не выполнено, узел отсутствует в дереве.");
        }

        System.out.println("Удаление узла с двумя детьми \"10\":");
        if (binaryTree.remove(10)) {
            binaryTree.traverseInWidth(consumer);

            System.out.println();
        } else {
            System.out.println("Удаление не выполнено, узел отсутствует в дереве.");
        }

        if (binaryTree.contains(5)) {
            System.out.println("Узел \"5\" присутствует в дереве.");
        } else {
            System.out.println("Узел \"5\" не присутствует в дереве.");
        }

        System.out.println("Обход дерева в глубину:");
        binaryTree.traverseInDepth(consumer);

        System.out.println();

        System.out.println("Рекурсивный обход дерева в глубину:");
        binaryTree.traverseInDepthRecursive(consumer);
    }
}