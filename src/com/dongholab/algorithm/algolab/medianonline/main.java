package com.dongholab.algorithm.algolab.medianonline;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> smaller = new PriorityQueue<>(100000, Collections.reverseOrder());
        PriorityQueue<Integer> bigger = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            smaller.clear();
            bigger.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            long sum = 0;
            int number;

            while (st.hasMoreTokens()) {
                if (smaller.size() == 0) {
                    number = Integer.parseInt(st.nextToken());
                    smaller.add(number);
                } else {
                    number = Integer.parseInt(st.nextToken());
                    if (number < smaller.peek().intValue()) {
                        smaller.add(number);
                    } else {
                        bigger.add(number);
                    }
                }

                if (smaller.size() < bigger.size()) {
                    smaller.add(bigger.poll());
                } else if (smaller.size() > bigger.size() + 1) {
                    bigger.add(smaller.poll());
                }

                double currentMedian;
                if (smaller.size() == bigger.size()) {
                    currentMedian = Math.floor((smaller.peek() + bigger.peek()) * 0.5);
                } else {
                    currentMedian = smaller.peek();
                }

                sum += currentMedian;
            }
            System.out.println(sum % 10);
        }
    }
}
