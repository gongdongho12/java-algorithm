package com.dongholab.cj;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {

    public static void main(String[] args) {
        int[] x = {3, 4};
        int[] y = {3, 5};
        int[] radius = {2, 3};
        int[] v = {12, 83, 54, 35, 686, 337, 258, 95, 170, 831, 8, 15};

        // i == min X;
        // b == min Y;
        // r == max X;
        // t == max Y
        int l = -1, b = -1, r = -1, t = -1;

        for (int i = 0; i < radius.length; i++) {
            int tempRadius = radius[i];
            int tempX = x[i];
            int tempY = y[i];

            int tempMinX = tempX - tempRadius;
            int tempMaxX = tempX + tempRadius;
            int tempMinY = tempY - tempRadius;
            int tempMaxY = tempY + tempRadius;
            if (l == -1 || tempMinX < l) {
                l = tempMinX;
            }
            if (r == -1 || tempMaxX > r) {
                r = tempMaxX;
            }
            if (b == -1 || tempMinY < b) {
                b = tempMinY;
            }
            if (t == -1 || tempMaxY > t) {
                t = tempMaxY;
            }
        }

        AtomicInteger counter = new AtomicInteger();
        int finalR = r;
        int finalL = l;
        int finalB = b;
        int finalT = t;

        Long answer = Arrays.stream(v).boxed()
                .collect(Collectors.groupingBy(value -> counter.getAndIncrement() / 2))
                .values().stream().map(path -> {
                    int assignX = finalL + path.get(0) % (finalR - finalL);
                    int assignY = finalB + path.get(1) % (finalT - finalB);
                    return Arrays.asList(new Integer[]{assignX, assignY});
                }).map(assignPath -> {
                    boolean check = false;
                    checker:
                    for (int i = 0; i < radius.length; i++) {
                        if (Math.pow(radius[i], 2) >= (Math.pow(x[i] - assignPath.get(0), 2) + Math.pow(y[i] - assignPath.get(1), 2))) {
                            check = true;
                            break checker;
                        }
                    }
                    return check;
                }).filter(check -> check).count();

        Long a = (answer * (finalR - finalL) * (finalT - finalB)) / (v.length / 2);
        a.intValue();
    }

    class Solution {
        public int solution(int[] x, int[] y, int[] radius, int[] v) {
            int l = -1, b = -1, r = -1, t = -1;

            for (int i = 0; i < radius.length; i++) {
                int tempRadius = radius[i];
                int tempX = x[i];
                int tempY = y[i];

                int tempMinX = tempX - tempRadius;
                int tempMaxX = tempX + tempRadius;
                int tempMinY = tempY - tempRadius;
                int tempMaxY = tempY + tempRadius;
                if (l == -1 || tempMinX < l) {
                    l = tempMinX;
                }
                if (r == -1 || tempMaxX > r) {
                    r = tempMaxX;
                }
                if (b == -1 || tempMinY < b) {
                    b = tempMinY;
                }
                if (t == -1 || tempMaxY > t) {
                    t = tempMaxY;
                }
            }

            AtomicInteger counter = new AtomicInteger();
            int finalR = r;
            int finalL = l;
            int finalB = b;
            int finalT = t;

            Long answer = Arrays.stream(v).boxed()
                    .collect(Collectors.groupingBy(value -> counter.getAndIncrement() / 2))
                    .values().stream().map(path -> {
                        int assignX = finalL + path.get(0) % (finalR - finalL);
                        int assignY = finalB + path.get(1) % (finalT - finalB);
                        return Arrays.asList(new Integer[]{assignX, assignY});
                    }).map(assignPath -> {
                        boolean check = false;
                        checker:
                        for (int i = 0; i < radius.length; i++) {
                            if (Math.pow(radius[i], 2) >= (Math.pow(x[i] - assignPath.get(0), 2) + Math.pow(y[i] - assignPath.get(1), 2))) {
                                check = true;
                                break checker;
                            }
                        }
                        return check;
                    }).filter(check -> check).count();

            return new Long((answer * (finalR - finalL) * (finalT - finalB)) / (v.length / 2)).intValue();
        }
    }
}
