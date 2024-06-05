package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleEditorTests {


    @Test
    @StdIo({"8", "1 abc", "3 3", "2 3", "1 xy", "3 2", "4", "4", "3 1"})
    public void test(StdOut out) {
        run();
        assertThat(out.capturedLines()).isEqualTo(List.of("c", "y", "a").toArray());
    }

    static class SimpleEditor {

        StringBuilder buffer = new StringBuilder();
        Deque<String[]> history = new ArrayDeque<>();

        String deleteOps(StringBuilder buffer, String backLen) {
            int len = buffer.length();
            int back = Integer.parseInt(backLen);
            String part = buffer.substring(len - back, len);
            buffer.delete(len - back, len);
            return part;
        }

        String appendOps(StringBuilder buffer, String part) {
            buffer.append(part);
            return Integer.toString(part.length());
        }

        public void process(String[] command) {
            switch (command[0]) {
                case "1" -> history.push(new String[]{"6", appendOps(buffer, command[1])});
                case "2" -> history.push(new String[]{"5", deleteOps(buffer, command[1])});
                case "3" -> System.out.println(buffer.charAt(Integer.parseInt(command[1]) - 1));
                case "4" -> process(history.pop());
                case "5" -> appendOps(buffer, command[1]);
                case "6" -> deleteOps(buffer, command[1]);
            }
        }
    }

    void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.nextLine();
            SimpleEditor se = new SimpleEditor();
            IntStream.range(0, n).forEach(i -> se.process(scanner.nextLine().trim().split(" ")));
        }
    }
}
