package com.dongholab.algorithm.heapsort;

class Solution {
    static void sort(int arr[], int k) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= k; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    static void sort2(int arr[], int k) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify2(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify2(arr, i, 0);
        }
    }

    static void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    static void heapify2(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify2(arr, n, largest);
        }
    }

    static void swap(int arr[], int currentIndex, int changeIndex) {
        int temp = arr[currentIndex];
        arr[currentIndex] = arr[changeIndex];
        arr[changeIndex] = temp;
    }

    public int[] solution(int[] a, int k) {
        sort(a, k);
        return a;
    }
}