package com.dongholab.scatterlab.sol3;

import java.util.*;

public class Solution {
    public int solution(int N, int M) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> dp = new LinkedList<>();
        int primeIndex = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                if (primeIndex == 0) {
                    dp.add(i);
                } else {
                    dp.add(i + dp.get(primeIndex - 1));
                }
                ++primeIndex;
            }
        }

        int answer = 0;
        dp = new ArrayList<>(dp);
        for (int i = dp.size() - 1; i >= 0; i--) {
            int end = dp.get(i);
            if (end == M) {
                answer++;
            }
            for (int j = i - 1; j >= 0; j--) {
                int start = dp.get(j);
                int diff = end - start;
                if (diff == M) {
                    answer++;
                }

                if (diff >= M) {
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(20, 36));
        System.out.println(solution.solution(12, 10));


    }
}
