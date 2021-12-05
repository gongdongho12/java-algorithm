package com.dongholab.algorithm.algolab.kmp.fail;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class main {
    static int[] failure(String pattern) {
        int textLength = pattern.length();
        int[] F = new int[textLength];
        int j = 0;
        for (int i = 1; i < textLength; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = F[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) F[i] = ++j;
        }
        return F;
    }

    static int kmp(String text, String pattern) {
        int result = 0;
        int[] F = failure(pattern);
        System.out.println(Arrays.stream(F).boxed().map(String::valueOf).collect(Collectors.joining(" ")));

        int j = 0;
        int textLength = text.length();
        int patternLength = pattern.length();
        for(int i = 0; i < textLength; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {             // not matching
                j = F[j - 1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {                         // matching
                if (j == patternLength - 1){
                    j = F[j];
                    result++;
                }
                else {
                    j++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String pattern = st.nextToken();
            String text = st.nextToken();
            System.out.println(kmp(text, pattern));
        }
    }
}
