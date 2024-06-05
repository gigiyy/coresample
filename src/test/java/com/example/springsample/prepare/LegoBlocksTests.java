package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LegoBlocksTests {

    @Test
    public void test() {
        assertThat(legoBlocks(2, 2)).isEqualTo(3);
        assertThat(legoBlocks(3, 2)).isEqualTo(7);
        assertThat(legoBlocks(2, 3)).isEqualTo(9);
        assertThat(legoBlocks(4, 4)).isEqualTo(3375);
    }

    @Test
    public void testLarge() {
        assertThat(legoBlocks(271, 700)).isEqualTo(820286798);
        assertThat(legoBlocks(988, 857)).isEqualTo(615792624);
    }


    @Test
    public void testAddInt() {
        System.out.printf("%,d\n", (long) Math.pow(10, 9) + 7);
        int i1 = 387559437;
        int i2 = 747044834;
        int i3 = 439975209;
        int i4 = 775641458;
        System.out.println((i1 + i2 + i3 + i4));
    }

    public int legoBlocks(int n, int m) {
        // Write your code here
        if (n == 1) {
            if (m > 4) return 0;
            else return 1;
        }
        long mod = 1_000_000_007L;
        long[] colm = new long[]{0, 1, 2, 4, 8};
        long[] col = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            if (i < 5) {
                col[i] = colm[i];
            } else {
                col[i] = col[i - 1] + col[i - 2] + col[i - 3] + col[i - 4];
                col[i] %= mod;
            }
        }

        long[] part = new long[m + 1];
        for (int i = 0; i <= m; i++) {
            part[i] = 1;
            // to calculate power(col[i], n). it's possible to use BigInteger.pow(n) to avoid overflow,
            // but it's took much longer than simple loops using primitive type
            // also Math.pow(col[i], n) might also overflow and the result will be 0;
//            part[i] = (long) (Math.pow(col[i], n) % mod);
            for (int j = 0; j < n; j++) {
                part[i] *= col[i];
                part[i] %= mod;
            }
        }

        long[] result = new long[m + 1];
        result[1] = 1;
        for (int i = 2; i <= m; i++) {
            long bad = 0L;
            for (int j = 1; j < i; j++) {
                bad += result[j] * part[i - j];
                bad %= mod;
            }
            result[i] = (part[i] + mod - bad) % mod;
        }
        return (int) result[m];

    }
}
