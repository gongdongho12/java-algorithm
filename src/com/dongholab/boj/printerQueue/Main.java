package com.dongholab.boj.printerQueue;

/*
    백준 프린터큐 https://www.acmicpc.net/problem/1966
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static class Pair<T> {
        private T value;
        private int index;
        Pair(T value, int index) {
            this.value = value;
            this.index = index;
        }

        public T getValue() {
            return this.value;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static void main(String[] args) throws IOException {
        Queue<Pair<Integer>> printerQueue = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 일단 갯수는 아래에서 공백으로 split하기때문에 의미가 없으므로 무시
            st.nextToken();
            int select = Integer.parseInt(st.nextToken());
            AtomicInteger index = new AtomicInteger();
            List<Pair<Integer>> docList = Arrays.stream(br.readLine().split(" ")).map((v) -> new Pair<Integer>(Integer.parseInt(v), index.getAndIncrement())).collect(Collectors.toList());
            printerQueue.addAll(docList);

            int printCnt = 0;
            boolean check;
            Pair<Integer> print;

            do {
                check = true;
                print = printerQueue.poll();
                for (Pair<Integer> afterPrint: printerQueue) {
                    if (afterPrint.getValue() > print.getValue()) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    printCnt++;
                } else {
                    printerQueue.add(print);
                }
            } while (!printerQueue.isEmpty() && !(check && print.getIndex() == select));

            System.out.println(printCnt);
            printerQueue.clear();
        }
    }
}
