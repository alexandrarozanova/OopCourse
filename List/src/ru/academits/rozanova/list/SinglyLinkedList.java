package ru.academits.rozanova.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int getSize() {
        return size;
    }

    public E getFirstItemData() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    private void checkIndexValidity(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным числом.");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка.");
        }
    }

    public E getData(int index) {
        checkIndexValidity(index);

        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item.getData();
    }

    public E setData(int index, E data) {
        checkIndexValidity(index);

        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        E previousData = item.getData();

        item.setData(data);

        return previousData;
    }

    public E remove(int index) {
        checkIndexValidity(index);

        ListItem<E> item = head;

        for (int i = 0; i < index - 1; i++) {
            item = item.getNext();
        }

        ListItem<E> previousItem = item;

        E removedItemData = item.getNext().getData();

        previousItem.setNext(item.getNext().getNext());

        size--;

        return removedItemData;
    }

    public void addItemToStart(E data) {
        head = new ListItem<>(data, head);

        size++;
    }

    public void addItem(int index, E data) {
        checkIndexValidity(index);

        ListItem<E> item = head;

        for (int i = 0; i < index - 1; i++) {
            item = item.getNext();
        }

        ListItem<E> previousItem = item;

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        size++;
    }

    public boolean IsRemoved(E data) {
        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        for (; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData() == null) {
                if (data == null) {
                    break;
                }

                continue;
            }

            if (item.getData().equals(data)) {
                break;
            }
        }

        if (item == null) {
            System.out.println("Элемент не найден.");
        } else {
            if (previousItem == null) {
                head = head.getNext();
            } else {
                previousItem.setNext(item.getNext());
            }

            size--;
        }

        return true;
    }

    public E removeFirstItem() {
        E removedItemData = head.getData();

        head = head.getNext();

        size--;

        return removedItemData;
    }

    public void reverse() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        ListItem<E> item = head.getNext();

        head.setNext(null);

        while (item != null) {
            ListItem<E> nextItem = item.getNext();
            item.setNext(head);
            head = item;
            item = nextItem;
        }
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
}
