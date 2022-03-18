package com.dongholab.boj.dna;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineArr = br.readLine().split(" ");
        int N = Integer.parseInt(lineArr[0]);
        int M = Integer.parseInt(lineArr[1]);

        String[] DNA = new String[N];
        for (int i = 0; i < N; i++) {
            DNA[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < M; i++) {
            int[] cnt = new int[4];
            for (int j = 0; j < N; j++) {
                char ch = DNA[j].charAt(i);
                switch (ch) {
                    case 'A':
                        cnt[0]++;
                        break;
                    case 'C':
                        cnt[1]++;
                        break;
                    case 'G':
                        cnt[2]++;
                        break;
                    case 'T':
                        cnt[3]++;
                        break;
                }

            }

            int dna = 0;
            int max = 0;
            for (int j = 0; j < 4; j++) {
                if (cnt[j] > max || (cnt[j] == max && j < dna)) {
                    max = cnt[j];
                    dna = j;
                }
            }

            switch (dna) {
                case 0:
                    sb.append('A');
                    break;
                case 1:
                    sb.append('C');
                    break;
                case 2:
                    sb.append('G');
                    break;
                case 3:
                    sb.append('T');
                    break;
            }
            sum += N - max;
        }
        System.out.println(sb);
        System.out.println(sum);
    }
}