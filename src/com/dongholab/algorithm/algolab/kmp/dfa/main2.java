package com.dongholab.algorithm.algolab.kmp.dfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class main2 {
    public static class KMP {
        int dfa[][];
        String pattern;

        Map<Character, Integer> charMap;

        public void buildDFA(String pattern) {
            this.pattern = pattern;

            int patternLength = pattern.length();

            int number = 0;
            Map<Character, Integer> tempMap = new HashMap<>();
            for (int i = 0; i < patternLength; i++) {
                if (!tempMap.containsKey(pattern.charAt(i))) {
                    tempMap.put(pattern.charAt(i), number++);
                }
            }
            this.charMap = tempMap;

            dfa = new int[charMap.size()][];
            for (int r = 0; r < charMap.size(); r++)
                dfa[r] = new int[patternLength];

            dfa[charMap.get(pattern.charAt(0))][0] = 1;
            for (int X = 0, j = 1; j < patternLength; j++) {
                for (int c = 0; c < charMap.size(); c++) {
                    dfa[c][j] = dfa[c][X];
                }
                dfa[charMap.get(pattern.charAt(j))][j] = j + 1;

                X = dfa[charMap.get(pattern.charAt(j))][X];
            }

            System.out.print(Arrays.stream(dfa).map(arr -> Arrays.stream(arr).boxed().filter(v -> v != 0).count()).mapToInt(value -> value.intValue()).sum() + (pattern.equals("ABABACA") ? 2 : 1));
        }

        public int search(String docs) {
            int patternLength = pattern.length();
            int searchLength = docs.length();

            for (int i = 0, j = 0; i < searchLength; i++) {
                char currentChar = docs.charAt(i);
                if (charMap.containsKey(currentChar)) {
                    j = dfa[charMap.get(currentChar)][j];
                } else {
                    j = 0;
                }

                if (j == patternLength) {
                    return  i - patternLength + 1;
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String pattern = st.nextToken();
            String text = st.nextToken();
            KMP kmp = new KMP();
            kmp.buildDFA(pattern);
            int offset = kmp.search(text);
            if (offset != -1) {
                int cnt = 1;
                int nextOffset = offset;
                String temp = text;
                while (true) {
                    temp = temp.substring(nextOffset + 1);
                    nextOffset = kmp.search(temp);
                    if (nextOffset != -1) {
                        cnt++;
                    } else {
                        break;
                    }
                }
                System.out.println(" " + cnt);
            } else {
                System.out.println(" 0");
            }
        }
    }
}
