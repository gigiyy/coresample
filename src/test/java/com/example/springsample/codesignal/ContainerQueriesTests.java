package com.example.springsample.codesignal;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerQueriesTests {

    @Test
    public void testQueries() {
        assertThat(solution(new String[][]{{"ADD", "1"}, {"ADD", "2"}, {"EXISTS", "1"}, {"GET_NEXT", "1"}, {"GET_NEXT", "2"}, {"REMOVE", "1"}, {"REMOVE", "1"}}))
                .isEqualTo(new String[]{"", "", "true", "2", "", "true", "false"});
    }

    String[] solution(String[][] queries) {
        List<Integer> container = new ArrayList<>();
        final String add = "ADD";
        final String exists = "EXISTS";
        final String remove = "REMOVE";
        final String next = "GET_NEXT";
        String[] result = new String[queries.length];
        Arrays.fill(result, "");
        for (int i = 0; i < queries.length; i++) {
            String[] command = queries[i];
            int param = Integer.parseInt(command[1]);
            switch (command[0]) {
                case add -> container.add(param);
                case exists -> result[i] = Boolean.toString(container.contains(param));
                case remove -> result[i] = Boolean.toString(container.remove(Integer.valueOf(param)));
                case next -> result[i] = container.stream()
                        .filter(e -> e > param).min(Integer::compare)
                        .map(e -> Integer.toString(e)).orElse("");
            }
        }
        return result;
    }
}
