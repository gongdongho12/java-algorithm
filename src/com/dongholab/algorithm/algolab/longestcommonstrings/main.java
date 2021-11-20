package com.dongholab.algorithm.algolab.longestcommonstrings;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            int s1Length = s1.length();
            int s2Length = s2.length();

            int[][] dp = new int[s1Length + 1][s2Length + 1];
            for (int j = 0; j < s1Length; j++) {
                for (int k = 0; k < s2Length; k++) {
                    if (s1.charAt(j) == s2.charAt(k)) {
                        dp[j + 1][k + 1] = dp[j][k] + 1;
                    } else {
                        dp[j + 1][k + 1] = Math.max(dp[j][k + 1], dp[j + 1][k]);
                    }
                }
            }

            System.out.println(dp[s1Length][s2Length]);
        }
    }
}