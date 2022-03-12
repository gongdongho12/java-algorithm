package com.dongholab.programmers.skict.second;

class Pair {
    private int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void add(Pair pair) {
        this.a += pair.getA();
        this.b += pair.getB();
    }
}

class Solution {
    int[][] answer;
    int n, max;

    Pair[] directions;

    public int[][] solution(int n, boolean clockwise) {
        this.answer = new int[n][n];
        this.n = n;
        this.max = n * n / 4;
        if (clockwise) {
            directions = new Pair[]{
                    new Pair(0, 1),
                    new Pair(1, 0),
                    new Pair(0, -1),
                    new Pair(-1, 0)
            };
            draw(new Pair(0, 0), 0);
            draw(new Pair(0, n - 1), 1);
            draw(new Pair(n - 1, n - 1), 2);
            draw(new Pair(n - 1, 0), 3);
        } else {
            directions = new Pair[]{
                    new Pair(1, 0),
                    new Pair(0, 1),
                    new Pair(-1, 0),
                    new Pair(0, -1)
            };
            draw(new Pair(0, 0), 0);
            draw(new Pair(n - 1, 0), 1);
            draw(new Pair(n - 1, n - 1), 2);
            draw(new Pair(0, n - 1), 3);
        }
        if (n % 2 != 0) {
            answer[n / 2][n / 2] = this.max + 1;
        }
        return answer;
    }

    public void draw(Pair point, int dirDiff) {
        int cnt = 0;
        int tempN = n;
        answer[point.getA()][point.getB()] = ++cnt;
        Pair direction = null;
        for (int dir = 0; dir < 4; dir++) {
            direction = directions[(dir + dirDiff) % directions.length];
            tempN -= dir;
            for (int i = 0; i < tempN - 2; i++) {
                point.add(direction);
                answer[point.getA()][point.getB()] = ++cnt;
            }
        }

        for (int i = 0; i < max - cnt; i++) {
            if ( direction != null) {
                point.add(direction);
                answer[point.getA()][point.getB()] = ++cnt;
            }
        }
    }
}

public class Second {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, true));
        System.out.println(solution.solution(6, false));
        System.out.println(solution.solution(9, false));
    }
}
