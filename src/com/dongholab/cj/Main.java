package com.dongholab.cj;

import java.util.*;
import java.util.stream.*;

public class Main {
    private static HashMap<Integer, Set<Integer>> connection = new HashMap<>();
    private static int cnt = 0;

    public static void main(String[] args) {
        String[] subway = {"1 2 3 4 5 6 7 8","2 11","0 11 10","3 17 19 12 13 9 14 15 10","20 2 21"};
        int start = 1, end = 9;
        int startLine = -1, endLine = -1;
        List<List<Integer>> subwayList = Arrays.asList(subway).stream().map(subwayLines -> Arrays.asList(subwayLines.split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList())).collect(Collectors.toList());
        for (int i = 0; i < subwayList.size(); i++) {
            List<Integer> subwayLine = subwayList.get(i);
            if (subwayLine.contains(start)) {
                startLine = i;
            }
            if (subwayLine.contains(end)) {
                endLine = i;
            }
            for (int j = 0; j < subwayList.size(); j++) {
                if (i != j) {
                    List<Integer> copySubwayLine = new ArrayList<>(subwayLine);
                    List<Integer> otherSubwayLine = subwayList.get(j);
                    if (copySubwayLine.retainAll(otherSubwayLine)) {
                        Set<Integer> prev = connection.getOrDefault(i, new HashSet<>());
                        Set<Integer> otherPrev = connection.getOrDefault(j, new HashSet<>());
                        prev.add(j);
                        otherPrev.add(i);
                        connection.put(i, prev);
                        connection.put(j, otherPrev);
                    }
                }
            }
        }

        System.out.println(answer(startLine, endLine));
    }

    public static int answer(int startLine, int endLine) {
        if (startLine == endLine) {
            return cnt;
        }
        cnt++;
        Set<Integer> startConnection = connection.get(startLine);
        int maxSize = connection.size() / 2 + 1;
        if (cnt >= maxSize) {
          return maxSize;
        } else if (startConnection.contains(endLine)) {
            return cnt;
        } else {
            return startConnection.stream().map(newStartLine -> answer(newStartLine, endLine)).mapToInt(Integer::valueOf).min().getAsInt();
        }
    }
}
