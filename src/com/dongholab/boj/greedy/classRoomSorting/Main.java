package com.dongholab.boj.greedy.classRoomSorting;

/*
    백준: 강의실 배정
 */

import java.io.*;
import java.util.*;

class Pair {
    int start, end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Comparator<Pair> endTimeComparator = Comparator.comparingInt(Pair::getEnd);
        Comparator<Pair> timeTableComparator = (a, b) -> {
            int diff = a.getStart() - b.getStart();
            if (diff == 0) {
                return endTimeComparator.compare(a, b);
            } else {
                return diff;
            }
        };

        PriorityQueue<Pair> startTimePriorityQueue = new PriorityQueue<>(timeTableComparator);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Pair time = new Pair(start, end);
            startTimePriorityQueue.add(time);
        }

        PriorityQueue<Pair> assignTimePriorityQueue = new PriorityQueue<>(endTimeComparator);
        assignTimePriorityQueue.add(startTimePriorityQueue.poll());
        while (!startTimePriorityQueue.isEmpty()) {
            Pair time = startTimePriorityQueue.poll();
            if (assignTimePriorityQueue.peek().getEnd() <= time.getStart()) {
                assignTimePriorityQueue.poll();
            }
            assignTimePriorityQueue.add(time);
        }

        System.out.println(assignTimePriorityQueue.size());
    }
}
