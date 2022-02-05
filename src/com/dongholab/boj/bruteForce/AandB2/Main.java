package com.dongholab.boj.bruteForce.AandB2;

import java.io.*;

class StringChecker {
    String start;

    public StringChecker(String start) {
        this.start = start;
    }

    public String check(String end) {
        int endLengthIdx = end.length() - 1;
        char strStart = end.charAt(0);
        char strEnd = end.charAt(endLengthIdx);
        String next1 = null;
        if (strStart == 'B') {
            String temp = end.substring(1);
            StringBuilder sb = new StringBuilder(temp);
            next1 = sb.reverse().toString();
        }
        String next2 = null;
        if (strEnd == 'A') {
            next2 = end.substring(0, endLengthIdx);
        }

        if (next1 != null && next1.equals(start)) {
            return next1;
        } else if (next2 != null && next2.equals(start)) {
            return next2;
        } else {
            String temp1 = null, temp2 = null;
            if (next1 != null && next1.length() > 0) {
                temp1 = check(next1);
            }
            if (temp1 != null) {
                return temp1;
            }
            if (next2 != null && next2.length() > 0) {
                temp2 = check(next2);
            }
            if (temp2 != null) {
                return temp2;
            }
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        StringChecker sc = new StringChecker(start);
        String end = br.readLine();
        System.out.println(sc.check(end) != null ? 1 : 0);
    }
}
