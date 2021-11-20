package com.dongholab.algorithm.algolab.hanoitop;

import java.io.*;
import java.math.*;
import java.util.*;

public class main {
    static void hanoiMove(int start, int via, int target, int n, BigInteger k) {
        if (!(n <= 0 || k.compareTo(new BigInteger("0")) <= 0 || k.compareTo(new BigInteger("2").pow(n)) == 1)) {
            int nminusone = n - 1;
            int compareValue = k.compareTo(new BigInteger("2").pow(nminusone));
            if (compareValue == 0) {
                System.out.println(String.format("%d %d", start, target));
            } else if (compareValue == -1) {
                hanoiMove(start, target, via, nminusone, k);
            } else{
                hanoiMove(via, start, target, nminusone, k.subtract(new BigInteger("2").pow(nminusone)));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            hanoiMove(1, 2, 3, start, new BigInteger(st.nextToken()));
        }
    }
}
