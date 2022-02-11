package com.dongholab.boj.graphTraversal.work;

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visits;
    static int count;

    public static void main(String[] args) throws IOException {
        count = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        visits = new boolean[N];
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            List<Integer> startList = map.getOrDefault(end, new LinkedList<>());
            startList.add(start);
            map.put(end, startList);
        }
        int K = Integer.parseInt(br.readLine());
        dfs(map, K);
        System.out.println(count);
    }

    public static void dfs(Map<Integer, List<Integer>> map, int select) {
        visits[select - 1] = true;
        try {
            List<Integer> startList = map.get(select);
            startList.forEach(start -> {
                if (!visits[start - 1]) {
                    count++;
                    dfs(map, start);
                }
            });
        } catch (NullPointerException npe) {}
    }
}
