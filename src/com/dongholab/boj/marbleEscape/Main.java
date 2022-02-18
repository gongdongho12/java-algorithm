package com.dongholab.boj.marbleEscape;

import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair prev) {
        this.x = prev.x;
        this.y = prev.y;
    }
}

public class Main {
    static int N, M, ans = 0;
    static char[][] map;
    static Pair[] way = {
            new Pair(-1, 0),
            new Pair(0, 1),
            new Pair(1, 0),
            new Pair(0, -1)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        Pair red = null, blue = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    red = new Pair(i, j);
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    blue = new Pair(i, j);
                    map[i][j] = '.';
                }
            }
        }

        recursive(0, red, blue);
        System.out.println(ans);
    }

    static void recursive(int dep, Pair red, Pair blue) {
        if (ans == 1 || dep == 10) {
            return;
        }

        for (int k = 0; k < 4; k++) {
            Pair nextRed = new Pair(red);
            Pair nextBlue = new Pair(blue);
            move(nextRed, k);
            move(nextBlue, k);
            int type = check(nextRed, nextBlue);
            if (type == 0) {
                continue;
            } else if (type == 1) {
                ans = 1;
                return;
            } else {
                if (nextRed.x == nextBlue.x && nextRed.y == nextBlue.y) {
                    if (k == 0) {
                        if (nextRed.x < nextBlue.x) {
                            nextBlue.x += 1;
                        } else {
                            nextRed.x += 1;
                        }
                    } else if (k == 1) {
                        if (nextRed.y > nextBlue.y) {
                            nextBlue.y -= 1;
                        } else {
                            nextRed.y -= 1;
                        }
                    } else if (k == 2) {
                        if (nextRed.x > nextBlue.x) {
                            nextBlue.x -= 1;
                        } else {
                            nextRed.x -= 1;
                        }
                    } else if (k == 3) {
                        if (nextRed.x < nextBlue.x) {
                            nextBlue.y += 1;
                        } else {
                            nextRed.y += 1;
                        }
                    }
                }
            }
            recursive(dep + 1, nextRed, nextBlue);
        }
    }

    static int check(Pair red, Pair blue) {
        if (map[red.x][red.y] == 'O' && map[blue.x][blue.y] == 'O') {
            return 0;
        }
        if (map[red.x][red.y] == 'O' && map[blue.x][blue.y] != 'O') {
            return 1;
        }
        if (map[red.x][red.y] != 'O' && map[blue.x][blue.y] == 'O') {
            return 0;
        }
        if (map[red.x][red.y] != 'O' && map[blue.x][blue.y] != 'O') {
            return 2;
        }
        return 0;
    }

    static void move(Pair pair, int k) {
        while (true) {
            pair.x += way[k].x;
            pair.y += way[k].y;
            if (map[pair.x][pair.y] == '#') {
                pair.x -= way[k].x;
                pair.y -= way[k].y;
                break;
            } else if (map[pair.x][pair.y] == 'O') {
                break;
            }
        }
    }
}