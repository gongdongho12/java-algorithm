package com.dongholab.boj.findHome;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    final static int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] map = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    map[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            map[u][v] = w;
            map[v][u] = w;
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] mac = new int[M];
        for (int i = 0; i < M; i++) {
            mac[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] star = new int[S];
        for (int i = 0; i < S; i++) {
            star[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int[] notHome = new int[M + S];
        for (int i = 0; i < M + S; i++) {
            if (i <= M - 1) {
                notHome[i] = mac[i];
            } else {
                notHome[i] = star[i - M];
            }
        }

        OptionalInt result = IntStream.range(0, V - 1).mapToObj(home -> {
            if (Arrays.stream(star).filter(s -> s == home).findFirst().isEmpty() && Arrays.stream(mac).filter(m -> m == home).findFirst().isEmpty()) {
                OptionalInt macLength = Arrays.stream(mac).map(m -> map[home][m]).min();
                OptionalInt starLength = Arrays.stream(star).map(s -> map[home][s]).min();

                if (macLength.isPresent() && starLength.isPresent()) {
                    int macRealLength = macLength.getAsInt();
                    int starRealLength = starLength.getAsInt();
                    if (macRealLength <= x && starRealLength <= y) {
                        return macRealLength + starRealLength;
                    }
                }
            }
            return null;
        }).filter(Objects::nonNull).mapToInt(v -> v).min();

        System.out.println(result.isPresent() ? result.getAsInt() : -1);
    }
}