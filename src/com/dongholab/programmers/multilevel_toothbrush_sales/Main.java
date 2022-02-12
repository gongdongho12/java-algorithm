package com.dongholab.programmers.multilevel_toothbrush_sales;

import java.util.*;

class Solution {
    static Map<String, Triple> treeMap;
    static List<List<String>> depthKeyList;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        treeMap = new HashMap<>();
        int maxDepth = 0;
        depthKeyList = new LinkedList<>();
        depthKeyList.add(new LinkedList<>());
        while (Arrays.stream(referral).filter(v -> v != null).findAny().isPresent()) {
            for (int i = 0; i < enroll.length; i++) {
                if (referral[i] != null) {
                    String currentName = enroll[i];
                    String parentName = referral[i];
                    Triple parentData = treeMap.getOrDefault(parentName, null);
                    if (parentName.equals("-")) {
                        treeMap.put(currentName, new Triple(parentName, 0, 0));
                        referral[i] = null;
                        depthKeyList.get(0).add(currentName);
                    } else if (parentData != null) {
                        int nextDepth = parentData.depth + 1;
                        if (maxDepth < nextDepth) {
                            maxDepth = nextDepth;
                            depthKeyList.add(new LinkedList<>());
                        }
                        treeMap.put(currentName, new Triple(parentName, 0, nextDepth));
                        depthKeyList.get(nextDepth).add(currentName);
                        referral[i] = null;
                    }
                }
            }
        }

        Map<String, Integer> sellingMap = new HashMap<>();
        for (int i = 0; i < seller.length; i++) {
            sellingMap.put(seller[i], amount[i]);
        }

        for (int i = depthKeyList.size() - 1; i >= 0; i--) {
            var depthList = depthKeyList.get(i);
            depthList.forEach(currentName -> {
                int sellingCount = sellingMap.getOrDefault(currentName, 0);
                recursiveSendTip(currentName, 100 * sellingCount);
            });
        }

        return Arrays.stream(enroll).mapToInt(name -> treeMap.get(name).price).toArray();
    }

    public void recursiveSendTip(String currentName, int sellPrice) {
        Triple currentData = treeMap.getOrDefault(currentName, null);
        if (currentData != null && sellPrice > 0) {
            int tip = sellPrice / 10;
            int remainPrice = sellPrice - tip;
            currentData.price += remainPrice;
            if (!currentData.parent.equals("-") && tip > 0) {
                recursiveSendTip(currentData.parent, tip);
            }
        }
    }
}

class Triple {
    String parent;
    int price;
    int depth;

    public Triple(String parent, int price, int depth) {
        this.parent = parent;
        this.price = price;
        this.depth = depth;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        )));
    }
}
