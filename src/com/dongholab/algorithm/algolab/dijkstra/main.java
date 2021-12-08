package com.dongholab.algorithm.algolab.dijkstra;

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Edge>[] graph;

        int N = Integer.parseInt(br.readLine());
        for (int a = 0; a < N; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int edgeSize = Integer.parseInt(st.nextToken());

            graph = new ArrayList[edgeSize];
            for (int i = 0; i < edgeSize; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int b = 0; b < edgeSize; b++) {
                st = new StringTokenizer(br.readLine());
                int edge = Integer.parseInt(st.nextToken()) - 1;
                int edgeN = Integer.parseInt(st.nextToken());
                for (int c = 0; c < edgeN; c++) {
                    int node = Integer.parseInt(st.nextToken()) - 1;
                    int dis = Integer.parseInt(st.nextToken());
                    graph[edge].add(new Edge(node, dis));
                }
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            boolean[] visit = new boolean[edgeSize];
            int[] append = new int[edgeSize];
            Edge[] dest = new Edge[edgeSize];
            dest[0] = new Edge(0,0);
            pq.add(dest[0]);
            for (int i = 1; i < edgeSize; i++) {
                dest[i] = new Edge(i, Integer.MAX_VALUE);
                pq.add(dest[i]);
            }
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (edge.dis == Integer.MAX_VALUE) break;

                for (Edge next: graph[edge.node]) {
                    if(visit[next.node]) continue;
                    if (dest[next.node].dis > dest[edge.node].dis + next.dis) {
                        append[next.node] = next.dis;
                        dest[next.node].dis = dest[edge.node].dis + next.dis;
                        pq.remove(dest[next.node]);
                        pq.add(dest[next.node]);

                    }
                }
                visit[edge.node] = true;
            }

            System.out.println(Arrays.stream(append).sum());
        }
    }
}
