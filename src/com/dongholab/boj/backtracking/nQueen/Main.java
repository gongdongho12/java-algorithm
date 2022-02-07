package com.dongholab.boj.backtracking.nQueen;

import java.io.*;

public class Main {
    static int N, answer;
    static int[] chessMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chessMap = new int[N];
        answer = 0;
        recursive(0);
        System.out.println(answer);
    }

    public static void recursive(int i) {
        if (i == N) {
            answer++;
            return;
        }
        for (int j = 0; j < N; j++) {
            chessMap[i] = j;
            if (check(i)) {
                recursive(i + 1);
            }
        }
    }

    public static boolean check(int col) {
        for (int i = 0; i < col; i++) {
            if (chessMap[col] == chessMap[i] || Math.abs(col - i) == Math.abs(chessMap[col] - chessMap[i])) {
                return false;
            }
        }
        return true;
    }
}
