package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MinMaxListTests {

    @Test
    @StdIo
    public void test(StdOut out) {
        miniMaxSum(List.of(1, 3, 7, 5, 9));
        miniMaxSum(List.of(769082435, 210437958, 673982045, 375809214, 380564127));
        assertThat(out.capturedLines()).isEqualTo(
                List.of("16 24", "1640793344 2199437821").toArray()
        );
    }

    // min and max sum of 4 elements in a 5 element array.
    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        BigInteger min = sorted.subList(0, 4).stream().map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
        BigInteger max = sorted.subList(1, 5).stream().map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
        System.out.println(min + " " + max);
    }
}
