package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeIndexTests {

    @Test
    public void test() {
        assertThat(palindromeIndex("bcbc")).isIn(List.of(0, 3));
        assertThat(palindromeIndex("abcdfcba")).isIn(List.of(3, 4));
    }
    public int palindromeIndex(String s) {
        // Write your code here
        if (testPalindrome(s)) return -1;
        for (int i = 0, j = s.length() -1; i < j; ) {
            if (s.charAt(i) != s.charAt(j)) {
                StringBuilder sb = new StringBuilder(s);
                sb.deleteCharAt(i);
                if (testPalindrome(sb.toString())) {
                    return i;
                } else {
                    sb = new StringBuilder(s);
                    sb.deleteCharAt(j);
                    if (testPalindrome(sb.toString())) {
                        return j;
                    } else {
                        return -1;
                    }
                }
            } else {
                i++;
                j--;
            }
        }
        return -1;
    }

    boolean testPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return s.contentEquals(sb.reverse());
    }
}
