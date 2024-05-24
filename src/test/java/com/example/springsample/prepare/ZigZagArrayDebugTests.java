package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ZigZagArrayDebugTests {

    @Test
    @StdIo
    public void test(StdOut out) {
        findZigZagSequence(new int[]{1, 2, 5, 3, 7, 4, 6}, 7);
        assertThat(out.capturedLines()).isEqualTo(List.of("1 2 3 7 6 5 4").toArray());
    }
    public void findZigZagSequence(int [] a, int n){
        Arrays.sort(a);
        int mid = (n - 1)/2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        int st = mid + 1;
        int ed = n - 2;
        while(st <= ed){
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed - 1;
        }
        for(int i = 0; i < n; i++){
            if(i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }
}
