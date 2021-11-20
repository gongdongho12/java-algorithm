package com.dongholab.algorithm.algolab.coinshare.share;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int j, k, N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int coinN = Integer.parseInt(st.nextToken());

            int[][] dp = new int[11][price + 1];
            dp[0][0] = 1;

            for (j = 1; j <= coinN; j++) {
                int currentCoin = Integer.parseInt(st.nextToken());
                for (k = 0; k <= price; k++) {
                    dp[j][k] = (k < currentCoin ? 0 : dp[j][k - currentCoin]) + dp[j - 1][k];
                }
            }
            System.out.println(dp[coinN][price]);
        }
    }
}
