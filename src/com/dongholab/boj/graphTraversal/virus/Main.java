package com.dongholab.boj.graphTraversal.virus;

/*
    백준 바이러스 https://www.acmicpc.net/problem/2606
 */

import java.io.*;
import java.util.*;

public class Main {
    public static class Pair {
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
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCnt = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        boolean[] infectionChecker = new boolean[nodeCnt];
        infectionChecker[0] = true;

        Deque<Pair> deque = new LinkedList<>();

        Pair p;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            p = new Pair(a, b);
            if (a == 1 || b == 1) {
                deque.addFirst(p);
            } else {
                deque.addLast(p);
            }
        }

        int answer = 0, afterCnt = 0;
        boolean update = false;
        while (!deque.isEmpty()) {
            if (afterCnt == deque.size()) {
                if (update) {
                    afterCnt = 0;
                    update = false;
                } else {
                    break;
                }
            }

            p = deque.pollFirst();
            int a = p.getA() - 1;
            int b = p.getB() - 1;
            if (infectionChecker[a] || infectionChecker[b]) {
                if (!(infectionChecker[a] && infectionChecker[b])) {
                    infectionChecker[a] = true;
                    infectionChecker[b] = true;
                    answer++;
                    update = true;
                }
            } else {
                afterCnt++;
                deque.addLast(p);
            }
        }
        System.out.println(answer);
    }
}
