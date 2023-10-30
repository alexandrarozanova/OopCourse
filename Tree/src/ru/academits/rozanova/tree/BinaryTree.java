package ru.academits.rozanova.tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private final Comparator<E> comparator;
    private BinaryTreeNode<E> root;
    private int size;

    public BinaryTree() {
        comparator = null;
    }

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) data1).compareTo(data2);
    }

    public void add(E data) {
        if (root == null) {
            root = new BinaryTreeNode<>(data);
            size++;

            return;
        }

        BinaryTreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparingResult = compare(data, currentNode.getData());

            if (comparingResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new BinaryTreeNode<>(data));
                    size++;

                    return;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new BinaryTreeNode<>(data));
                    size++;

                    return;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean contains(E data) {
        if (size == 0) {
            return false;
        }

        BinaryTreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparingResult = compare(data, currentNode.getData());

            if (comparingResult == 0) {
                return true;
            }

            if (comparingResult < 0) {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public boolean remove(E data) {
        if (size == 0) {
            return false;
        }

        BinaryTreeNode<E> currentNodeParent = null;
        BinaryTreeNode<E> currentNode = root;
        boolean isLeftChild = false;

        while (true) {
            int comparingResult = compare(data, currentNode.getData());

            if (comparingResult == 0) {
                break;
            }

            currentNodeParent = currentNode;

            if (comparingResult < 0) {
                currentNode = currentNode.getLeft();

                isLeftChild = true;
            } else {
                currentNode = currentNode.getRight();

                isLeftChild = false;
            }

            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.getRight() == null && currentNode.getLeft() == null) {
            if (currentNodeParent == null) {
                root = null;
            } else if (isLeftChild) {
                currentNodeParent.setLeft(null);
            } else {
                currentNodeParent.setRight(null);
            }

            size--;

            return true;
        }

        if ((currentNode.getLeft() == null || currentNode.getRight() == null)) {
            BinaryTreeNode<E> nextNode = (currentNode.getLeft() != null) ? currentNode.getLeft() : currentNode.getRight();

            if (currentNodeParent == null) {
                root = nextNode;
            } else if (isLeftChild) {
                currentNodeParent.setLeft(nextNode);
            } else {
                currentNodeParent.setRight(nextNode);
            }

            size--;

            return true;
        }

        BinaryTreeNode<E> minLeftNode = currentNode.getRight();
        BinaryTreeNode<E> minLeftNodeParent = null;

        while (minLeftNode.getLeft() != null) {
            minLeftNodeParent = minLeftNode;
            minLeftNode = minLeftNode.getLeft();
        }

        if (minLeftNodeParent != null) {
            minLeftNodeParent.setLeft(minLeftNode.getRight());
        } else {
            currentNode.setRight(minLeftNode.getRight());
        }

        minLeftNode.setRight(currentNode.getRight());
        minLeftNode.setLeft(currentNode.getLeft());

        if (currentNodeParent == null) {
            root = minLeftNode;
        } else if (isLeftChild) {
            currentNodeParent.setLeft(minLeftNode);
        } else {
            currentNodeParent.setRight(minLeftNode);
        }

        size--;

        return true;
    }

    public void traverseInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new ArrayDeque<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.poll();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<BinaryTreeNode<E>> stack = new ArrayDeque<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<E> node = stack.removeLast();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }
    }

    private void visit(BinaryTreeNode<E> node, Consumer<E> consumer) {
        if (node != null) {
            consumer.accept(node.getData());

            visit(node.getLeft(), consumer);
            visit(node.getRight(), consumer);
        }
    }

    public void traverseInDepthRecursive(Consumer<E> consumer) {
        visit(root, consumer);
    }
}
