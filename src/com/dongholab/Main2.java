package com.dongholab;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {
//        String s = "abcxyqwertyxyabc";
        String s = "";
//        String s = "abcxyasdfasdfxyabc";
//        int length = s.length();
        List<String> arr = new ArrayList<>();
        all:
        while (s.length() > 0) {
            boolean last = true;
            System.out.println("size: " + (s.length() / 2));
            split:
            for (int i = 1; i <= s.length() / 2; i++) {
                String first = s.substring(0, i);
                int lastStart = s.length() - i;
                String end = s.substring(lastStart);
                if (first.equals(end)) {
                    System.out.println("first : " + first);
                    s = s.substring(i, lastStart);
                    System.out.println("update : " + s);
                    arr.add(first);
                    last = false;
                    break split;
                }
            }
            if (last) {
                System.out.println("is End?" + last);
                break;
            }
        }
        ArrayList<String> reversed = new ArrayList<>(arr);
        Collections.reverse(reversed);
        if (s.length() > 0) {
            arr.add(s);
        }
        arr.addAll(reversed);
        arr.stream().forEach(v -> {
            System.out.println("v : " + v);
        });
    }

    class Solution {
        public String[] solution(String s) {
            List<String> arr = new ArrayList<>();
            while (s.length() > 0) {
                boolean last = true;
                split:
                for (int i = 1; i <= s.length() / 2; i++) {
                    String first = s.substring(0, i);
                    int lastStart = s.length() - i;
                    String end = s.substring(lastStart);
                    if (first.equals(end)) {
                        s = s.substring(i, lastStart);
                        arr.add(first);
                        last = false;
                        break split;
                    }
                }
                if (last) {
                    break;
                }
            }
            ArrayList<String> reversed = new ArrayList<>(arr);
            Collections.reverse(reversed);
            if (s.length() > 0) {
                arr.add(s);
            }
            arr.addAll(reversed);
            return arr.stream().toArray(String[]::new);
        }
    }
}
