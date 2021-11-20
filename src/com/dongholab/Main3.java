package com.dongholab;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main3 {

    public static void main(String[] args) {
//        String s = "aabcbcd";
        String s = "aabcabcbc";
        String t = "abc";
        int i = 0;
        while (s.indexOf(t) >= 0) {
            int beforeLenght = s.length();
            s = s.replaceAll(t, "");
            i += (beforeLenght - s.length()) / t.length();
        }
        System.out.println("i : " + i);
    }

    public class Solution {
        public int solution(String s, String t) {
            int answer = 0;
            while (s.indexOf(t) >= 0) {
                int beforeLengh = s.length();
                s = s.replaceAll(t, "");
                answer += (beforeLengh - s.length()) / t.length();
            }
            return answer;
        }
    }
}
