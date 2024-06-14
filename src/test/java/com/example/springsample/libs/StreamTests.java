package com.example.springsample.libs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTests {

    @Test
    public void testStreamCollect() {
        ArrayList<Integer> result = IntStream.range(0, 10)
                .filter(i -> i % 2 == 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        int[] aResult = IntStream.range(0, 10)
                .filter(i -> i % 2 == 0)
                .toArray();
        assertThat(result).contains(0, 2, 4, 6, 8);
        assertThat(aResult).contains(0, 2, 4, 6, 8);
    }

    @Test
    public void testSortStringContent() {
        String s = "defacs";
        String sorted = s.chars().sorted().collect(StringBuilder::new,
                (sb, c) -> sb.append((char) c), StringBuilder::append).toString();
        String aSorted = s.chars().sorted().mapToObj(ch -> String.valueOf((char) ch)).reduce("", String::concat);
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        String bSorted = new String(chs);
        assertThat(sorted).isEqualTo("acdefs");
        assertThat(aSorted).isEqualTo(sorted);
        assertThat(bSorted).isEqualTo(sorted);
    }
}
