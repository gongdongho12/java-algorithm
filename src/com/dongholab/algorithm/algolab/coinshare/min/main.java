package com.dongholab.algorithm.algolab.coinshare.min;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        final int max_value = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int j, k, N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int coinN = Integer.parseInt(st.nextToken());
            int[] coins = new int[coinN];
            for (j = 0; j < coinN; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[price + 1];
            Arrays.fill(dp, 1, price + 1, max_value);

            for (j = 0; j < coinN; j++) {
                for (k = coins[j]; k <= price; k++) {
                    dp[k] = Math.min(dp[k], dp[k - coins[j]] + 1);
                }
            }
            System.out.println(dp[price]);
        }
    }
}
