package com.dongholab.boj.simulation.castleDiffence;

/*
    백준-캐슬디펜스
 */

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Castle {
    boolean[][] map;
    int range = 1, N, M, kill = 0;
    List<Integer> achers;

    Castle(boolean[][] map, int N, int M, int range, List<Integer> achers) {
        this.map = map;
        this.range = range;
        this.N = N;
        this.M = M;
        this.achers = achers;
    }

    public void attack() {
        int castle = N;

        while (castle > 0) {
            ArrayList<Integer[]> enemy = new ArrayList<>();
            for (int a = 0; a < achers.size(); a++) {
                int min = Integer.MAX_VALUE;
                int[] pos = new int[2];
                for (int j = 0; j < M; j++) {
                    for (int i = castle - 1; i >= 0; i--) {
                        int diff = Math.abs(castle - i) + Math.abs(achers.get(a) - j);

                        if (map[i][j] && diff <= range) {
                            if (diff < min) {
                                min = diff;
                                pos[0] = i;
                                pos[1] = j;
                            }
                            break;
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    enemy.add(new Integer[]{pos[0], pos[1]});
                }
            }
            for (int i = 0; i < enemy.size(); i++) {
                if (map[enemy.get(i)[0]][enemy.get(i)[1]]) {
                    kill += 1;
                    map[enemy.get(i)[0]][enemy.get(i)[1]] = false;
                }
            }
            castle -= 1;
        }
    }

    public int getKill() {
        return this.kill;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int range = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            boolean[] boolArr = new boolean[M];
            for (int j = 0; j < M; j++) {
                boolArr[j] = st.nextToken().equals("1");
            }
            map[i] = boolArr;
        }
        Set<Set<Integer>> acherList = listToSet(permutation(IntStream.range(0, M).boxed().collect(Collectors.toList()), 3));

        System.out.println(acherList.stream().mapToInt(achers -> {
            Castle castle = new Castle(copyMap(map), N, M, range, new ArrayList<>(achers));
            castle.attack();
            return castle.getKill();
        }).max().getAsInt());
    }

    private static boolean[][] copyMap(boolean[][] map) {
        int N = map.length;
        int M = 0;
        try {
            M = map[0].length;
        } catch (Exception e) {}
        boolean[][] tempMap = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, M);
        }
        return tempMap;
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