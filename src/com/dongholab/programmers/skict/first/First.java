package com.dongholab.programmers.skict.first;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Pair implements Comparable<Pair> {
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

    @Override
    public int compareTo(Pair p) {

        double bal = Double.valueOf(this.a) / Double.valueOf(this.b);
        double otherBal = Double.valueOf(p.a) / Double.valueOf(p.b);
        if (bal == otherBal) {
            return p.a - this.a;
        } else {
            return Double.valueOf((otherBal - bal) * 1000).intValue();
        }
    }
}

class Solution {
    final static int[] coins = new int[]{1, 5, 10, 50, 100, 500};

    public int solution(int money, int[] costs) {
        int answer = 0;
        PriorityQueue<Pair> coinPairs = new PriorityQueue<>();
        IntStream.range(0, costs.length).forEach(i -> {
            coinPairs.add(new Pair(coins[i], costs[i]));
        });
        while (!coinPairs.isEmpty() && money != 0) {
            var currentCoin = coinPairs.poll();
            int cnt = money / currentCoin.getA();
            if (cnt > 0) {
                money -= cnt * currentCoin.getA();
                answer += cnt * currentCoin.getB();
            }
        }
        return answer;
    }
}

public class First {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4578, new int[]{1, 4, 99, 35, 50, 1000}));
        System.out.println(solution.solution(1999, new int[]{2, 11, 20, 100, 200, 600}));
    }
}
