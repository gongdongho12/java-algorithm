package com.dongholab.algorithm.algolab.matrixchainmultiplication;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        final int INF = Integer.MAX_VALUE / 2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int matrixCnt = Integer.parseInt(st.nextToken());
            int[] matrix = new int[matrixCnt + 1];
            int[][] dp = new int[matrixCnt + 1][matrixCnt + 1];
            for (int j = 0; j <= matrixCnt; j++) {
                matrix[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < matrixCnt; j++) {
                for (int k = 1; k <= matrixCnt - j; k++) {
                    int l = k + j;
                    if (l == k) {
                        dp[k][l] = 0;
                        continue;
                    }
                    dp[k][l] = INF;
                    for (int m = k; m < l; m++) {
                        dp[k][l] = Math.min(dp[k][l], dp[k][m] + dp[m + 1][l] + matrix[k - 1] * matrix[m] * matrix[l]);
                    }
                }
            }

            System.out.println(dp[1][matrixCnt]);
        }
    }
}