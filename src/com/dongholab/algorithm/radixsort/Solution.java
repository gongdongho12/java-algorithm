package com.dongholab.algorithm.radixsort;

import java.util.*;

public class Solution {
    public static class Pair {
        private Integer key;
        private String value;

        Pair(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return this.key;
        }

        @Override
        public String toString() {
            return String.format("%d %s", this.key, this.value);
        }
    }

    public static int digit16(int v, int d) {
        v = v >> d * 4;
        return v & 0xf;
    }

    public static int getDigit16Cnt(int v) {
        int i = 0;
        while (v > 0) {
           v >>= 4;
           i++;
        }
        return i;
    }

    public static void radixSort(List<Pair> pairList) {
        Integer max = null;
        for (Pair pair: pairList) {
            if (max == null || max < pair.getKey()) {
                max = pair.getKey();
            }
        }

        int maxDigit = getDigit16Cnt(max);
        for (int digit = 0; digit < maxDigit; digit++) {
            countSort(pairList, digit);
        }
    }

    public static void countSort(List<Pair> pairList, int digit) {
        LinkedHashMap<Integer, LinkedList<Pair>> counter = new LinkedHashMap();
        for (int i = 0; i < 16; i++) {
            counter.put(i, new LinkedList());
        }
        for (Pair pair: pairList) {
            int digitValue = digit16(pair.getKey(), digit);
            counter.get(digitValue).add(pair);
        }
        pairList.clear();
        pairList.addAll(counter.values().stream().reduce(new LinkedList(), (prevList, nextPair) -> {
            prevList.addAll(nextPair);
            return prevList;
        }));
    }

    public static void main(String[] args) {
        int size = 4;
        LinkedList<Pair> pairList = new LinkedList(
                Arrays.asList(new Pair[]{
                        new Pair(1033111111, "zeppelin"),
                        new Pair(1033111111, "beatles"),
                        new Pair(10, "queen"),
                        new Pair(55, "scorpions")
                })
        );

        radixSort(pairList);
        pairList.forEach(pair -> System.out.println(pair));
    }
}
