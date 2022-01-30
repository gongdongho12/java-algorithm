package com.dongholab.boj.string.password_read;

/*
    백준: https://www.acmicpc.net/problem/4659
 */

import java.io.*;
import java.util.stream.*;

public class Main {
    static String str;
    static char[] word;
    static int moCount;
    static boolean sameWord;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            str = br.readLine();
            if (str.equals("end")) {
                break;
            }
            word = new char[str.length()];
            moCount = 0;
            sameWord = false;

            for (int i = 0; i < str.length(); i++) {
                word[i] = str.charAt(i);

                if (strInclude("aeiou", word[i])) {
                    moCount++;
                }
            }

            for (int z = 2; z < word.length; z++) {
                if (IntStream.rangeClosed(z - 2, z).mapToObj(v -> Character.valueOf(word[v])).filter(c -> strInclude("aeiou", c)).count() == 3) {
                    sameWord = true;
                } else if (IntStream.rangeClosed(z - 2, z).mapToObj(v -> Character.valueOf(word[v])).filter(c -> !strInclude("aeiou", c)).count() == 3) {
                    sameWord = true;
                }
            }

            for (int i = 1; i < word.length; i++) {
                if (word[i] == word[i - 1]) {
                    if (!strInclude("eo", word[i])) {
                        sameWord = true;
                    }
                }
            }

            System.out.println(String.format("<%s> %s", str, (moCount == 0 || sameWord) ? "is not acceptable." : "is acceptable."));
        }
    }

    static boolean strInclude(String str, char c) {
        return str.contains(String.valueOf(c));
    }
}
