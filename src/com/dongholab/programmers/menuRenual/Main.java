package com.dongholab.programmers.menuRenual;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new LinkedList<>();
        for (int courseSize : course) {
            Map<String, Integer> orderMap = new HashMap<>();
            List<List<Character>> orderCombinations = new LinkedList<>();

            for (String order : orders) {
                List<Character> characterList = order.chars().sorted().mapToObj(e -> (char) e).collect(Collectors.toList());
                orderCombinations.addAll(combination(characterList, courseSize));
            }

            for (List<Character> orderCombination : orderCombinations) {
                String orderStr = orderCombination.stream().map(String::valueOf).collect(Collectors.joining());
                orderMap.put(orderStr, orderMap.getOrDefault(orderStr, 0) + 1);
            }

            OptionalInt maxValue = orderMap.values().stream().mapToInt(v -> v).max();
            orderMap.forEach((order, value) -> {
                if (value >= 2 && value == maxValue.getAsInt()) {
                    answer.add(order);
                }
            });
        }
        return answer.stream().sorted().toArray(size -> (String[]) Array.newInstance(String.class, size));
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
        System.out.println(Arrays.toString(solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
}
