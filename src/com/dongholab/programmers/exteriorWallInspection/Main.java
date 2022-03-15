package com.dongholab.programmers.exteriorWallInspection;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int weekLength = weak.length;
        int distLength = dist.length;

        int[] spreadWeak = new int[weekLength * 2 - 1];
        for (int i = 0; i < weekLength; i++) {
            spreadWeak[i] = weak[i];
        }
        for (int i = 0; i < weekLength - 1; i++) {
            spreadWeak[i + weekLength] = weak[i] + n;
        }

        AtomicBoolean isEnd = new AtomicBoolean(false);
        AtomicInteger answer = new AtomicInteger();
        IntStream.rangeClosed(1, distLength).forEach(cnt -> permutation(Arrays.stream(dist).boxed().collect(Collectors.toList()), cnt).stream().forEach(perm -> {
                    if (!isEnd.get()) {
                        outer:
                        for (int i = 0; i < weekLength; i++) {
                            int start = i;
                            int check = 0;
                            for (int j = i; j < i + weekLength; j++) {
                                if (spreadWeak[j] - spreadWeak[start] > perm.get(check)) {
                                    start = j;
                                    check++;
                                }
                                if (check == cnt) {
                                    continue outer;
                                }
                            }
                            answer.set(cnt);
                            isEnd.set(true);
                        }
                    }
                })
        );
        return isEnd.get() ? answer.get() : -1;
    }

    private static <T> List<List<T>> permutation(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        List<List<T>> result = new LinkedList<>();

        int length = list.size();
        for (int i = 0; i < length; i++) {
            T current = list.get(i);
            Class<?> classType = current.getClass();
            List<List<T>> permutations = permutation(
                    List.of(Stream.concat(list.subList(0, i).stream(), list.subList(i + 1, length).stream()).toArray(size -> (T[]) Array.newInstance(classType, size))),
                    num - 1
            );
            List<List<T>> attach = permutations.stream().map((permutation) -> List.of(Stream.concat(Stream.of(current), permutation.stream())
                    .toArray(size -> (T[]) Array.newInstance(classType, size)))
            ).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}
