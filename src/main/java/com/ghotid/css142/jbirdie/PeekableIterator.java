package com.ghotid.css142.jbirdie;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator where you can look at the next element without consuming it.
 */

// The class is general-purpose enough that weaker access isn't necessary for
// any kind of security.
@SuppressWarnings("WeakerAccess")
public class PeekableIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private T curr;

    public PeekableIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        pullElement();
    }

    /**
     * Update the current element.
     */
    private void pullElement() {
        if (iterator.hasNext()) {
            curr = iterator.next();
        } else {
            curr = null;
        }
    }

    /**
     * Return whether there's an element left.
     */
    @Override
    public boolean hasNext() {
        return curr != null;
    }

    /**
     * Consume the next element and return it.
     */
    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();

        T result = curr;
        pullElement();

        return result;
    }

    /**
     * Return the next element without consuming it.
     */
    public T peek() {
        if (!hasNext())
            throw new NoSuchElementException();

        return curr;
    }
}
