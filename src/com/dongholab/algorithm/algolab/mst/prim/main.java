package com.dongholab.algorithm.algolab.mst.prim;

import java.io.*;
import java.util.*;

public class main {
    public static class Edge implements Comparable<Edge> {
        int node;
        int dis;

        public Edge(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.dis, edge.dis);
        }
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        ArrayList<ArrayList<Edge>> graph;
        boolean[] visit;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int a = 0; a < N; a++) {
            pq.clear();
            int ans = 0, cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int edgeSize = Integer.parseInt(st.nextToken());
            visit = new boolean[edgeSize];

            graph = new ArrayList<>();
            for (int b = 0; b < edgeSize; b++) {
                graph.add(new ArrayList<>());
            }

            for (int b = 0; b < edgeSize; b++) {
                st = new StringTokenizer(br.readLine());
                int edge = Integer.parseInt(st.nextToken()) - 1;
                int edgeN = Integer.parseInt(st.nextToken());
                for (int c = 0; c < edgeN; c++) {
                    int node = Integer.parseInt(st.nextToken()) - 1;
                    int dis = Integer.parseInt(st.nextToken());
                    graph.get(edge).add(new Edge(node, dis));
                }
            }

            pq.add(new Edge(0, 0));
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();

                if (visit[edge.node]) continue;

                visit[edge.node] = true;
                ans += edge.dis;
                for (Edge next : graph.get(edge.node)) {
                    if (!visit[next.node]) {
                        pq.add(next);
                    }
                }
            }

            if (++cnt == edgeSize) break;

            System.out.println(ans);
        }
    }
}