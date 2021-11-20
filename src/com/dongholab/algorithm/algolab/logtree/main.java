package com.dongholab.algorithm.algolab.logtree;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        final int maxValue = Integer.MAX_VALUE / 2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken()) + 1;
            int[][] dp = new int[k][k + 1];
            st = new StringTokenizer(br.readLine());
            int[] c = new int[k + 1];
            for (int j = 1; j < k; j++) {
                c[j] = Integer.parseInt(st.nextToken());
            }
            c[k] = L;

            for (int j = 2; j <= k; ++j) {
                for (int l = 0; l <= k - j; ++l) {
                    int m = l + j, min = maxValue;
                    for (int s = l + 1; s < m; ++s) {
                        min = Math.min(min, dp[l][s] + dp[s][m]);
                    }
                    if (min != maxValue) {
                        dp[l][m] = min + c[m] - c[l];
                    }
                }
            }

            System.out.println(dp[0][k]);
        }
    }
}
