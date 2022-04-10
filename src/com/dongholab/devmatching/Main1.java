package com.dongholab.devmatching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Data {
    int start;
    int end;
    int length;

    public Data(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}

class Solution {

    public int[][] solution(int[][] dist) {
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> b.length - a.length);
        for (int i = 0; i < dist.length; i++) {
            var currentRow = dist[i];
            for (int j = 0; j < currentRow.length; j++) {
                pq.add(new Data(i, j, currentRow[j]));
            }
        }
        List<Data> line = new LinkedList<>();

        int[][] answer = {};
        return answer;
    }
}
public class Main1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{0, 5, 2, 4, 1}, {5, 0, 3, 9, 6}, {2, 3, 0, 6, 3}, {4, 9, 6, 0, 3}, {1, 6, 3, 3, 0}}));
    }
}
