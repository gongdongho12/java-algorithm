package com.dongholab.boj.string.string_game_2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                System.out.println("1 1");
                continue;
            }

            int[] alphabetCnt = new int[26];
            for(int j = 0; j < str.length(); j++) {
                alphabetCnt[str.charAt(j) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for(int j = 0; j < str.length(); j++) {
                if(alphabetCnt[str.charAt(j) - 'a'] < k) {
                    continue;
                }
                int count = 1;
                for(int l = j + 1; l < str.length(); l++) {
                    if(str.charAt(j) == str.charAt(l)) {
                        count++;
                    }
                    if (count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == -1) {
                System.out.println("-1");
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}
