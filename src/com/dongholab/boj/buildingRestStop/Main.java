package com.dongholab.boj.buildingRestStop;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        Set<Integer> divider = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            divider.add(Integer.parseInt(st.nextToken()));
        }
        divider.add(right);
        int left = 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            AtomicInteger prev = new AtomicInteger(0);
            int buildableCnt = divider.stream().mapToInt(div -> (div - prev.getAndSet(div) - 1) / mid).sum();

            if(buildableCnt > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
}