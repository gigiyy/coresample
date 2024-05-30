package com.example.springsample.prepare;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MergeLinkedListTests {

    @Test
    public void test() {
        SinglyLinkedListNode node3 = new SinglyLinkedListNode(7, null);
        SinglyLinkedListNode node2 = new SinglyLinkedListNode(3, node3);
        SinglyLinkedListNode node1 = new SinglyLinkedListNode(1, node2);

        SinglyLinkedListNode node6 = new SinglyLinkedListNode(2, null);
        SinglyLinkedListNode node5 = new SinglyLinkedListNode(1, node6);

        assertThat(expand(mergeLists(node1, node5))).isEqualTo(List.of(1, 1, 2, 3, 7));
    }

    List<Integer> expand(SinglyLinkedListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.data);
            head = head.next;
        }
        return result;
    }


    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return null;
        else if (head1 == null) return head2;
        else if (head2 == null) return head1;

        SinglyLinkedListNode head, tail;
        if (head1.data <= head2.data) {
            tail = head = head1;
            head1 = head1.next;
        } else {
            tail = head = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                tail.next = head1;
                tail = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                tail = head2;
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            tail.next = head1;
        }
        if (head2 != null) {
            tail.next = head2;
        }
        return head;
    }
}



@Data
@AllArgsConstructor
class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

}