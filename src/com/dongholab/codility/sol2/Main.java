package com.dongholab.codility.sol2;

import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(String S) {
        int[] intArr = S.chars().map(c -> c - '0').toArray();
        int sum = Arrays.stream(intArr).sum();
        return (sum % 3 == 0 ? 1 : 0) + IntStream.range(0, intArr.length).map(index -> {
            int current = intArr[index];
            int currentIgnoreSum = sum - current;
            int cnt = 0;
            for (int k = 0; k <= 9; k++) {
                if (k != current && (currentIgnoreSum + k) % 3 == 0) {
                    cnt++;
                }
            }
            return cnt;
        }).sum();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("23"));
        System.out.println(solution.solution("0081"));
        System.out.println(solution.solution("022"));
    }
}
