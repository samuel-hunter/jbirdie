package com.ghotid.css142.jbirdie;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * An iterator where you can look at the next element without consuming it.
 */

// The class is general-purpose enough that weaker access isn't necessary for
// any kind of security.
@SuppressWarnings("WeakerAccess")
public class PeekableIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private T curr;

    private boolean tokenPulled = false;

    public PeekableIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        pullElement();
    }

    /**
     * Pull the current element from the element to peek at.
     */
    private void pullElement() {
        if (iterator.hasNext()) {
            curr = Objects.requireNonNull(iterator.next());
            tokenPulled = true;
        }
    }

    /**
     * Return whether there's an element left.
     */
    @Override
    public boolean hasNext() {
        return tokenPulled || iterator.hasNext();
    }

    /**
     * Consume the next element and return it.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (tokenPulled) {
            tokenPulled = false;
            return curr;
        } else {
            return iterator.next();
        }
    }

    /**
     * Return the next element without consuming it.
     */
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (!tokenPulled) {
            pullElement();
        }

        return curr;
    }
}
