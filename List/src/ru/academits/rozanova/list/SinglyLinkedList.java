package ru.academits.rozanova.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным числом.");
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка.");
        }
    }

    public E get(int index) {
        checkIndex(index);

        ListItem<E> item = getItem(index);

        return item.getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> item = getItem(index);

        E oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public E remove(int index) {
        checkIndex(index);

        ListItem<E> previousItem = getItem(index - 1);

        E removedItemData = previousItem.getNext().getData();

        previousItem.setNext(previousItem.getNext().getNext());

        size--;

        return removedItemData;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        size++;
    }

    public void add(int index, E data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным числом.");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка.");
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<E> previousItem = getItem(index - 1);

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        size++;
    }

    public boolean remove(E data) {
        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        for (; item != null; previousItem = item, item = item.getNext()) {
            if (Objects.equals(item.getData(), data)) {
                break;
            }
        }

        if (item == null) {
            return false;
        }

        if (previousItem == null) {
            head = head.getNext();
        } else {
            previousItem.setNext(item.getNext());
        }

        size--;

        return true;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        E removedItemData = head.getData();

        head = head.getNext();

        size--;

        return removedItemData;
    }

    public void reverse() {
        if (head == null) {
            return;
        }

        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        while (item != null) {
            ListItem<E> nextItem = item.getNext();
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> getCopy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        if (size == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getData());

        for (ListItem<E> item = head.getNext(), listCopyItem = listCopy.head; item != null; item = item.getNext()) {
            listCopyItem.setNext(new ListItem<>(item.getData()));

            listCopyItem = listCopyItem.getNext();
        }

        listCopy.size = size;

        return listCopy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }
}
