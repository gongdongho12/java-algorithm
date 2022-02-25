package com.dongholab.programmers.findWay;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

class Solution {
    List<Integer> preOrder, postOrder;
    public int[][] solution(int[][] nodeinfo) {
        int length = nodeinfo.length;
        preOrder = new LinkedList<>();
        postOrder = new LinkedList<>();

        AtomicReference<Node> root = new AtomicReference<>(null);
        IntStream.range(0, length).mapToObj(i -> {
            int[] nodeXY = nodeinfo[i];
            return new Node(nodeXY[0], nodeXY[1], i + 1, null, null);
        }).sorted().forEach(node -> {
            Node rootNode = root.get();
            if (rootNode == null) {
                root.set(node);
            } else {
                generateTree(rootNode, node);
            }
        });
        preOrder(root.get());
        postOrder(root.get());

        return new int[][]{
                preOrder.stream().mapToInt(v -> v).toArray(),
                postOrder.stream().mapToInt(v -> v).toArray()
        };
    }

    public void generateTree(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                generateTree(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                generateTree(parent.right, child);
            }
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            preOrder.add(node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            postOrder.add(node.value);
        }
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int value;
    Node left;
    Node right;

    public Node(int x, int y, int value, Node left, Node right) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node n) {
        if (this.y == n.y) {
            return this.x - n.x;
        } else {
            return n.y - this.y;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                Arrays.toString(Arrays.stream(solution.solution(new int[][]{
                        new int[]{5, 3},
                        new int[]{11, 5},
                        new int[]{13, 3},
                        new int[]{3, 5},
                        new int[]{6, 1},
                        new int[]{1, 3},
                        new int[]{8, 6},
                        new int[]{7, 2},
                        new int[]{2, 2}
                })).map(v -> Arrays.toString(v)).toArray())
        );
    }
}
