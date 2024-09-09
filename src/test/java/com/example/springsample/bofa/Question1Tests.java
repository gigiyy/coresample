package com.example.springsample.bofa;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Question1Tests {



    @Test
    @StdIo
    public void testRemoveDuplicate(StdOut out) {
        removeDuplicate(new int[]{1, 4, 2, 7, 4, 7});
        assertThat(out.capturedLines()).isEqualTo(new String[]{"1427"});
    }
    //Program to delete duplicate from an array
    //Original Array: 142747
    //After removing duplicate: 1427

    void removeDuplicate(int[] input) {
        Set<Integer> found = new HashSet<>();
        for (int i : input) {
            if (!found.contains(i)) {
                System.out.print(i);
                found.add(i);
            }
        }
    }


    @Test
    public void testReverseNumber() {
        assertThat(reverse(2345)).isEqualTo(5432);
        assertThat(reverse(2)).isEqualTo(2);
        assertThat(reverse(0)).isEqualTo(0);
    }
    //program to reverse a number
    //Enter an integer: 2345
    //Reversed number = 5432

    int reverse(int input) {
        // loop for the digit
        int num = input;
        int rev = 0;
        do {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num = num / 10;
        } while (num > 0);
        return rev;
    }


}
