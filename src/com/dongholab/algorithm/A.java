package com.dongholab.algorithm;

import java.util.*;
import java.util.stream.Collector;

class Solution {
    int solution(String s) {
        checker(s, s.length());
        return 0;
    }

    boolean bracketCheck(Stack<Character> stack, char afterBracket) {
        switch(afterBracket) {
            case ')':
                return stack.peek() == '(';
            case '}':
                return stack.peek() == '{';
            case ']':
                return stack.peek() == '[';
            default :
                return false;
        }
    }

    void checker(String s, int length) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> remainStack = new Stack<>();
        for(int i = 0; i < length; i++) {
            if (stack.size() == 0) {
                remainStack.clear();
            }
            char current = s.charAt(i);
            if (bracketCheck(stack, current)) {
                stack.pop();
            } else {
                stack.push(current);
            }
//            if (stack.size() == 2) {
//                remainStack.addAll(stack);
//            }
            if (stack.size() >= 2) {
                if (remainStack.size() == 0) {
                    remainStack.addAll(stack);
                } else {
                    remainStack.push(current);
                }
            }
        }
        System.out.println("s: " + s);
        String remain = stack.stream().collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString));
        System.out.println("remainStack: " + remainStack.stream().collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString)));
        System.out.println(remain);
    }
}

public class A {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("[]([[]){}") == 3);
        System.out.println(s.solution("{([()]))}") == 4);
        System.out.println(s.solution("(()()()") == 7);
    }
}
