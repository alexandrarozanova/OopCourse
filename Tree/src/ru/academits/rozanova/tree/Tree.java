package ru.academits.rozanova.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class Tree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public Tree() {
        root = null;
    }

    public int getSize() {
        return size;
    }

    private int compare(E node1, E node2) {
        return node1.compareTo(node2);
    }

    public void add(E node) {
        if (node == null) {
            throw new NullPointerException("Узел не может быть null.");
        }

        if (root == null) {
            root = new TreeNode<>(node);
            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparingResult = compare(node, currentNode.getData());

            if (comparingResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(node));

                    size++;

                    return;
                }

                currentNode = currentNode.getLeft();
            }

            if (comparingResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(node));

                    size++;

                    return;
                }

                currentNode = currentNode.getRight();
            }

            if (comparingResult == 0) {
                return;
            }
        }
    }

    public boolean getItem(E node) {
        if (size == 0) {
            return false;
        }

        if (node == null) {
            throw new NullPointerException("Узел не может быть null.");
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparingResult = compare(node, currentNode.getData());

            if (comparingResult == 0) {
                return true;
            }

            if (comparingResult < 0) {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }

            if (comparingResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public void remove(E node) {
        if (node == null) {
            throw new NullPointerException("Узел не может быть null.");
        }

        if (size == 0) {
            return;
        }

        TreeNode<E> currentNodeParent = null;
        TreeNode<E> currentNode = root;
        boolean isLeftChild = false;

        while (true) {
            int comparingResult = compare(node, currentNode.getData());

            if (comparingResult == 0) {
                break;
            }

            currentNodeParent = currentNode;

            if (comparingResult < 0) {
                currentNode = currentNode.getLeft();

                isLeftChild = true;
            }

            if (comparingResult > 0) {
                currentNode = currentNode.getRight();

                isLeftChild = false;
            }

            if (currentNode == null) {
                return;
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

            return;
        }

        if ((currentNode.getLeft() == null || currentNode.getRight() == null)) {
            TreeNode<E> nextNode;

            if (currentNode.getLeft() != null) {
                nextNode = currentNode.getLeft();
            } else {
                nextNode = currentNode.getRight();
            }

            if (currentNodeParent == null) {
                root = nextNode;
            } else if (isLeftChild) {
                currentNodeParent.setLeft(nextNode);
            } else {
                currentNodeParent.setRight(nextNode);
            }

            size--;

            return;
        }

        TreeNode<E> minLeftNode = currentNode.getRight();
        TreeNode<E> minLeftNodeParent = null;

        while (minLeftNode.getLeft() != null) {
            minLeftNodeParent = minLeftNode;
            minLeftNode = minLeftNode.getLeft();
        }

        if (minLeftNodeParent != null) {
            if (minLeftNode.getRight() != null) {
                minLeftNodeParent.setLeft(minLeftNode.getRight());
            } else {
                minLeftNodeParent.setLeft(null);
            }
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
    }

    public void traverseInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new ArrayDeque<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();

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

        Stack<TreeNode<E>> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }

    private void visit(TreeNode<E> node, Consumer<E> consumer) {
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
