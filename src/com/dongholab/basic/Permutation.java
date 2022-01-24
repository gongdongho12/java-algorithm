package com.dongholab.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<List<Integer>> result = permutation(list, 2);
        List<List<Integer>> result2 = permutation2(list, 2);
        System.out.println("result" + result2);
    }

    private static <T> List<List<T>> permutation(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        List<List<T>> result = new ArrayList<>();

        int length = list.size();
        for (int i = 0; i < length; i++) {
            T current = list.get(i);
            List<T> rest = new ArrayList<>(list.subList(0, i));
            rest.addAll(list.subList(i + 1, length));
            List<List<T>> permutations = permutation(rest, num - 1);
            List<List<T>> attach = permutations.stream().map((permutation) -> {
                List<T> append = new ArrayList<>(Arrays.asList(current));
                append.addAll(permutation);
                return append;
            }).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }

    private static <T> List<List<T>> permutation2(List<T> list, int num) {
        if (num == 1) {
            return list.stream().map(v -> Arrays.asList(v)).collect(Collectors.toList());
        }
        int length = list.size();
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            var v = list.get(i);
            var rest = new ArrayList<>(list.subList(0, i));
            rest.addAll(list.subList(i + 1, length));
            var permutations = permutation2(rest, num - 1);
            var attach = permutations.stream().map(permutation -> {
                var append = new ArrayList<>(Arrays.asList(v));
                append.addAll(permutation);
                return append;
            }).collect(Collectors.toList());
            result.addAll(attach);
        }
        return result;
    }
}
