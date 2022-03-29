package com.dongholab.programmers.skict2.third;

import java.util.*;
import java.util.stream.Collectors;

class Pair {
    private int a;
    private int b;

    Pair(int a, int b) {
        if (a < b) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a) + Objects.hashCode(b);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair)
            return (this.a == ((Pair) o).getA() && this.b == ((Pair) o).getB());
        return false;
    }
}

class Solution {
    public int solution(int[][] a, int[][] b, int m) {
        Set<Pair> treeA = Arrays.stream(a).map(path -> new Pair(path[0], path[1])).collect(Collectors.toSet());
        Set<Pair> treeB = Arrays.stream(b).map(path -> new Pair(path[0], path[1])).collect(Collectors.toSet());
        Set<Pair> treeSame = Arrays.stream(b).map(path -> new Pair(path[0], path[1])).filter(current -> treeA.contains(current)).collect(Collectors.toSet());
        Set<Pair> remainA = new HashSet<>(treeA);
        Set<Pair> remainB = new HashSet<>(treeB);
        remainA.removeAll(treeSame);
        remainB.removeAll(treeSame);

        Map<String, Integer> countMap = new HashMap<>();
        remainB.forEach(pathB -> {
            remainA.forEach(pathA -> {
                String key = null;
                if (pathA.getA() == pathB.getA()) {
                    if (pathB.getB() < pathA.getB()) {
                        key = String.format("%s-%s", pathB.getB(), pathA.getB());
                    } else {
                        key = String.format("%s-%s", pathA.getB(), pathB.getB());
                    }
                    countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                }

                if (pathA.getB() == pathB.getA()) {
                    if (pathB.getB() < pathA.getA()) {
                        key = String.format("%s-%s", pathB.getB(), pathA.getA());
                    } else {
                        key = String.format("%s-%s", pathA.getA(), pathB.getB());
                    }
                    countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                }

                if (pathA.getA() == pathB.getB()) {
                    if (pathB.getA() < pathA.getB()) {
                        key = String.format("%s-%s", pathB.getA(), pathA.getB());
                    } else {
                        key = String.format("%s-%s", pathA.getB(), pathB.getA());
                    }
                    countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                }

                if (pathA.getB() == pathB.getB()) {
                    if (pathB.getA() < pathA.getA()) {
                        key = String.format("%s-%s", pathB.getA(), pathA.getA());
                    } else {
                        key = String.format("%s-%s", pathA.getA(), pathB.getA());
                    }
                    countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                }
            });
        });

        Double result = Math.ceil(Double.valueOf((countMap.size() - m) / 4.0));
        return result.intValue();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{1, 2}, {3, 1}, {2, 4}, {3, 5}}, new int[][]{{2, 1}, {4, 1}, {2, 5}, {3, 2}}, 1));
        System.out.println(solution.solution(new int[][]{{3, 4}, {7, 2}, {5, 4}, {2, 3}, {6, 5}, {1, 2}}, new int[][]{{2, 1}, {3, 6}, {1, 4}, {1, 5}, {7, 1}, {3, 2}}, 2));
    }
}
