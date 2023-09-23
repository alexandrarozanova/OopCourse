package ru.academits.rozanova.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int CAPACITY = 10;
    private E[] items;
    private int size;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше нуля.");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList(E[] e) {
        size = e.length;

        items = Arrays.copyOf(e, e.length);
    }

    public ArrayList(Collection<E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        Object[] collectionItems = collection.toArray();

        //noinspection unchecked
        items = (E[]) Arrays.copyOf(collectionItems, collectionItems.length);

        size = collection.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E item : items) {
            if (Objects.equals(item, o)) {
                return true;
            }
        }

        return false;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = element;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], o)) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                items[size - 1] = null;

                --size;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        Object[] collectionItems = collection.toArray();

        for (int i = 0; i < collection.size(); i++) {
            if (indexOf(collectionItems[i]) == -1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        Object[] collectionItems = collection.toArray();

        int newSize = size + collection.size();
        ensureCapacity(newSize);

        for (int i = size, j = 0; i < newSize; i++, j++) {
            //noinspection unchecked
            items[i] = (E) collectionItems[j];

            ++size;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        if (index == size) {
            addAll(collection);

            return true;
        }

        checkIndex(index);

        Object[] collectionItems = collection.toArray();

        int newSize = size + collection.size();
        ensureCapacity(newSize);

        System.arraycopy(items, index, items, index + collection.size(), newSize);

        for (int i = index, j = 0; j < collection.size(); i++, j++) {
            //noinspection unchecked
            items[i] = (E) collectionItems[j];

            ++size;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        for (int i = 0; i < collection.size(); i++) {
            for (int j = 0; j < size; j++) {
                if (collection.contains(items[j])) {
                    remove(items[j]);
                }
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        for (int i = 0; i < collection.size(); i++) {
            for (int j = 0; j < size; j++) {
                if (!collection.contains(items[j])) {
                    remove(items[j]);
                }
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            remove(items[i]);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = get(index);

        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index <= size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

        ++size;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        if (index <= size) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        E removedElement = get(index);

        items[size - 1] = null;

        --size;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = items.length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше нуля.");
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (E item : items) {
            stringBuilder.append(item)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection DataFlowIssue
        return null;
    }
}