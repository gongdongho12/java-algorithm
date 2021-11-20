package com.dongholab.algorithm.heapsort;

public class Solution2 {
    static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 + 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (i != largest) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    static void swap(int[] arr, int curr, int change) {
        int temp = arr[curr];
        arr[curr] = arr[change];
        arr[change] = temp;
    }
}
