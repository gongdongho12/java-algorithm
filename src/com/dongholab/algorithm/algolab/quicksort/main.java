package com.dongholab.algorithm.algolab.quicksort;

import java.io.*;
import java.util.*;

public class main {
    static int compareCnt = 0;
    static int swapCnt = 0;

    static void quickSort(int[] arr, int low, int high, boolean isLomuto) {
        if (low >= high) {
            return;
        }
        int p = isLomuto ? partitionLomuto(arr, low, high) : partitionHoare(arr, low, high);
        quickSort(arr, low, isLomuto ? p - 1 : p, isLomuto);
        quickSort(arr, p + 1, high, isLomuto);
    }

    static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
            } while (compare(pivot, arr[i]));
            do {
                j--;
            } while (compare(arr[j], pivot));

            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;
            }
        }
    }

    static int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[low];
        int j = low;
        for (int i = low + 1; i <= high; i++) {
            if (compare(pivot, arr[i])) {
                j += 1;
                swap(arr, i, j);
            }
        }
        swap(arr, j, low);
        return j;
    }

    static boolean compare(int a, int b) {
        compareCnt++;
        return a > b;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swapCnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());

            int[] arr = new int[size];
            int index = 0;
            while(st.hasMoreTokens()) {
                int val = Integer.parseInt(st.nextToken());
                arr[index++] = val;
            }

            result.clear();
            swapCnt = 0;
            compareCnt = 0;
            int[] arrCopy = arr.clone();
            quickSort(arrCopy, 0, size-1, false);
            result.add(swapCnt);
            result.add(compareCnt);

            swapCnt = 0;
            compareCnt = 0;
            quickSort(arr, 0, size-1, true);
            result.add(swapCnt);
            result.add(compareCnt);
            System.out.println(String.format("%d %d %d %d", result.get(0), result.get(2), result.get(1), result.get(3)));
        }
    }
}
