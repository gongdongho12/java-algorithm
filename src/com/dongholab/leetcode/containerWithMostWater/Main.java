package com.dongholab.leetcode.containerWithMostWater;

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = Math.min(height[left], height[right]) * (right - left);
        while(left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int temp = Math.min(leftHeight, rightHeight) * (right - left);
            if (max < temp) {
                max = temp;
            }

            if (leftHeight <= rightHeight) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{ 1,8,6,2,5,4,8,3,7 }) == 49);
        System.out.println(solution.maxArea(new int[]{ 2,3,4,5,18,17,6 }) == 17);
    }
}
