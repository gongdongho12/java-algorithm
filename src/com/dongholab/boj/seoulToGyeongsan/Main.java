package com.dongholab.boj.seoulToGyeongsan;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int onFootTime = Integer.parseInt(st.nextToken());
            int onFootPrice = Integer.parseInt(st.nextToken());
            int onBikeTime = Integer.parseInt(st.nextToken());
            int onBikePrice = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[0][onFootTime] = onFootPrice;
                dp[0][onBikeTime] = Math.max(dp[0][onBikeTime], onBikePrice);
            } else {
                for (int j = 0; j <= K; j++) {
                    if (dp[i - 1][j] != 0) {
                        int nextFootTime = j + onFootTime;
                        if (nextFootTime <= K) {
                            dp[i][nextFootTime] = Math.max(dp[i][nextFootTime], dp[i - 1][j] + onFootPrice);
                        }
                        int nextBikeTime = j + onBikeTime;
                        if (nextBikeTime <= K) {
                            dp[i][nextBikeTime] = Math.max(dp[i][nextBikeTime], dp[i - 1][j] + onBikePrice);
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }
}