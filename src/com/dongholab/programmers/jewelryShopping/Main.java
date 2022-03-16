package com.dongholab.programmers.jewelryShopping;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(gems));
        int gemKindsCnt = set.size();

        Queue<String> queue = new LinkedList<>();
        int len = Integer.MAX_VALUE, start = 0, idx = 0;

        for (int i = 0; i < gems.length; i++) {
            String currentGem = gems[i];
            map.put(currentGem, map.getOrDefault(currentGem, 0) + 1);
            queue.add(currentGem);

            while (map.get(queue.peek()) > 1) {
                map.put(queue.peek(), map.get(queue.poll()) - 1);
                idx++;
            }

            if (map.size() == gemKindsCnt && len > (i - idx)) {
                len = i - idx;
                start = idx + 1;
            }
        }
        return new int[]{start, start + len};
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"AA", "AB", "AC", "AA", "AC"})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"XYZ", "XYZ", "XYZ"})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
    }
}