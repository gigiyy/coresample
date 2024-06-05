package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MixingCookiesTests {

    @Test
    public void test() {
        assertThat(cookies(9, List.of(2, 7, 3, 6, 4, 6))).isEqualTo(4);
        assertThat(cookies(11, List.of(1, 1, 1))).isEqualTo(-1);
        assertThat(cookies(1000, List.of(52, 96, 13, 37))).isEqualTo(-1);
    }

    public int cookies(int k, List<Integer> A) {
        // Write your code here
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int a : A) {
            map.compute(a, (key, val) -> val == null ? 1 : val + 1);
        }
        int steps = 0;
        while (!map.isEmpty()) {
            int first = removeFirst(map);
            if (first >= k) return steps;
            if (map.isEmpty()) return -1;
            int second = removeFirst(map);
            map.compute(first + 2 * second, (key, val) -> val == null ? 1 : val + 1);
            steps++;
        }
        return -1;
    }

    // map always has entries
    int removeFirst(TreeMap<Integer, Integer> map) {
        while (true) {
            Map.Entry<Integer, Integer> e = map.firstEntry();
            int key = e.getKey();
            int value = e.getValue();
            if (value == 0) {
                map.remove(key);
            } else if (value == 1) {
                map.remove(key);
                return key;
            } else {
                map.put(key, value - 1);
                return key;
            }
        }
    }
}
