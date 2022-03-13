package com.dongholab.codility.sol1;

import java.util.*;

class Solution {
    public int solution(String S) {
        int length = S.length();
        Map<String, List<Integer>> startTokenMap = new HashMap<>();
        for (int i = 0; i < length - 1; i++) {
            String token = S.substring(i, i + 2);
            List<Integer> indexList = startTokenMap.getOrDefault(token, new LinkedList<>());
            indexList.add(i);
            startTokenMap.put(token, indexList);
        }

        int answer = -1;
        for(Map.Entry<String, List<Integer>> entry : startTokenMap.entrySet()) {
            List<Integer> value = entry.getValue();
            int size = value.size();
            if (size >= 2) {
                int start = value.get(0);
                int end = value.get(size - 1);
                answer = Math.max(answer, end - start);
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("aakmaakmakda"));
        System.out.println(solution.solution("aaa"));
        System.out.println(solution.solution("akbabakb"));
        System.out.println(solution.solution("codility"));
    }
}
