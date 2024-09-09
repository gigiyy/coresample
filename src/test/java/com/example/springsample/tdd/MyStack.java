package com.example.springsample.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyStack<T> {
    private int size = 0;
    private final List<T> elements = new ArrayList<>();

    public int size() {
        return size;
    }

    public void push(T e) {
        elements.add(size++, e);
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Empty Stack");
        }
        return elements.get(--size);
    }
}
