package com.dongholab.boj.fileCombine;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int fileLength = Integer.parseInt(br.readLine());
            int[] sum = new int[fileLength + 1];
            AtomicInteger atomicIndex = new AtomicInteger();
            Arrays.stream(br.readLine().split(" ")).forEach(v -> {
                int j = atomicIndex.getAndIncrement();
                sum[j + 1] = sum[j] + Integer.valueOf(v);
            });

            int[][] dp = new int[fileLength + 1][fileLength + 1];
            for (int j = 0; j < fileLength; j++) {
                for (int k = 1; k < fileLength - j; k++) {
                    int l = k + j + 1;
                    dp[k][l] = Integer.MAX_VALUE / 2;
                    for (int m = k; m < l; m++) {
                        dp[k][l] = Math.min(dp[k][l], dp[k][m] + dp[m + 1][l]);
                    }
                    dp[k][l] += sum[l] - sum[k - 1];
                }
            }
            System.out.println(dp[1][fileLength]);
        }
    }
}
