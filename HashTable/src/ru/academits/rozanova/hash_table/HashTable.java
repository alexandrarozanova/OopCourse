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
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимое значение для размера таблицы: " + capacity + ", размер таблицы должен начинаться с 0.");
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
    public boolean contains(Object item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(item);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private final int initialModCount;
        private int currentListIndex = 0;
        private int currentItemIndex = -1;

        private HashTableIterator() {
            initialModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            while (currentListIndex < lists.length && lists[currentListIndex] == null) {
                currentListIndex++;
            }

            return currentListIndex < lists.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец таблицы, невозможно получить следующий элемент.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Таблица была изменена.");
            }

            currentItemIndex++;
            E item = lists[currentListIndex].get(currentItemIndex);

            if (currentItemIndex == lists[currentListIndex].size() - 1 && hasNext()) {
                currentListIndex++;
                currentItemIndex = -1;
            }

            return item;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int index = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E item : list) {
                    array[index] = item;

                    index++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < lists.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(lists, lists.length);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > lists.length) {
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
    public boolean remove(Object item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            return false;
        }

        lists[index].remove(item);

        size--;
        modCount++;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        if (collection.isEmpty()) {
            return false;
        }

        for (Object list : collection) {
            if (!contains(list)) {
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

        for (Object list : collection) {
            remove(list);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                if (list.retainAll(collection)) {
                    size--;
                }
            }
        }

        modCount++;

        return true;
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

    private int getIndex(Object item) {
        if (item == null) {
            return 0;
        }

        return Math.abs(item.hashCode() % lists.length);
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
