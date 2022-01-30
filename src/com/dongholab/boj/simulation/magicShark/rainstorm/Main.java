package com.dongholab.boj.simulation.magicShark.rainstorm;

import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class World {
    int[][] map;
    boolean[][] visited;
    Queue<Pair> cloudQueue;
    int N;
    int answer;

    final static Pair[] direction = {
            new Pair(0, -1),
            new Pair(-1, -1),
            new Pair(-1, 0),
            new Pair(-1, 1),
            new Pair(0, 1),
            new Pair(1, 1),
            new Pair(1, 0),
            new Pair(1, -1)
    };

    public World(int N) {
        this.N = N;
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        cloudQueue = new LinkedList<>();
        cloudQueue.add(new Pair(N, 1));
        cloudQueue.add(new Pair(N, 2));
        cloudQueue.add(new Pair(N - 1, 1));
        cloudQueue.add(new Pair(N - 1, 2));
    }

    void setMap(int i, int j, int value) {
        this.map[i][j] = value;
    }

    public void move(int idx, int dist) {
        this.answer = 0;
        Queue<Pair> moveCloud = new LinkedList<>();
        while (!cloudQueue.isEmpty()) {
            Pair cur = cloudQueue.poll();

            Pair direct = direction[idx];
            int assignX = cur.x + (direct.x * dist);
            int assignY = cur.y + (direct.y * dist);

            while (!isValid(assignX)) {
                assignX = change(assignX);
            }
            while (!isValid(assignY)) {
                assignY = change(assignY);
            }

            moveCloud.add(new Pair(assignX, assignY));
            map[assignX][assignY]++;
            visited[assignX][assignY] = true;
        }

        while (!moveCloud.isEmpty()) {
            Pair cur = moveCloud.poll();
            int cnt = 0;
            for (int i = 1; i <= 7; i += 2) {
                Pair direct = direction[i];
                int assignX = cur.x + direct.x;
                int assignY = cur.y + direct.y;

                if (!isValid(assignX) || !isValid(assignY) || map[assignX][assignY] < 1) {
                    continue;
                }
                cnt++;
            }
            map[cur.x][cur.y] += cnt;
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if ((map[i][j] >= 2) && !visited[i][j]) {
                    cloudQueue.add(new Pair(i, j));
                    map[i][j] -= 2;
                } else {
                    visited[i][j] = false;
                }
                answer += map[i][j];
            }
        }
    }

    boolean isValid(int x) {
        return x > 0 && x <= N;
    }

    int change(int x) {
        return x < 1 ? x + N : x - N;
    }

    int getAnswer() {
        return this.answer;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Pair[] command = new Pair[M];

        World w = new World(N);

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; ++j) {
                w.setMap(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            command[i] = new Pair(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        for (int i = 0; i < M; ++i) {
            int dir = command[i].x - 1;
            int dist = command[i].y;

            w.move(dir, dist);
        }
        System.out.print(w.getAnswer());
    }
}