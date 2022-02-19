package com.dongholab.boj.factorialZeroCnt;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int cnt = 1;
        int assign = getCnt(cnt);

        while (assign < M) {
            cnt++;
            assign = getCnt(cnt);
        }

        if (assign == M) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }
    }

    public static int getCnt(int cnt) {
        int num = 0, temp = cnt, sum = 0;
        while (temp % 5 == 0) {
            num++;
            temp /= 5;
        }
        for (int i = 1; i <= num; i++) {
            sum += cnt / Math.pow(5, i);
        }
        return sum;
    }
}
