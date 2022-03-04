package com.dongholab.programmers.raceTrackConstruction;

import java.util.*;

class Pair {
    private int a, b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public Pair add(Pair other) {
        return new Pair(this.a + other.a, this.b + other.b);
    }
}


class Car {
    Pair position;
    int dir, cost;

    Car(Pair position, int dir, int cost) {
        this.position = position;
        this.dir = dir;
        this.cost = cost;
    }
}

class Solution {
    static int N;
    static int[][] dp;
    static Pair[] directions;

    public int solution(int[][] board) {
        N = board.length;
        dp = new int[N][N];
        directions = new Pair[]{new Pair(0, -1), new Pair(0, 1), new Pair(-1, 0), new Pair(1, 0)};
        Pair start = new Pair(0, 0);
        return Math.min(bfs(start, 1, board), bfs(start, 3, board)) * 100;
    }

    public int bfs(Pair position, int dir, int[][] board) {
        Queue<Car> queue = new LinkedList<>();
        queue.add(new Car(position, dir, 0));
        dp[position.getA()][position.getB()] = 1;
        int cost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Car car = queue.poll();

            Pair currentPos = car.position;
            int currentDir = car.dir;
            int currentCost = car.cost;

            if (currentPos.getA() == N - 1 && currentPos.getB() == N - 1) {
                if (cost > currentCost) {
                    cost = currentCost;
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    Pair nextPos = currentPos.add(directions[i]);
                    if (!(nextPos.getA() >= N || nextPos.getA() < 0 || nextPos.getB() >= N || nextPos.getB() < 0 || board[nextPos.getA()][nextPos.getB()] == 1)) {
                        int nextCost = currentCost + (currentDir == i ? 1 : 6);

                        if (dp[nextPos.getA()][nextPos.getB()] == 0) {
                            dp[nextPos.getA()][nextPos.getB()] = nextCost;
                            queue.add(new Car(nextPos, i, nextCost));
                        } else if (dp[nextPos.getA()][nextPos.getB()] >= nextCost) {
                            dp[nextPos.getA()][nextPos.getB()] = nextCost;
                            queue.add(new Car(nextPos, i, nextCost));
                        }
                    }
                }
            }
        }

        return cost;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }
}
