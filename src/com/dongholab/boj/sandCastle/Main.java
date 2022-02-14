package com.dongholab.boj.sandCastle;

import java.io.*;
import java.util.*;

public class Main {
    public static class Pair {
        private int x;
        private int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    final static char EMPTY = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Pair> direction = new ArrayList<>(Arrays.asList(new Pair[]{
                new Pair(0, -1),
                new Pair(0, 1),
                new Pair(1, -1),
                new Pair(1, 0),
                new Pair(1, 1),
                new Pair(-1, -1),
                new Pair(-1, 0),
                new Pair(-1, 1)
        }));
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        char[][] map = new char[row][column];
        Queue<Pair> emptyQueue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < column; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == EMPTY) {
                    emptyQueue.add(new Pair(i, j));
                }
            }
        }

        int answer = 0;
        while (!emptyQueue.isEmpty()) {
            int size = emptyQueue.size();
            for (int i = 0; i < size; i++) {
                Pair current = emptyQueue.poll();
                for (int j = 0; j < 8; j++) {
                    int nextX = current.getX() + direction.get(j).getX();
                    int nextY = current.getY() + direction.get(j).getY();
                    if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < column) {
                        if (map[nextX][nextY] != EMPTY) {
                            map[nextX][nextY]--;
                            if (map[nextX][nextY] == '0') {
                                map[nextX][nextY] = EMPTY;
                                emptyQueue.add(new Pair(nextX, nextY));
                            }
                        }
                    }
                }
            }
            if (!emptyQueue.isEmpty()) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
