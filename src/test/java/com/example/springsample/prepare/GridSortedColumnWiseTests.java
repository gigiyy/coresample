package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GridSortedColumnWiseTests {


    @Test
    public void test() {
        assertThat(gridChallenge(List.of("abc", "lmp", "qrt"))).isEqualTo("YES");
        assertThat(gridChallenge(List.of("mpxz", "abcd", "wlmf"))).isEqualTo("NO");
        assertThat(gridChallenge(List.of("abc", "hjk", "mpq", "rtv"))).isEqualTo("YES");
    }

    public String gridChallenge(List<String> grid) {
        // Write your code here
        if (grid.size() == 1) return "YES";
        List<String> sorted = new ArrayList<>();
        for (String src : grid) {
            char[] arr = src.toCharArray();
            Arrays.sort(arr);
            sorted.add(new String(arr));
        }
        int ny = grid.size();
        int nx = grid.getFirst().length();
        for (int idx = 0; idx < nx; idx++) {
            char[] col = new char[ny];
            for (int idy = 0; idy < ny; idy++) {
                col[idy] = sorted.get(idy).charAt(idx);
            }
            String before = new String(col);
            Arrays.sort(col);
            String after = new String(col);
            if (!before.equals(after)) {
                return "NO";
            }
        }
        return "YES";

    }
}
