package com.example.springsample.prepare;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;

public class HuffmanDecoderTests {


    @Test
    @StdIo
    public void testHuffman(StdOut out) {
        Node nb = new Node(1, 'B', null, null);
        Node nc = new Node(1, 'C', null, null);
        Node n2 = new Node(2, '\u0000', nb, nc);
        Node na = new Node(3, 'A', null, null);
        Node n1 = new Node(5, '\u0000', n2, na);
        decode("1001011", n1);
        assertThat(out.capturedLines()).isEqualTo(new String[]{"ABACA"});
    }

    void decode(String s, Node root) {
        Node current = root;
        for (char ch: s.toCharArray()) {
            if (ch == '1') {
                current = current.right;
            } else {
                current = current.left;
            }
            if (current.left == null && current.right == null) {
                System.out.print(current.data);
                current = root;
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Node {
        int frequency;
        char data;
        Node left;
        Node right;
    }
}
