package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConsList implements List<LispObject> {
    private final LispObject list;

    public ConsList(LispObject list) {
        this.list = list;
    }

    public void assertSizeEquals(int expected) {
        int actual = size();
        if (expected != actual)
            throw new ArgumentNumberException(actual, expected);
    }

    public int assertSizeAtLeast(int minimum) {
        int actual = size();
        if (minimum > actual)
            throw new ArgumentNumberException(actual, minimum + "+");

        return actual;
    }

    public int assertSizeWithin(int minimum, int maximum) {
        int actual = size();
        if (minimum > actual || maximum < actual) {
            throw new ArgumentNumberException(actual,
                    minimum + ".." + maximum);
        }

        return actual;
    }

    @Override
    public int size() {
        int size = 0;

        LispObject node = list;
        while (!(node instanceof NilObject)) {
            node = node.getCdr();
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return list instanceof NilObject;
    }

    @Override
    public boolean contains(Object o) {
        // Quick shortcut for non-lisp objects.
        if (!(o instanceof LispObject))
            return false;

        for (LispObject lo : this) {
            if (lo.equals(o))
                return true;
        }

        return false;
    }


    @Override
    public Iterator<LispObject> iterator() {
        return new Iterator<LispObject>() {
            private LispObject tail = list;

            @Override
            public boolean hasNext() {
                return !(tail instanceof NilObject);
            }

            @Override
            public LispObject next() {
                LispObject next = tail.getCar();
                tail = tail.getCdr();
                return next;
            }
        };
    }

    @Override
    public Object[] toArray() {
        LispObject[] result = new LispObject[size()];
        int index = 0;
        for (LispObject obj : this) {
            result[index++] = obj;
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(LispObject lispObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o))
                return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends LispObject> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int i, Collection<? extends LispObject> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param i index of list.
     * @return the requested node, or 'nil' if the list was empty.
     */
    @Override
    public LispObject get(int i) {
        if (i < 0)
            throw new IndexOutOfBoundsException();

        LispObject node = list;
        while (i > 0) {
            node = node.getCdr();

            // Optimization: return the result already if it is nil.
            if (node instanceof NilObject)
                return node;

            i--;
        }

        return node.getCar();
    }

    @Override
    public LispObject set(int i, LispObject lispObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int i, LispObject lispObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        // Quick shortcut for non-lisp objects.
        if (!(o instanceof LispObject))
            return -1;

        int index = 0;

        LispObject node = list;
        while (!(node instanceof NilObject)) {
            if (node.getCar().equals(o))
                return index;

            node = node.getCdr();
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // Quick shortcut for non-lisp objects.
        if (!(o instanceof LispObject))
            return -1;

        int currentIndex = 0;
        int selectedIndex = -1;

        LispObject node = list;

        while (!(node instanceof NilObject)) {
            if (node.getCar().equals(o))
                selectedIndex = currentIndex;

            node = node.getCdr();
            currentIndex++;
        }

        return selectedIndex;
    }

    @Override
    public ListIterator<LispObject> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<LispObject> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<LispObject> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
