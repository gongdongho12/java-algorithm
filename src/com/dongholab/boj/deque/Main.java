package com.dongholab.boj.deque;

import java.io.*;
import java.util.*;

public class Main {
    public static void printValue(Integer value) {
        System.out.println(value == null ? -1 : value);
    }

    public static void main(String[] args) throws IOException {
        Deque<Integer> deque = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    printValue(deque.pollFirst());
                    break;
                case "pop_back":
                    printValue(deque.pollLast());
                    break;
                case "size":
                    printValue(deque.size());
                    break;
                case "empty":
                    printValue(deque.isEmpty() ? 1 : 0);
                    break;
                case "front":
                    printValue(deque.peekFirst());
                    break;
                case "back":
                    printValue(deque.peekLast());
                    break;
            }
        }
    }
}
