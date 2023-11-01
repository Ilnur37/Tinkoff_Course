package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private int index;
    private final T[] elements;

    public BackwardIterator(Collection<T> collection) {
        this.elements = (T[]) collection.toArray();
        this.index = elements.length - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T item = elements[index];
        --index;
        return item;
    }
}
