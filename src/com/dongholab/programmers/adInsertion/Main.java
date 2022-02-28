package com.dongholab.programmers.adInsertion;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public static class Pair {
        private int key;
        private long value;

        Pair(int key, long value) {
            this.key = key;
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return secondTimeFormat(this.key);
        }

        public String secondTimeFormat(int time) {
            int hour = time / 3600;
            time %= 3600;
            int minute = time/60;
            time %= 60;
            int second = time;
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeInt = strToSecond(play_time);
        int advTimeInt = strToSecond(adv_time);
        StringTokenizer st;
        int[] multiViewCnt = new int[playTimeInt];
        for (int i = 0; i < logs.length; i++) {
            st = new StringTokenizer(logs[i], "-");
            int start = strToSecond(st.nextToken());
            int end = strToSecond(st.nextToken());
            for (int j = start; j < end; j++) {
                multiViewCnt[j]++;
            }
        }

        Pair firstMaxAdv = new Pair(0, 0);
        long sum = 0;
        for (int i = 0; i < advTimeInt; i++) {
            sum += multiViewCnt[i];
        }
        firstMaxAdv.setValue(sum);

        for (int i = advTimeInt; i < playTimeInt; i++) {
            sum += multiViewCnt[i] - multiViewCnt[i - advTimeInt];
            if (sum > firstMaxAdv.getValue()) {
                firstMaxAdv.setKey(i - advTimeInt + 1);
                firstMaxAdv.setValue(sum);
            }
        }

        return firstMaxAdv.toString();
    }

    static int strToSecond(String str) {
        AtomicInteger powIndex = new AtomicInteger(2);
        return Arrays.stream(str.split(":")).mapToInt(value -> Integer.parseInt(value) * Double.valueOf(Math.pow(60, powIndex.getAndDecrement())).intValue()).sum();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"}));
        System.out.println(solution.solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
        System.out.println(solution.solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
    }
}
