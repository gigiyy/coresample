package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BalancedBracesTests {

    @Test
    public void test() {
        assertThat(isBalanced("{{[([()])]}}")).isEqualTo("YES");
    }

    public static String isBalanced(String s) {
        // Write your code here
        Deque<Character> stack = new ArrayDeque<>();
        // with java9, Map.of() can be used instead
        Map<Character, Character> map = Stream.of(new char[][]{
                {'[', ']'},
                {'(', ')'},
                {'{', '}'}
        }).collect(Collectors.toMap(e -> e[0], e -> e[1]));
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                if (ch != map.get(stack.pop())) {
                    return "NO";
                }
            }
        }
        return stack.isEmpty() ? "YES" : "NO";

    }
}
