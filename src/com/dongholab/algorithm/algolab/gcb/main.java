package com.dongholab.algorithm.algolab.gcb;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {
    static int gcd(int a, int b) {
        if (b == 0 || a == 0)
            return Math.max(a, b);
        int r = a % b;
        if (r == 0)
            return b;
        else
            return gcd(b, r);
    }

    public static void main(String[] args) throws IOException {
        Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            System.out.println(gcd(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}
