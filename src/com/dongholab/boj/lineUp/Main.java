package com.dongholab.boj.lineUp;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int current;
        while (!q.isEmpty()) {
            current = q.poll();
            result.offer(current);

            for (int i : graph.get(current)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        System.out.println(result.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(" ")));
    }
}