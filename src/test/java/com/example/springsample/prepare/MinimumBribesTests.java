package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumBribesTests {

    @Test
    @StdIo
    public void test(StdOut out) {
        minimumBribes(List.of(1, 2, 5, 3, 4, 7, 8, 6));
        assertThat(out.capturedLines()).isEqualTo(List.of("4").toArray());
    }

    @Test
    @StdIo
    public void test2(StdOut out) {
        minimumBribes(List.of(1, 2, 5, 3, 7, 8, 6, 4));
        assertThat(out.capturedLines()).isEqualTo(List.of("7").toArray());
    }

    @Test
    @StdIo
    public void testChaotic(StdOut out) {
        minimumBribes(List.of(5, 1, 2, 3, 7, 8, 6, 4));
        assertThat(out.capturedLines()).isEqualTo(List.of("Too chaotic").toArray());
    }

    @Test
    @StdIo
    public void testLoooong(StdOut out) throws IOException {
        for (List<Integer> q : readFile()) {
            minimumBribes(q);
        }
        assertThat(out.capturedLines()).isEqualTo(List.of("115173", "Too chaotic", "115013", "Too chaotic").toArray());
    }

    List<List<Integer>> readFile() throws IOException {
        List<List<Integer>> result = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/test/resources/minimum-bribes-input.txt"))) {
            int n = Integer.parseInt(br.readLine().trim());
            IntStream.range(0, n).forEach(i -> {
                try {
                    int count = Integer.parseInt(br.readLine().trim());
//                    System.out.println("got input of #" + count);
                    List<Integer> q = Stream.of(br.readLine().trim().split(" "))
                            .map(Integer::parseInt)
                            .toList();
                    result.add(q);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return result;
    }

    // to calculate the total moves, it's necessary to check
    // how many smaller sticker is still behind current sticker
    // since the sticker range from 1 to n
    // it's faster to use BitSet to check whether the sticker turns up already
    // using the nextClearBit(last) to skip those shown up from the head of the queue.
    public void minimumBribes(List<Integer> q) {
        BitSet found = new BitSet(q.size() + 1);
        int total = 0;
        int last = 1;
        for (int n : q) {
            int move = 0;
            last = found.nextClearBit(last);
            for (int i = last; i < n; i++) {
                if (!found.get(i)) {
                    if (move == 2) {
                        System.out.println("Too chaotic");
                        return;
                    } else {
                        move++;
                    }
                }
            }
            found.set(n);
            total += move;
        }
        System.out.println(total);
    }

    public void minimumBribesV1(List<Integer> q) {
        // Write your code here
        Map<Integer, Integer> change = new HashMap<>();
        for (int n : q) {
            for (int key : change.keySet()) {
                if (n < key) {
                    int move = change.getOrDefault(key, 0);
                    if (move == 2) {
                        System.out.println("Too chaotic");
                        return;
                    }
                    change.put(key, move + 1);
                }
            }
            change.put(n, 0);
        }
        Integer sum = change.values().stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
