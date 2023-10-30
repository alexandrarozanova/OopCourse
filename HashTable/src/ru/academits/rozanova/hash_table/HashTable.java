package ru.academits.rozanova.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Недопустимое значение для размера хэш-таблицы: " + capacity + ", размер хэш-таблицы должен быть > 0.");
        }

        //noinspection unchecked
        lists = new ArrayList[capacity];
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
    public boolean contains(Object object) {
        int index = getIndex(object);

        return lists[index] != null && lists[index].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private final int initialModCount;
        private int currentListIndex;
        private int currentItemIndex = -1;
        private int visitedItemsCount;

        private HashTableIterator() {
            initialModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец хэш-таблицы таблицы, невозможно получить следующий элемент.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена.");
            }

            currentItemIndex++;
            visitedItemsCount++;

            while (lists[currentListIndex] == null || lists[currentListIndex].isEmpty()) {
                currentListIndex++;
            }

            E item = lists[currentListIndex].get(currentItemIndex);

            if (currentItemIndex == lists[currentListIndex].size() - 1) {
                currentListIndex++;
                currentItemIndex = -1;
            }

            return item;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E item : list) {
                    array[i] = item;

                    i++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(object)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        for (Object item : collection) {
            if (!contains(item)) {
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

        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        if (collection.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (Object item : collection) {
            while (remove(item)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        int newSize = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.retainAll(collection);
                newSize += list.size();
            }
        }

        if (newSize != size) {
            size = newSize;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        size = 0;
        modCount++;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ArrayList<E> list : lists) {
            stringBuilder.append(list)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
