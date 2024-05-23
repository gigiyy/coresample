package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxListTests {

    @Test
    public void test() {
        miniMaxSum(List.of(1, 3, 7, 5, 9));
        miniMaxSum(List.of(769082435, 210437958, 673982045, 375809214, 380564127));
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
