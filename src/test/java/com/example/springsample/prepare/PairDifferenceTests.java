package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PairDifferenceTests {
    @Test
    public void test() {
        assertThat(pairs(2, List.of(1, 5, 3, 4, 2))).isEqualTo(3);
    }

    // using bitset to store the occurrence of the numbers in list
    // now only need to check whether i and i+k is set or not with O(N) complexity
    public int pairs(int k, List<Integer> arr) {
        BitSet set = new BitSet();
        for (int num : arr) {
            set.set(num);
        }
        int pair = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) && set.get(Math.min(set.size() - 1, i+k))) {
                pair++;
            }
        }
        return pair;
    }

    // checking the value in a sorted list that's at most kth element away.
    // so it's O(kN) complexity
    public int pairsV1(int k, List<Integer> arr) {
        // Write your code here
        List<Integer> sorted = new ArrayList<>(arr);
        int n = sorted.size();
        Collections.sort(sorted);
        int pair = 0;
        for (int idx = 0; idx < n; idx++) {
            int now = sorted.get(idx);
            for (int idy = Math.min(n - 1, idx + k); idy > idx; idy--) {
                if (sorted.get(idy) - now == k) {
                    pair++;
                    break;
                }
            }
        }
        return pair;
    }
}
