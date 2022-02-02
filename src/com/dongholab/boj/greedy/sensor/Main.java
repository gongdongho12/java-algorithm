package com.dongholab.boj.greedy.sensor;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        PriorityQueue<Integer> diffPQ = new PriorityQueue<>();
        int prev = pq.poll();
        while (!pq.isEmpty()) {
            diffPQ.add(pq.peek() - prev);
            prev = pq.poll();
        }
        System.out.println(IntStream.range(0, N - K).map(i -> diffPQ.poll()).sum());
    }
}
