package com.example.springsample.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StackTests {

    private MyStack<Integer> subject;

    @BeforeEach
    public void setUp() {
        subject = new MyStack<>();
    }

    // empty stack has size 0
    @Test
    public void testEmptyStackSizeEq0() {
        assertThat(subject.size()).isEqualTo(0);
    }

    // push element will increase the size
    @Test
    public void testPushWillIncreaseSizeBy1() {
        int size = subject.size();
        subject.push(2);
        assertThat(subject.size()).isEqualTo(size + 1);
    }

    // pop will decrease the size
    // popping empty stack will throw NoSuchElement exception
    @Test
    public void testPopWillDecreaseSizeBy1() {
        assertThatThrownBy(() -> {
            subject.pop();
        }).isInstanceOf(NoSuchElementException.class)
                .hasMessage("Empty Stack");
        subject.push(2);
        int size = subject.size();
        subject.pop();
        assertThat(subject.size()).isEqualTo(size - 1);
    }

    // pushed multiple element and will pop back elements in reversed order
    @Test
    public void testPopMultipleElementsInReversedOrder() {
        Random rnd = new Random();
        List<Integer> elements = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            int e = rnd.nextInt(100);
            elements.add(e);
            subject.push(e);
        });
        Collections.reverse(elements);
        for (int e : elements) {
            assertThat(subject.pop()).isEqualTo(e);
        }
    }
}
