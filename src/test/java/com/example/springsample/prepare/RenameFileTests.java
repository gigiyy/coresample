package com.example.springsample.prepare;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RenameFileTests {

    @Test
    public void test() {
        assertThat(renameFile("aba", "ababa")).isEqualTo(4);
    }

    @Test
    public void testLoooog() {
        int count = renameFile("mzb", "mmzzzzzzzzzzbbbbbzzzbbbbbbbbbbbbbzzzzzzzzzzzbbbbbbbbbbbbbbbbbbzzzzzzzzzzzzzzzbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println(count);
    }

    // since the new name is much shorter than old one
    // so check the occurrences of the first character of the new name in old one,
    // then count the ways to form the rest of new name in the sub string starting from
    // each occurrence respectively and recursively
    public int renameFile(String newName, String oldName) {
        log.info("in >>> newName: {}, oldName: {}", newName, oldName);
        if (newName.isEmpty()) return 0;
        char first = newName.charAt(0);
        if (oldName.length() < newName.length()) return 0;
        if (newName.length() == 1) return (int) oldName.chars().filter(ch -> ch == first).count();

        int mod = (int) Math.pow(10, 9) + 7;
        int index = oldName.indexOf(first);
        int count = 0;
        while (index < oldName.length() && index != -1) {
            count += renameFile(newName.substring(1), oldName.substring(index + 1)) % mod;
            index = oldName.indexOf(first, index + 1);
        }
        log.info("out << newName: {}, oldName: {}, count: {}", newName, oldName, count);
        return count % mod;
    }


    // using brute-force algorithm to check all possible combinations and it would be too slow for longer name
    public int renameFileV1(String newName, String oldName) {
        // Write your code here
        int length = oldName.length();
        int count = 0;
        for (int idx = 0; idx < Math.pow(2, length); idx++) {
            StringBuilder sb = new StringBuilder(oldName);
            for (int idy = 0; idy < length; idy++) {
                if ((idx >> idy) % 2 == 0) {
                    sb.deleteCharAt(length - 1 - idy);
                }
            }
            System.out.println(sb);
            if (newName.contentEquals(sb)) {
                count++;
            }
        }
        return count % (int) (Math.pow(10, 9) + 7);
    }
}
