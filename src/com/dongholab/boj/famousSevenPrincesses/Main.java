package com.dongholab.boj.famousSevenPrincesses;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

class Pair {
    private int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}

public class Main {
    static Pair[] directions;
    static boolean[] visited;
    static char[][] womanClass;
    static final int LINE_SIZE = 5;
    static int answer;

    public static void main(String[] args) throws IOException {
        directions = new Pair[]{
                new Pair(-1, 0),
                new Pair(1, 0),
                new Pair(0, -1),
                new Pair(0, 1)
        };
        visited = new boolean[LINE_SIZE * LINE_SIZE];
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        womanClass = new char[LINE_SIZE][LINE_SIZE];
        for (int i = 0; i < LINE_SIZE; i++) {
            womanClass[i] = br.readLine().toCharArray();
        }

        List<Integer> picker = IntStream.range(0, LINE_SIZE * LINE_SIZE).boxed().collect(Collectors.toList());
        combination(picker, 7).forEach(selectStudents -> bfs(selectStudents));
        System.out.println(answer);
    }

    private static <T> List<List<T>> combination(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        int length = list.size();
        List<List<T>> result = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            T current = list.get(i);
            Class<?> classType = current.getClass();
            List<List<T>> combinations = combination(List.of(list.subList(i + 1, length).toArray(size -> (T[]) Array.newInstance(classType, size))), num - 1);
            List<List<T>> attach = combinations.stream().map((combination) -> List.of(Stream.concat(Stream.of(current), combination.stream())
                    .toArray(size -> (T[]) Array.newInstance(classType, size)))
            ).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }

    public static void bfs(List<Integer> selectStudents) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[7];
        queue.add(selectStudents.get(0));

        Pair count = new Pair(1, 0);
        while (!queue.isEmpty()) {
            int selectStudent = queue.poll();
            int row = selectStudent / LINE_SIZE;
            int col = selectStudent % LINE_SIZE;
            if (womanClass[row][col] == 'S') {
                count.setB(count.getB() + 1);
            }
            for (int dir = 0; dir < 4; dir++) {
                Pair direction = directions[dir];
                for (int i = 1; i < 7; i++) {
                    if (!visited[i] && (col + direction.getA()) == (selectStudents.get(i) % LINE_SIZE) && (row + direction.getB()) == (selectStudents.get(i) / LINE_SIZE)) {
                        visited[i] = true;
                        queue.add(selectStudents.get(i));
                        count.setA(count.getA() + 1);
                    }
                }
            }
        }
        if (count.getA() == 7 && count.getB() >= 4) {
            answer++;
        }
    }
}
