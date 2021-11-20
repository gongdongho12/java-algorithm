package com.dongholab;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};
        Comparator<Integer> descComp = Comparator.comparing(Integer::intValue).reversed();
        List<Integer> discountList = Arrays.stream(discounts).boxed().sorted(descComp).collect(Collectors.toList());
        Arrays.stream(prices).boxed().sorted(descComp).mapToInt(v -> {
            if (discountList.size() > 0) {
                v = v * (100 - discountList.remove(0)) / 10;
            }
            return v;
        }).sum();
    }

    class Solution {
        public int solution(int[] prices, int[] discounts) {
            Comparator<Integer> descCompare = Comparator.comparing(Integer::intValue).reversed();
            List<Integer> discountList = Arrays.stream(discounts).boxed().sorted(descCompare).collect(Collectors.toList());

            return Arrays.stream(prices).boxed().sorted(descCompare).mapToInt(v -> {
                if (discountList.size() > 0) {
                    v = v / 100 * (100 - discountList.remove(0));
                }
                return v;
            }).sum();
        }
    }
}
