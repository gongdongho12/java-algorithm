package com.dongholab.algorithm.matrixchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static long [][] a;
    static long [][] dp;
    static long max_value = Long.MAX_VALUE;

    public static long solve(int start, int end) {
        if(start == end) return 0;
        if(dp[start][end]!= max_value) {
            return dp[start][end];
        }
        for(int i = start; i < end; i++) {
            long cost = solve(start, i) + solve(i + 1, end)+ a[start][0] * a[i][1] * a[end][1];
            dp[start][end] = Math.min(dp[start][end], cost);
        }
        return dp[start][end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        a = new long[N][2];
        dp = new long[N][N];

        a[0][0] = Long.parseLong(br.readLine());
        a[0][1] = Long.parseLong(br.readLine());
        Arrays.fill(dp[0], max_value);
        for (int i = 1; i < N; i++) {
            a[i][0] = a[i - 1][1];
            a[i][1] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[i], max_value);
        }
        System.out.println(solve(0, N - 1));
    }
}
