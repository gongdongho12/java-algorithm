package com.dongholab.boj.deque;

import java.io.*;
import java.util.*;

public class Main {
    public static void printValue(Integer value) {
        System.out.println(value == null ? -1 : value);
    }

    public static class LinkedNode<T> {
        private Node head;
        private Node tail;

        private int size = 0;

        private class Node {
            private T data = null;
            private Node next;

            public Node(T input) {
                this.data = input;
                this.next = null;
            }
        }

        public void addFirst(T input) {
            Node newNode = new Node(input);
            newNode.next = head;
            head = newNode;
            size++;
            if (head.next == null) {
                tail = head;
            }
        }

        public void addLast(T input) {
            Node newNode = new Node(input);
            if (size == 0) {
                addFirst(input);
            } else {
                tail.next = newNode;
                tail = newNode;
                size++;
            }
        }

        Node getNode(int index) {
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        }

        public T get(int k) {
            Node temp = getNode(k);
            return temp.data;
        }

        public T first() {
            if (size >= 1) {
                return get(0);
            } else {
                return null;
            }
        }

        public T last() {
            if (size >= 1) {
                return get(size - 1);
            } else {
                return null;
            }
        }

        public T removeFirst() {
            if (size >= 1) {
                Node temp = head;
                head = temp.next;
                T returnData = temp.data;
                size--;
                return returnData;
            } else {
                return null;
            }
        }

        public T remove(int k) {
            if (size >= 1) {
                if (k == 0) {
                    return removeFirst();
                }
                Node temp = getNode(k - 1);
                Node todoDeleted = temp.next;
                temp.next = temp.next.next;
                T returnData = todoDeleted.data;
                if (todoDeleted == tail) {
                    tail = temp;
                }
                size--;
                return returnData;
            } else {
                return null;
            }
        }

        public T removeLast() {
            return remove(size - 1);
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static void main(String[] args) throws IOException {
        LinkedNode<Integer> deque = new LinkedNode<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    printValue(deque.removeFirst());
                    break;
                case "pop_back":
                    printValue(deque.removeLast());
                    break;
                case "size":
                    printValue(deque.size());
                    break;
                case "empty":
                    printValue(deque.isEmpty() ? 1 : 0);
                    break;
                case "front":
                    printValue(deque.first());
                    break;
                case "back":
                    printValue(deque.last());
                    break;
            }
        }
    }
}
