package com.dongholab.boj.factorialZeroCnt;

import java.io.*;

public class Main {
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int cnt = 1;

        int assign = getZeroCnt(cnt);
        while (assign < M) {
            cnt++;
            assign = getZeroCnt(cnt);
        }

        if (assign == M) {
            System.out.println(cnt * 5);
        } else {
            System.out.println(-1);
        }
    }

    public static int getZeroCnt(int cnt) {
        int num = 1;
        while (cnt % 5 == 0) {
            num++;
            cnt /= 5;
        }
        sum += num;
        return sum;
    }
}
