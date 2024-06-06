package com.example.springsample.prepare;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;

public class BTreePreOrderTests {


    @Test
    @StdIo
    public void testBTreePreOrder(StdOut out) {
        Node n4 = new Node(4, null, null);
        Node n3 = new Node(3, null, n4);
        Node n6 = new Node(6, null, null);
        Node n5 = new Node(5, n3, n6);
        Node n2 = new Node(2, null, n5);
        Node n1 = new Node(1, null, n2);
        preOrder(n1);
        assertThat(out.capturedLines()).isEqualTo(new String[]{"1 2 5 3 4 6"});
    }


    public void preOrder(Node root) {
        System.out.print(root.data);
        if (root.left != null) {
            System.out.print(" ");
            preOrder(root.left);
        }
        if (root.right != null) {
            System.out.print(" ");
            preOrder(root.right);
        }
    }

    @Data
    @AllArgsConstructor
    static class Node {
        int data;
        Node left;
        Node right;
    }
}
