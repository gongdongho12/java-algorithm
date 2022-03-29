package com.dongholab.boj.hearsay;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> ts = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            ts.add(br.readLine());
        }
        List<String> result = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            if (ts.contains(line)) {
                result.add(line);
                ts.remove(line);
            }
        }
        System.out.println(String.format("%d\n%s", result.size(), result.stream().sorted().collect(Collectors.joining("\n"))));
    }
}