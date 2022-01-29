package com.dongholab.boj.string.group_word_checker;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Main {
    static boolean groupWordChecker(String str) {
        Set<Character> set = new HashSet<>();
        Pattern pattern = Pattern.compile("([a-z])\\1*");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            char token = matcher.group().charAt(0);
            if (set.contains(token)) {
                return false;
            } else {
                set.add(token);
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(IntStream.range(0, N).filter(i -> {
            try {
                String str = br.readLine();
                return groupWordChecker(str);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }).count());
    }
}
