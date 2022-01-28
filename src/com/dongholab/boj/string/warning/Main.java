package com.dongholab.boj.string.warning;

import java.io.*;
import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] firstTime = Arrays.stream(br.readLine().split(":")).mapToInt(value -> Integer.parseInt(value)).toArray();
        int[] secondTime = Arrays.stream(br.readLine().split(":")).mapToInt(value -> Integer.parseInt(value)).toArray();
        LocalTime a = LocalTime.of(firstTime[0], firstTime[1], firstTime[2]);
        LocalTime b = LocalTime.of(secondTime[0], secondTime[1], secondTime[2]);
        var result = Duration.between(a, b);

        if (result.isNegative()) {
            result = result.plusHours(24);
        }

        System.out.println(String.format("%02d:%02d:%02d", (result.isZero() ? 24 : result.toHoursPart()), result.toMinutesPart(), result.toSecondsPart()));
    }
}
