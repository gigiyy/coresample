package com.example.springsample.prepare;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SortedSumTests {

    @Test
    public void test() {
        assertThat(sortedSum(List.of(4, 3, 2, 1))).isEqualTo(65);
    }

    @Test
    public void testLongLargeNumberList() {
        int result = sortedSum(List.of(9783532, 23285325, 22343893, 232342345, 29838577, 2383536, 908887, 8898678));
        log.info("the sorted sum is {}", result);
    }

    // i in range(1, n + 1), sort the list a[0:i] in ascend order
    // then calculate the sum of each element multiplied with its index
    public int sortedSum(List<Integer> a) {
        // Write your code here
        int mod = (int) Math.pow(10, 9) + 7;
        log.info("int max: {}, mod: {}", Integer.MAX_VALUE, mod);
        int sum = 0;
        for (int len = 1; len <= a.size(); len++) {
            List<Integer> sub = new ArrayList<>(a.subList(0, len));
            Collections.sort(sub);
            log.info("{}", sub);
            for (int idx = 0; idx < sub.size(); idx++) {
                sum += sub.get(idx) * (idx + 1) % mod;
                if (sum > mod) log.info("over flow of sum {}", sum);
                sum %= mod;
            }
        }
        return sum;
    }
}
