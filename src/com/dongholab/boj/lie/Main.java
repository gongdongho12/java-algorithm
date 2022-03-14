package com.dongholab.boj.lie;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] truth;
    static int[] parent;
    static List<int[]> partyList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        partyList = new LinkedList<>();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truth_number = Integer.parseInt(st.nextToken());
        if (truth_number == 0) {
            System.out.println(M);
            System.exit(0);
        } else {
            truth = new int[truth_number];
            for (int i = 0; i < truth_number; i++) {
                truth[i] = Integer.parseInt(st.nextToken());
            }

            parent = new int[N + 1];
            init();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int party_number = Integer.parseInt(st.nextToken());
                if (party_number == 0) {
                    partyList.add(new int[]{});
                    continue;
                }
                int[] tmp = new int[party_number];
                int val = Integer.parseInt(st.nextToken());
                tmp[0] = val;
                for (int j = 1; j < party_number; j++) {
                    int item = Integer.parseInt(st.nextToken());
                    tmp[j] = item;
                    if (val < tmp[j]) {
                        union(val, item);
                    } else {
                        union(item, val);
                    }
                }
                partyList.add(tmp);
            }
            int ans = 0;

            for (int[] tmp : partyList) {
                boolean flag = false;
                for (int item : tmp) {
                    if (!check(item)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    ans++;
                }
            }
            System.out.println(ans);
        }

    }

    static public void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static public int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    static public boolean check(int x) {
        x = find(x);
        for (int item : truth) {
            if (x == find(item)) {
                return false;
            }
        }
        return true;
    }
}