package com.dongholab.boj.dp1.coin1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> coinList = new ArrayList<>();
        int[] dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coinList.add(coin);
            for (int j = coin; j <= K; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println(dp[K]);
    }
}
