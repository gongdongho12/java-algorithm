package com.dongholab.basic;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

public class Permutation {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<List<Integer>> result = permutation(list, 2);
        System.out.println("result" + result);
    }

    private static <T> List<List<T>> permutation(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        List<List<T>> result = new LinkedList<>();

        int length = list.size();
        for (int i = 0; i < length; i++) {
            T current = list.get(i);
            List<T> rest = new LinkedList<>(list.subList(0, i));
            rest.addAll(list.subList(i + 1, length));
            List<List<T>> permutations = permutation(rest, num - 1);
            List<List<T>> attach = permutations.stream().map((permutation) -> List.of(Stream.concat(Stream.of(current), permutation.stream())
                    .toArray(size -> (T[]) Array.newInstance(current.getClass(), size)))).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }
}
