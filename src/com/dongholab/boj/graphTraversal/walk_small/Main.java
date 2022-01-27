package com.dongholab.boj.graphTraversal.walk_small;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> Graph[];
    static boolean visit[];
    static int realVisit[];
    static int start, end, ans;

    static class Pair {
        int index, value;

        public Pair(int index, int cnt) {
            this.index = index;
            this.value = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        realVisit = new int[N + 1];
        Graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            Graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Graph[a].add(b);
            Graph[b].add(a);
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            Collections.sort(Graph[i]);
        }

        bfs(start, end);
        for(int i = 1; i <= N; i++) {
            visit[i] = false;
        }

        int last = realVisit[end];
        while (last > 0) {
            visit[last] = true;
            last = realVisit[last];
        }

        bfs(end, start);

        System.out.println(ans);
    }

    static void bfs(int st, int ed) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(st, 0));
        visit[st] = true;

        all:
        while (!q.isEmpty()) {
            Pair info = q.poll();

            for (int node: Graph[info.index]) {
                if(!visit[node]){
                    visit[node] = true;
                    realVisit[node] = info.index;
                    q.offer(new Pair(node, info.value + 1));
                }

                if (node == ed) {
                    ans += info.value + 1;
                    break all;
                }
            }
        }
    }
}
