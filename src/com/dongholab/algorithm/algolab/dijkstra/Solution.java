package com.dongholab.algorithm.algolab.dijkstra;

class Solution {
    class Graph {
        final int INF = Integer.MAX_VALUE / 2;
        final int NOT_VISIT = -1;
        private int n;           //노드들의 수
        private int maps[][];    //노드들간의 가중치 저장할 변수

        public Graph(int n) {
            this.n = n;
            maps = new int[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    maps[i][j] = NOT_VISIT;
                }
            }
        }

        public void input(int i, int j, int w) {
            int assignW = (maps[j][i] != NOT_VISIT) ? Math.min(maps[j][i], w) : w;
            maps[i][j] = assignW;
            maps[j][i] = assignW;
        }

        public int dijkstra(int v, int k) {
            int distance[] = new int[n + 1];          //최단 거리를 저장할 변수
            boolean[] check = new boolean[n + 1];     //해당 노드를 방문했는지 체크할 변수

            for (int i = 1; i < n + 1; i++) {
                distance[i] = INF;
            }

            distance[v] = 0;
            check[v] = true;

            //연결노드 distance갱신
            for (int i = 1; i < n + 1; i++) {
                if (!check[i] && maps[v][i] != NOT_VISIT) {
                    distance[i] = maps[v][i];
                }
            }


            for (int a = 0; a < n - 1; a++) {
                int min = INF;
                int minIndex = -1;

                // 최소길이, 최소인덱스 찾기
                for (int i = 1; i < n + 1; i++) {
                    if (!check[i] && distance[i] != INF) {
                        if (distance[i] < min) {
                            min = distance[i];
                            minIndex = i;
                        }
                    }
                }

                check[minIndex] = true;
                for (int i = 1; i < n + 1; i++) {
                    if (!check[i] && maps[minIndex][i] != NOT_VISIT) {
                        if (distance[i] > distance[minIndex] + maps[minIndex][i]) {
                            distance[i] = distance[minIndex] + maps[minIndex][i];
                        }
                    }
                }
            }

            int cnt = 0;
            //결과값 출력
            for (int i = 1; i < n + 1; i++) {
                if (distance[i] <= k) {
                    cnt++;
                }
            }
            return cnt;
        }
    }

    public int solution(int N, int[][] road, int K) {
        Graph g = new Graph(N);
        for (int[] way: road) {
            int a = way[0], b = way[1], c = way[2];
            g.input(a, b, c);
        }

        return g.dijkstra(1, K);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3);
        s.solution(	6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4);
    }
}