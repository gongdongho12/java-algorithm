package com.dongholab.programmers.skict2.first;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] goods) {
        Map<String, Map<Integer, Set<String>>> keywordMap = new HashMap<>();
        AtomicInteger goodsSize = new AtomicInteger(0);
        Arrays.stream(goods).forEach(good -> {
            goodsSize.incrementAndGet();
            int length = good.length();
            Map<Integer, Set<String>> currentMap = new HashMap<>();
            for (int i = 1; i <= length + 1; i++) {
                for (int j = 0; j <= length - i; j++) {
                    Set<String> currentSet = currentMap.getOrDefault(i, new HashSet<>());
                    currentSet.add(good.substring(j, j + i));
                    currentMap.put(i, currentSet);
                }
                keywordMap.put(good, currentMap);
            }
        });
        keywordMap.forEach((keyword, currentSet) -> {
            currentSet.forEach((size, querySet) -> {
                Set<String> duplicateAtSize = new HashSet<>();
                keywordMap.forEach((otherKeyowrd, otherSetWithLength) -> {
                    if (!otherKeyowrd.equals(keyword) && otherSetWithLength.containsKey(size)) {
                        Set<String> otherSet = otherSetWithLength.get(size);
                        Set<String> currentDuplicated = querySet.stream().filter(query -> otherSet.contains(query)).collect(Collectors.toSet());
                        duplicateAtSize.addAll(currentDuplicated);
                    }
                });
                keywordMap.forEach((otherKeyowrd, otherSetWithLength) -> {
                    if (otherSetWithLength.containsKey(size)) {
                        Set<String> otherSet = otherSetWithLength.get(size);
                        otherSet.removeAll(duplicateAtSize);
                    }
                });
            });
        });


        return Arrays.stream(goods).map(good -> {
            Map<Integer, Set<String>> goodSetWithSize = keywordMap.get(good);
            for(Map.Entry<Integer, Set<String>> sizeWithSet: goodSetWithSize.entrySet()) {
                Set<String> querySet = sizeWithSet.getValue();
                if (querySet.size() > 0) {
                    return querySet.stream().sorted().collect(Collectors.joining(" "));
                }
            }
            return "None";
        }).toArray(String[]::new);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[]{"pencil", "cilicon", "contrabase", "picturelist"})));
    }
}
