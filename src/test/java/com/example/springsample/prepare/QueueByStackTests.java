package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueByStackTests {

    @Test
    @StdIo({"10", "1 42", "2", "1 14", "3", "1 25", "3", "1 60", "1 78", "2", "2"})
    public void test(StdOut out) {
        run();
        assertThat(out.capturedLines()).isEqualTo(List.of("14", "14").toArray());
    }

    // when reading with Scanner, mindful that readInt and readLine operations is mixed together
    // since readIn will read till the EoL, then the next readLine operation will return an empty line.
    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StackQueue q = new StackQueue();
        IntStream.range(0, n).forEach(i -> {
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> q.enqueue(scanner.nextInt());
                case 2 -> q.dequeue();
                case 3 -> q.printFist();
            }
        });
    }

    static class StackQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;

        public StackQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void enqueue(int e) {
            in.push(e);
        }

        public int dequeue() {
            if (out.isEmpty()) offload();
            return out.pop();
        }

        public void printFist() {
            if (out.isEmpty()) offload();
            System.out.println(out.peek());
        }

        private void offload() {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }
}
