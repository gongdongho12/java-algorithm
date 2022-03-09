package com.dongholab.leetcode.houseRobber2;

import java.util.*;

class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        switch (length) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 3:
                return Arrays.stream(nums).max().getAsInt();
            default:
                int[] dp = new int[length];
                int[] dp2 = new int[length];
                dp[0] = nums[0];
                dp[1] = nums[0];
                dp2[0] = 0;
                dp2[1] = nums[1];
                for (int i = 2; i < length; i++) {
                    if (i != nums.length - 1) {
                        dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
                    }
                    dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
                }
                return Math.max(dp[length - 2], dp2[length - 1]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{0}) == 0);
        System.out.println(solution.rob(new int[]{1, 2, 3, 4, 5, 6}) == 12);
        System.out.println(solution.rob(new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}) == 2926);
        System.out.println(solution.rob(new int[]{2, 3, 2}) == 3);
        System.out.println(solution.rob(new int[]{1, 2, 3, 1}) == 4);
        System.out.println(solution.rob(new int[]{1, 2, 3}) == 3);
    }
}
