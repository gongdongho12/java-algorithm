package com.dongholab.algorithm;

import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + queue.poll());
        }
    }
}
