package ru.academits.rozanova.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Некорректное значение вместимости списка для его создания: " + capacity + ", значение должно начинаться с 0.");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList(E[] itemsArray) {
        if (itemsArray == null) {
            throw new NullPointerException("Массив не может быть null.");
        }

        size = itemsArray.length;

        items = Arrays.copyOf(itemsArray, itemsArray.length);
    }

    public ArrayList(Collection<E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        //noinspection unchecked
        items = (E[]) new Object[collection.size()];

        int i = 0;

        for (E item : collection) {
            items[i] = item;

            i++;
        }

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
        return indexOf(o) >= 0;
    }

    private class ArrayListIterator implements Iterator<E> {
        private final int initialModCount;
        private int currentIndex = -1;

        private ArrayListIterator() {
            initialModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец списка, невозможно получить следующий элемент.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }

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
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) == -1) {
            return false;
        }

        remove(indexOf(o));

        return true;
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
        addAll(size, collection);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Указанный индекс " + index + " выходит за диапазон допустимых значений: [0; " + size + "].");
        }

        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        int i = index;

        for (E item : collection) {
            items[i] = item;

            i++;
        }

        size += collection.size();
        ++modCount;

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

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = get(index);

        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Указанный индекс " + index + " выходит за диапазон допустимых значений: [0; " + size + "].");
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index < size - 1) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;

        ++size;
        ++modCount;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        E removedItem = items[index];

        items[size - 1] = null;

        --size;
        ++modCount;

        return removedItem;
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
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);

            return;
        }

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Указанный индекс " + index + " выходит за диапазон допустимых значений: [0; " + (size - 1) + "].");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i])
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