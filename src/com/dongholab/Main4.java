package com.dongholab;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main4 {

    enum Test {
        TEST1,
        TEST2,
        TEST3;
    }

    public static void main(String[] args) {
        System.out.println(String.format("%s %s", "test", "123"));
        int[] intArr = {1,2,3};
//        Arrays.asList(intArr);
        Set<Integer> intSet = new HashSet();
        intSet.addAll(Arrays.stream(intArr).boxed().collect(Collectors.toList()));
        Arrays.stream(intArr).boxed().collect(Collectors.toSet());
        Integer[] a = Arrays.stream(intArr).boxed().toArray(Integer[]::new);
        List<Integer> b = Arrays.stream(intArr).boxed().collect(Collectors.toList());

        AtomicInteger c = new AtomicInteger(0);
        Arrays.stream(intArr).forEach(v -> {
            if (v == 2) {
                c.getAndAdd(1);
            }
        });
        Math.max(1, 2);

        Test.valueOf("Test");
    }
}
