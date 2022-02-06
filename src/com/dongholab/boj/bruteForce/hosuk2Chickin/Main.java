package com.dongholab.boj.bruteForce.hosuk2Chickin;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

public class Main {
    final static int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i != j) {
                    map[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;
            map[b][a] = 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int current = map[i][j];
                    int diff = Math.min(current, map[i][k] + map[k][j]);
                    map[i][j] = diff;
                    map[j][i] = diff;
                }
            }
        }

        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();
        AtomicInteger answer = new AtomicInteger(INF);
        Set<Set<Integer>> selectIndex = listToSet(permutation(IntStream.range(0, N).boxed().collect(Collectors.toList()), 2));
        selectIndex.forEach(chickHouses -> {
            int temp = 0;
            Integer[] chickHouseList = chickHouses.stream().sorted().toArray(Integer[]::new);
            for (int i = 0; i < N; i++) {
                if (!chickHouses.contains(i)) {
                    temp += Math.min(map[chickHouseList[0]][i], map[chickHouseList[1]][i]);
                }
            }
            if (temp < answer.get()) {
                a.set(chickHouseList[0]);
                b.set(chickHouseList[1]);
                answer.set(temp);
            }
        });
        System.out.println(String.format("%d %d %d", a.get() + 1, b.get() + 1, answer.get() * 2));
    }

    private static <T> List<List<T>> permutation(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        List<List<T>> result = new ArrayList<>();

        int length = list.size();
        for (int i = 0; i < length; i++) {
            T current = list.get(i);
            List<T> rest = new ArrayList<>(list.subList(0, i));
            rest.addAll(list.subList(i + 1, length));
            List<List<T>> permutations = permutation(rest, num - 1);
            List<List<T>> attach = permutations.stream().map((permutation) -> {
                List<T> append = new ArrayList<>(Arrays.asList(current));
                append.addAll(permutation);
                return append;
            }).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }

    private static <T> Set<Set<T>> listToSet(List<List<T>> list) {
        Set<Set<T>> set = new HashSet<>();
        for (List<T> l: list) {
            set.add(new HashSet<>(l));
        }
        return set;
    }
}
