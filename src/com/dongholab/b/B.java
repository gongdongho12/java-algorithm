package com.dongholab.b;

import java.util.*;
import java.util.stream.*;

class Solution {
    public static int minSwapCnt;

    public int solution(int[] numbers, int k) {
        minSwapCnt = Integer.MAX_VALUE / 2;
        int length = numbers.length;
        List<Integer> init = Arrays.stream(numbers).boxed().collect(Collectors.toList());
//        System.out.println("init: " + init);
        IntStream fullCheckStream = permutation(init, length).stream().filter(list -> {
            for (int i = 1; i < length; i++) {
                if (Math.abs(list.get(i - 1) - list.get(i)) > k) {
                    return false;
                }
            }
            return true;
        }).mapToInt(list -> {
            int cnt = 0;
            List<Integer> newList = new ArrayList<>();
            List<Integer> originalList = new ArrayList<>();
//            System.out.println("list: " + list);
            for (int i = 0; i < length; i++) {
                int current = list.get(i);
                int original = init.get(i);
                if (current != init.get(i)) {
                    cnt++;
                    newList.add(current);
                    originalList.add(original);
                }
            }
//            System.out.println("newList: " + newList + ", cnt: " + cnt);
//            System.out.println("oriList: " + originalList);
            int swapCnt = swapCount(newList, originalList, cnt);
//            System.out.println("swapCnt: " + swapCnt);
            if (minSwapCnt > swapCnt) {
                minSwapCnt = swapCnt;
            }
            return swapCnt;
        });
        try {
            return fullCheckStream.min().getAsInt();
        } catch (NoSuchElementException nee) {
            return -1;
        }
    }

    private static int swapCount(List<Integer> newList, List<Integer> oriList, int N) {
        int cnt = 0;
        int swapPoint = N - 1;
        for (int i = 0; i < N; i++) {
            boolean doubleSwap = false;
            for (int j = 0; j < N; j++) {
                if (newList.get(i) == oriList.get(j) && newList.get(j) == oriList.get(i)) {
                    doubleSwap = true;
                    break;
                }
            }
            cnt++;

            if (cnt >= minSwapCnt) {
                return minSwapCnt;
            }

            if (doubleSwap) {
                swapPoint-= 2;
            } else {
                swapPoint--;
            }

            if (swapPoint <= 0) {
                return cnt;
            }
        }
        return cnt;
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
}

public class B {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{10, 40, 30, 20}, 20) == 1);
        System.out.println(sol.solution(new int[]{3, 7, 2, 8, 6, 4, 5, 1}, 3) == 2);
        System.out.println(sol.solution(new int[]{3, 7, 2, 8, 6, 4, 5, 1}, 1) == 5);
    }
}
