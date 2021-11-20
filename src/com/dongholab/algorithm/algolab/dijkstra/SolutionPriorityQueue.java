package com.dongholab.algorithm.algolab.dijkstra;

import java.util.List;
import java.util.PriorityQueue;

class SolutionPriorityQueue {
    final static int NOT_VISIT = -1;

    public int solution(int N, int[][] road, int K) {
        int[][] map = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = NOT_VISIT;
            }
        }

        for (int[] r: road) {
            int a = r[0], b = r[1], c = r[2];
            int assignC = (map[a][b] != NOT_VISIT) ? Math.min(map[a][b], c) : c;
            map[a][b] = assignC;
        }
        boolean[] check = new boolean[N];
        int[][] distance = new int[N][2];


        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.get(1) - o1.get(1));

        return 0;
    }

    public static void main(String[] args) {
        SolutionPriorityQueue s = new SolutionPriorityQueue();
        s.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3);
        s.solution(	6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4);
    }
}