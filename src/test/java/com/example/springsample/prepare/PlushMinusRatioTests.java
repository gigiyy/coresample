package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class PlushMinusRatioTests {

    @Test
    public void test() {
        plusMinus(List.of(-1, -1, 1, 0, 1));
        plusMinus(List.of(1, -2, -7, 9, 1, -8, -5));
    }

    BigDecimal ratio(int count, int len) {
        return new BigDecimal(count).divide(new BigDecimal(len), new MathContext(6, RoundingMode.HALF_UP));
    }

    public void plusMinus(List<Integer> arr) {
        // Write your code here
        int len = arr.size();
        int plus = 0;
        int minus = 0;
        int zero = 0;
        for (int num : arr) {
            if (num > 0) {
                plus++;
            } else if (num < 0) {
                minus++;
            } else {
                zero++;
            }
        }
        System.out.printf("%.6f\n", ratio(plus, len));
        System.out.printf("%.6f\n", ratio(minus, len));
        System.out.printf("%.6f\n", ratio(zero, len));
    }

}
