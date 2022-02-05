package com.dongholab.boj.bruteForce.AandB2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class StringChecker2 {
    String end;

    public StringChecker2(String end) {
        this.end = end;
    }

    public String check(String str) {
        String next1 = str + "A";
        StringBuffer sb = new StringBuffer(str);
        sb.append("B");
        String next2 = sb.reverse().toString();
        int maxLength = end.length();
        if (next1.equals(end)) {
            return next1;
        } else if (next2.equals(end)) {
            return next2;
        } else {
            String temp1 = null, temp2 = null;
            if (next1.length() < maxLength) {
                temp1 = check(next1);
            }
            if (temp1 != null) {
                return temp1;
            }
            if (next2.length() < maxLength) {
                temp2 = check(next2);
            }
            if (temp2 != null) {
                return temp2;
            }
            return null;
        }
    }
}

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        String end = br.readLine();
        StringChecker2 sc = new StringChecker2(end);
        System.out.println(sc.check(start) != null ? 1 : 0);
    }
}
