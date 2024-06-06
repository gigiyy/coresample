package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NoPrefixTests {

    @Test
    @StdIo
    public void testPrefix(StdOut out) {
        noPrefix(List.of("aab", "aac", "aacghgh", "aabghgh"));
        assertThat(out.capturedLines()).isEqualTo(List.of("BAD SET", "aacghgh").toArray());
    }

    // using a Trie data structure to store the word list
    // checking isWord flag along the way to find out whether it's prefix to each other
    public static void noPrefix(List<String> words) {
        Node root = new Node();
        for (String word : words) {
            Node current = root;
            boolean created = false;
            for (char ch : word.toCharArray()) {
                if (current.children.containsKey(ch)) {
                    current = current.children.get(ch);
                    // found an existing word that is prefix to current word
                    if (current.isWord) {
                        System.out.println("BAD SET");
                        System.out.println(word);
                        return;
                    }
                } else {
                    created = true;
                    Node child = new Node();
                    child.content = ch;
                    current.children.put(ch, child);
                    current = child;
                }
            }
            // no new child is created along the way,
            // means current word is prefix to one of previous longer word
            if (!created) {
                System.out.println("BAD SET");
                System.out.println(word);
                return;
            }
            current.isWord = true;
        }
        System.out.println("GOOD SET");
    }

    static class Node {
        public Node() {
            content = '\u0000';
            children = new HashMap<>();
            isWord = false;
        }

        char content;
        private HashMap<Character, Node> children;
        boolean isWord;
    }

    public static void noPrefixV1(List<String> words) {
        // Write your code here
        int n = words.size();
        if (n == 1) {
            System.out.println("GOOD SET");
        }
        for (int i = 1; i < n; i++) {
            String current = words.get(i);
            for (int j = 0; j < i; j++) {
                String test = words.get(j);
                int len = Math.min(current.length(), test.length());
                if (current.substring(0, len).equals(test.substring(0, len))) {
                    System.out.println("BAD SET");
                    System.out.println(current);
                    return;
                }
            }
        }
        System.out.println("GOOD SET");
    }
}
