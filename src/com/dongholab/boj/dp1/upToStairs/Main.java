package com.dongholab.boj.dp1.upToStairs;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static int getStair(List<Integer> stairs, int i) {
        try {
            Integer temp = stairs.get(i);
            return temp == null ? 0 : temp;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        List<Integer> stairs = IntStream.range(0, N).mapToObj(i -> {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        dp[0] = stairs.get(0);
        if (N >= 2) {
            dp[1] = Math.max(getStair(stairs, 0) + getStair(stairs, 1), getStair(stairs, 1));
            if (N >= 3) {
                dp[2] = Math.max(getStair(stairs, 0) + getStair(stairs, 2), getStair(stairs, 1) + getStair(stairs, 2));
                for (int i = 3; i < N; i++) {
                    dp[i] = Math.max(dp[i - 2] + getStair(stairs, i), dp[i - 3] + getStair(stairs, i) + getStair(stairs, i - 1));
                }
            }
        }
        System.out.println(dp[N - 1]);
    }
}
