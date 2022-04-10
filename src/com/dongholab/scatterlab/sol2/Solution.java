package com.dongholab.scatterlab.sol2;

public class Solution {
    final static double onwerF = 2.0;
    public double solution(double C, double F, double X) {
        double answer = X / onwerF;
        double assignF = onwerF + F;
        return Math.round(Math.min(answer, solutionWithParttime(C, F, X, assignF, C / onwerF)) * 1000000) / 1000000.0;
    }

    public double solutionWithParttime(double C, double F, double X, double sumF, double time) {
        double answer = X / sumF;
        double currentTime = time + answer;
        double assignF = sumF + F;
        double assignTime = time + (C / sumF);
        double nextTime = X / assignF;
        if (currentTime < assignTime + nextTime) {
            return currentTime;
        } else {
            return solutionWithParttime(C, F, X, assignF, assignTime);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(30.0, 1.0, 2.0));
        System.out.println(solution.solution(30.0, 2.0, 100.0));
        System.out.println(solution.solution(30.5, 3.14159, 1999.1999));
        System.out.println(solution.solution(500.0, 4.0, 2000.0));
    }
}
