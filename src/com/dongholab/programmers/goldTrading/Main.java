package com.dongholab.programmers.goldTrading;

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

class Solution {
    public int solution(int[] gold_prices) {
        int length = gold_prices.length;
        int maxCheckable = length / 2;
        List<Pair> buySellIndexAble = new LinkedList<>();
        // 판매가 구매보다 큰 모든 날을 List에 담는다
        for (int i = 0; i < length; i++) {
            int buy = gold_prices[i];
            for (int j = i + 1; j < length; j++) {
                int sell = gold_prices[j];
                if (sell - buy > 0) {
                    buySellIndexAble.add(new Pair(i, j));
                }
            }
        }
        return IntStream.rangeClosed(1, maxCheckable).map(i -> {
            // 위에서 만든 pair의 1부터 최대 중첩가능한 수인 maxCheckable(포함)한 수로 조합한 리스트만을 뽑습니다
            OptionalInt max = combination(buySellIndexAble, i).stream().filter(pairList -> {
                int pairLength = pairList.size();
                List<Integer> buyIndex = new LinkedList<>();
                List<Integer> sellIndex = new LinkedList<>();
                for (int j = 0; j < pairLength; j++) {
                    Pair current = pairList.get(j);
                    buyIndex.add(current.getA());
                    sellIndex.add(current.getB());
                }
                if (IntStream.range(0, pairLength).filter(j -> {
                    Pair buyWithSell = pairList.get(j);
                    // 구매일이 판매일이나 판매 다음일인지 체크
                    // 구매나 판매가 겹치는 날이 하나라도 있는지 체크
                    // 만약 아래 조건에 하나라도 만족하면 제외
                    return sellIndex.contains(buyWithSell.getA()) || sellIndex.contains(buyWithSell.getA() - 1) || (IntStream.range(0, pairLength).filter(k -> k != j && (buyIndex.get(k) == buyWithSell.getA() || sellIndex.get(k) == buyWithSell.getB())).findFirst().isPresent());
                }).findFirst().isPresent()) {
                    return false;
                }
                return true;
            }).mapToInt(pairList -> {
                // i개 조합의 최댓값
                int maxVal = pairList.stream().mapToInt(pair -> gold_prices[pair.getB()] - gold_prices[pair.getA()]).sum();
                return maxVal;
            }).max();
            // 모든 조합의 최댓값이 있다면 최댓값 리턴
            return max.isPresent() ? max.getAsInt() : 0;
        }).max().getAsInt();
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
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{2, 5, 1, 3, 4}));
        System.out.println(solution.solution(new int[]{7, 2, 5, 6, 1, 4, 2, 8}));
        System.out.println(solution.solution(new int[]{1, 2, 3, 4, 5, 6 ,7 ,8}));
    }
}
