package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FlipMatrixAndSumUpperLeftTests {


    @Test
    public void test() {
        assertThat(flippingMatrix(List.of(List.of(1, 2), List.of(3, 4)))).isEqualTo(4);
        assertThat(flippingMatrix(List.of(List.of(3, 4, 7, 9), List.of(8, 5, 2, 1),
                List.of(0, 4, 2, 3), List.of(2, 2, 1, 5)))).isEqualTo(29);
    }

    // input: a 2n*2n matrix
    // return value: the maximum possible sum of the upper left corner (n*n) of the matrix
    public int flippingMatrix(List<List<Integer>> matrix) {
        // Write your code here
        int n = matrix.size();
        int sum = 0;
        // the for each element of the upper left corner,
        // they can be swap freely with other elements of below positions
        // (i, j), (i, n - 1 - j), (n - 1 - i, j), (n - 1- i, n - 1 -j)
        for (int i = 0; i < n / 2; i++)
            for (int j = 0; j < n / 2; j++)
                sum += max(matrix.get(i).get(j), matrix.get(i).get(n - j - 1),
                        matrix.get(n - i - 1).get(j), matrix.get(n - i - 1).get(n - j - 1));
        return sum;
    }

    int max(int e1, int e2, int e3, int e4) {
        int max1 = Math.max(e1, e2);
        int max2 = Math.max(e3, e4);
        return Math.max(max1, max2);
    }

}
