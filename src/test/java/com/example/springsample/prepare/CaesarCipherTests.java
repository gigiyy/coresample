package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CaesarCipherTests {


    @Test
    public void test() {
        assertThat(caesarCipher("middle-Outz", 2)).isEqualTo("okffng-Qwvb");
    }

    public static String caesarCipher(String s, int k) {
        // Write your code here
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(s);
        for (int idx = 0; idx < s.length(); idx++) {
            char ch = sb.charAt(idx);
            int atLower = lower.indexOf(ch);
            int atUpper = upper.indexOf(ch);
            if (atLower != -1) {
                sb.setCharAt(idx, lower.charAt((atLower + k) % 26));
            } else if (atUpper != -1) {
                sb.setCharAt(idx, upper.charAt((atUpper + k) % 26));
            }
        }
        return sb.toString();
    }
}
