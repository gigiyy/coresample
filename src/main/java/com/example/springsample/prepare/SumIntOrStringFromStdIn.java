package com.example.springsample.prepare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class SumIntOrStringFromStdIn {

    public static void main(String[] args) {
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
