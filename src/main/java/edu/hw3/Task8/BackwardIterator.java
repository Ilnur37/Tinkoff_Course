package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {
    private int index;
    private final Collection<T> collection;

    public BackwardIterator(Collection<T> collection) {
        this.index = collection.size();
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return index != 0;
    }

    @Override
    public T next() {
        --index;
        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}
