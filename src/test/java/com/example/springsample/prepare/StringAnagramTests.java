package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StringAnagramTests {


    @Test
    public void test() {
        List<String> dictionary = List.of("abc", "cab", "bac", "de");
        List<String> query = List.of("abc", "ef");
        assertThat(stringAnagram(dictionary, query)).isEqualTo(List.of(3, 0));
    }

    public String sortString(String src) {
        char[] chars = src.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<Integer> stringAnagram(List<String> dictionary, List<String> query) {
        // Write your code here
        Map<String, Integer> mapped = new HashMap<>();
        for (String dic : dictionary) {
            mapped.compute(sortString(dic), (key, val) -> val == null ? 1 : val + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (String src : query) {
            result.add(mapped.getOrDefault(sortString(src), 0));
        }

        return result;
    }
}
