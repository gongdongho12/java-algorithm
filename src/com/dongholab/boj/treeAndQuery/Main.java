package com.dongholab.boj.treeAndQuery;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static List<Integer>[] list;
    static int dp[];
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        list = new LinkedList[N + 1];
        dp = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 1; i < list.length; i++) {
            list[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            list[U].add(V);
            list[V].add(U);
        }

        dfs(R);
        String answer = IntStream.range(0, Q).mapToObj(i -> {
            try {
                int U = Integer.parseInt(br.readLine());
                return String.valueOf(dp[U]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(v -> v != null).collect(Collectors.joining("\n"));

        System.out.println(answer);
    }

    public static int dfs(int current) {
        if (dp[current] != 0) {
            return dp[current];
        }
        visit[current] = true;
        int count = 1;

        for (int node : list[current]) {
            if (!visit[node]) {
                count += dfs(node);
            }
        }
        dp[current] = count;

        return dp[current];
    }
}