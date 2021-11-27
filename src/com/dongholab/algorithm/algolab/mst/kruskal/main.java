package com.dongholab.algorithm.algolab.mst.kruskal;

import java.io.*;
import java.util.*;

public class main {
    public static class Edge implements Comparable<Edge> {
        int from, to, dis;

        public Edge(int from, int to, int dis) {
            this.from = from;
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge compareEdge) {
            return this.dis - compareEdge.dis;
        }
    }

    public static int findSet(int[] p, int x) {
        if (p[x] == x) return x;
        else return p[x] = findSet(p, p[x]);
    }

    public static void union(int[] p, int x, int y) {
        if (x < y) p[findSet(p, y)] = findSet(p, x);
        else p[findSet(p, x)] = findSet(p, y);
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int a = 0; a < N; a++) {
            pq.clear();
            int ans = 0, cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int edgeSize = Integer.parseInt(st.nextToken());

            parent = new int[edgeSize];
            for (int b = 0; b < edgeSize; b++) {
                parent[b] = b;
            }

            for (int b = 0; b < edgeSize; b++) {
                st = new StringTokenizer(br.readLine());
                int edge = Integer.parseInt(st.nextToken()) - 1;
                int edgeN = Integer.parseInt(st.nextToken());
                for (int c = 0; c < edgeN; c++) {
                    int node = Integer.parseInt(st.nextToken()) - 1;
                    int dis = Integer.parseInt(st.nextToken());
                    pq.add(new Edge(edge, node, dis));
                }
            }

            while (!pq.isEmpty()) {
                Edge edge = pq.poll();

                if (findSet(parent, edge.from) != findSet(parent, edge.to)) {
                    ans += edge.dis;
                    if (++cnt == edgeSize) break;
                    union(parent, edge.from, edge.to);
                }
            }

            System.out.println(ans);
        }
    }
}