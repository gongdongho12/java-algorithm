package com.dongholab.programmers.friends4Block;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    final static char EMPTY = ' ';

    public int solution(int m, int n, String[] board) {
        Map<Integer, Set<Integer>> removeMap = new HashMap<>();
        for (int row = 0; row < m - 1; row++) {
            String line = board[row];
            String nextLine = board[row + 1];
            for (int col = 0; col < n - 1; col++) {
                char current = line.charAt(col);
                if (current != ' ') {
                    char right = line.charAt(col + 1);
                    char bottomRight = nextLine.charAt(col + 1);
                    char bottom = nextLine.charAt(col);
                    if (current == right && current == bottom && current == bottomRight) {
                        Set<Integer> removeRow = removeMap.getOrDefault(row, new HashSet<>());
                        removeRow.add(col);
                        removeRow.add(col + 1);
                        removeMap.put(row, removeRow);
                        Set<Integer> removeRowNext = removeMap.getOrDefault(row + 1, new HashSet<>());
                        removeRowNext.add(col);
                        removeRowNext.add(col + 1);
                        removeMap.put(row + 1, removeRowNext);
                    }
                }
            }
        }
        if (removeMap.size() > 0) {
            removeMap.forEach((row, removeList) -> {
                StringBuilder lineBuilder = new StringBuilder(board[row]);
                removeList.forEach(col -> lineBuilder.setCharAt(col, EMPTY));
                board[row] = lineBuilder.toString();
            });

            String bottomLine = board[m - 1];
            for (int row = m - 2; row >= 0; row--) {
                StringBuilder bottomLineBuilder = new StringBuilder(bottomLine);
                for (int col = 0; col < n; col++) {
                    char currentChar;
                    AtomicInteger appendRow = new AtomicInteger(0);
                    StringBuilder currentLineBuilder;
                    String newLine;
                    int newLineRow;
                    do {
                        newLineRow = row - appendRow.getAndIncrement();
                        newLine = board[newLineRow];
                        currentChar = newLine.charAt(col);
                    } while (currentChar == EMPTY && newLineRow > 0);
                    currentLineBuilder = new StringBuilder(newLine);
                    if (currentChar != EMPTY && bottomLine.charAt(col) == EMPTY) {
                        bottomLineBuilder.setCharAt(col, currentChar);
                        currentLineBuilder.setCharAt(col, EMPTY);
                        board[newLineRow] = currentLineBuilder.toString();
                    }
                }
                board[row + 1] = bottomLineBuilder.toString();
                bottomLine = board[row];
            }
            return solution(m, n, board);
        } else {
            return Arrays.stream(board).mapToInt(line -> Math.toIntExact(line.codePoints().filter(code -> Character.valueOf((char) code) == EMPTY).count())).sum();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }
}
