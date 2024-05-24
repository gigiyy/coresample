package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SumIntOrStringFromStdInTests {
    @Test
    @StdIo({"1 3 4 5 9", "abc def gek"})
    public void test(StdOut out) {
        sum();
        assertThat(out.capturedLines()).isEqualTo(List.of("22").toArray());
    }

    @Test
    @StdIo({"abc def  gek"})
    public void testString(StdOut out) {
        sum();
        assertThat(out.capturedLines()).isEqualTo(List.of("abcdefgek").toArray());
    }

    public void sum() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] input = br.readLine().split(" ");
            try {
                Integer sum = Stream.of(input).map(Integer::parseInt).mapToInt(Integer::intValue).sum();
                System.out.println(sum);
            } catch (NumberFormatException e) {
                String sum = Stream.of(input).reduce("", (a, b) -> a + b);
                System.out.println(sum);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
