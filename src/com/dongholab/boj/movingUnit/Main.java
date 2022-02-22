package com.dongholab.boj.movingUnit;

import java.io.*;
import java.util.*;

class Pair {
    private int a;
    private int b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair)
            return this.a == ((Pair) o).getA() && this.b == ((Pair) o).getB();
        return false;
    }
}

public class Main {
    static boolean[][] map;
    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        int[][] visit = new int[N][M];
        Pair[] direction = new Pair[]{new Pair(-1, 0), new Pair(1, 0), new Pair(0, -1), new Pair(0, 1)};

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a - 1][b - 1] = true;
        }

        st = new StringTokenizer(br.readLine());
        Pair start = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        visit[start.getA()][start.getB()] = 1;
        st = new StringTokenizer(br.readLine());
        Pair end = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(start) ;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.equals(end)) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int moveA = p.getA() + direction[i].getA();
                int moveB = p.getB() + direction[i].getB();
                if (0 > moveA || moveA + A - 1 >= N || 0 > moveB || moveB + B - 1  >= M) {
                    continue;
                }
                if (visit[moveA][moveB] > 0) {
                    continue;
                }
                if (blockCheck(p)) {
                    visit[moveA][moveB] = visit[p.getA()][p.getB()] + 1;
                    queue.add(new Pair(moveA, moveB));
                }
            }
        }
        System.out.println(visit[end.getA()][end.getB()] - 1);
    }

    public static boolean blockCheck(Pair point) {
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                int a = point.getA() + i;
                int b = point.getB() + j;
                if (map[a][b]) {
                    return false;
                }
            }
        }
        return true;
    }
}
