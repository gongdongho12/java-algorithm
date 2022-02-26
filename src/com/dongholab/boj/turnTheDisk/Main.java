package com.dongholab.boj.turnTheDisk;

import java.io.*;
import java.util.*;

class Pair {
    private Integer key;
    private Integer value;

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    Pair(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair)
            return this.key == ((Pair) o).key && this.value == ((Pair) o).value;
        return false;
    }
}

class Circle {
    List<Integer> circlePosition;
    Circle(List<Integer> circleList) {
        circlePosition = new LinkedList<>();
        circlePosition.addAll(circleList);
    }

    public List<Integer> getCirclePosition() {
        return this.circlePosition;
    }

    void rotate(int distance) {
        Collections.rotate(this.circlePosition, distance);
    }

    public Pair getSumWithSize() {
        int[] remain = this.circlePosition.stream().filter(v -> v != null).mapToInt(v -> v).toArray();
        return new Pair(remain.length, Arrays.stream(remain).sum());
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        List<Circle> circleList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> tempCircle = new LinkedList<>();
            for (int j = 0; j < M; j++) {
                tempCircle.add(Integer.parseInt(st.nextToken()));
            }
            circleList.add(new Circle(tempCircle));
        }

        Set<Pair> removeSet = new HashSet<>();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = x; j <= circleList.size(); j += x) {
                Circle currentCircle = circleList.get(j - 1);
                // d = 0 시계, 1 반시계
                currentCircle.rotate((d == 0 ? 1 : -1) * k);
            }
            removeSet.clear();

            for (int j = 0; j < M; j++) {
                List<Integer> prevCirclePosition = null;
                for (int l = 0; l < circleList.size(); l++) {
                    Circle tempCircle = circleList.get(l);
                    List<Integer> tempCirclePosition = tempCircle.getCirclePosition();
                    if (prevCirclePosition != null) {
                        Integer prevValue = prevCirclePosition.get(j);
                        Integer currentValue = tempCirclePosition.get(j);
                        if (prevValue != null && currentValue != null && prevValue == currentValue) {
                            removeSet.add(new Pair(l, j));
                            removeSet.add(new Pair(l - 1, j));
                        }
                    }
                    int tempCirclePositionLength = tempCirclePosition.size();
                    int prevValueId = tempCirclePositionLength - 1;
                    Integer prevValue = tempCirclePosition.get(prevValueId);
                    for (int m = 0; m < tempCirclePositionLength; m++) {
                        Integer currentValue = tempCirclePosition.get(m);
                        if (prevValue != null && currentValue != null && currentValue == prevValue) {
                            removeSet.add(new Pair(l, m));
                            removeSet.add(new Pair(l, prevValueId));
                        }
                        prevValueId = m;
                        prevValue = currentValue;
                    }
                    prevCirclePosition = tempCirclePosition;
                }
            }

            if (removeSet.size() > 0) {
                for (Pair pair : removeSet) {
                    circleList.get(pair.getKey()).getCirclePosition().set(pair.getValue(), null);
                }
            } else {
                Pair countWithSum = new Pair(0, 0);
                for (int j = 0; j < circleList.size(); j++) {
                    Pair pair = circleList.get(j).getSumWithSize();
                    countWithSum.setKey(countWithSum.getKey() + pair.getKey());
                    countWithSum.setValue(countWithSum.getValue() + pair.getValue());
                }

                if (countWithSum.getKey() > 0) {
                    double avg = Double.valueOf(countWithSum.getValue()) / countWithSum.getKey();
                    for (int j = 0; j < circleList.size(); j++) {
                        Circle currentCircle = circleList.get(j);
                        List<Integer> currentPositionList = currentCircle.getCirclePosition();
                        for (int l = 0; l < currentPositionList.size(); l++) {
                            Integer currentValue = currentPositionList.get(l);
                            if (currentValue != null) {
                                if (currentValue < avg) {
                                    currentPositionList.set(l, currentValue + 1);
                                } else if (currentValue > avg) {
                                    currentPositionList.set(l, currentValue - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(circleList.stream().mapToInt(circle -> circle.getSumWithSize().getValue()).sum());
    }
}