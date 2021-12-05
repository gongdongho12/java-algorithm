package com.dongholab.algorithm.algolab.kmp.dfa;

import java.io.*;
import java.util.*;

public class main {
    public static class KMP_DFA {
        int dfa[][];
        String pattern;
        int patternLength;
        Map<Character, Integer> charMap;

        public KMP_DFA(String pattern) {
            this.pattern = pattern;
            this.patternLength = pattern.length();

            int number = 0;
            this.charMap = new HashMap<>();
            for (int i = 0; i < patternLength; i++) {
                if (!this.charMap.containsKey(pattern.charAt(i))) {
                    this.charMap.put(pattern.charAt(i), number++);
                }
            }
            int charMapSize = number + 1;

            dfa = new int[charMapSize][];
            for (int r = 0; r < charMapSize; r++) {
                dfa[r] = new int[patternLength + 1];
            }

            dfa[charMap.get(pattern.charAt(0))][0] = 1;
            int X = 0;
            for (int j = 1; j < patternLength; j++) {
                for (int c = 0; c < charMapSize; c++) {
                    dfa[c][j] = dfa[c][X];
                }
                dfa[charMap.get(pattern.charAt(j))][j] = j + 1;
                X = dfa[charMap.get(pattern.charAt(j))][X];
            }

            for (int c = 0; c < charMapSize; c++) {
                dfa[c][patternLength] = dfa[c][X];
            }
        }

        public long stateCounts() {
            return Arrays.stream(dfa).map(states -> Arrays.stream(states).boxed().filter(state -> state > 0).count()).mapToLong(value -> value).sum();
        }

        public int search(String text) {
            int textLength = text.length();

            int match = 0;
            for (int i = 0, j = 0; i < textLength && j <= patternLength; i++) {
                j = dfa[charMap.get(text.charAt(i))][j];
                if (j == patternLength) {
                    match++;
                }
            }

            return match;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String pattern = st.nextToken();
            String text = st.nextToken();
            KMP_DFA kmpDfa = new KMP_DFA(pattern);
            System.out.println(kmpDfa.stateCounts() + " " + kmpDfa.search(text));
        }
    }
}
