package com.dongholab.boj.flipACoin;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] coinMap = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                coinMap[i][j] = str.charAt(j) == 'H';
            }
        }

        int answer = N * N;
        for (int bit = 0; bit < (1 << N); bit++) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int frontCnt = 0;
                for (int j = 0; j < N; j++) {
                    boolean cur = coinMap[i][j];
                    if ((bit & (1 << j)) != 0) {
                        cur = !cur;
                    }
                    if (!cur) {
                        frontCnt++;
                    }
                }
                sum += Math.min(frontCnt, N - frontCnt);
            }
            answer = Math.min(answer, sum);
        }
        System.out.println(answer);
    }
}