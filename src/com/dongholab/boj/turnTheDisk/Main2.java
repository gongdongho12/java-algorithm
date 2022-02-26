package com.dongholab.boj.turnTheDisk;

import java.io.*;
import java.util.*;

class Pair2 {
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

    Pair2(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair2)
            return this.key == ((Pair2) o).key && this.value == ((Pair2) o).value;
        return false;
    }
}

class Circle2 {
    Integer[] circlePosition;
    Circle2(List<Integer> circleList) {
        circlePosition = circleList.stream().toArray(Integer[]::new);
    }

    public Integer[] getCirclePosition() {
        return this.circlePosition;
    }

    void rotate(int distance) {
        for (int i = 0; i < Math.abs(distance); i++) {
            if (distance > 0) {
                rotateRight();
            } else {
                rotateLeft();
            }
        }
    }

    void rotateRight() {
        int length = this.circlePosition.length;
        Integer[] tempArr = new Integer[length];
        Integer temp = this.circlePosition[length - 1];
        tempArr[0] = temp;
        for (int i = 0; i < length - 1; i++) {
            tempArr[i + 1] = this.circlePosition[i];
        }
        this.circlePosition = tempArr;
    }

    void rotateLeft() {
        int length = this.circlePosition.length;
        Integer[] tempArr = new Integer[length];
        Integer temp = this.circlePosition[0];
        tempArr[length - 1] = temp;
        for (int i = 0; i < length - 1; i++) {
            tempArr[i] = this.circlePosition[i + 1];
        }
        this.circlePosition = tempArr;
    }

    public Pair2 getSumWithSize() {
        int[] remain = Arrays.stream(this.circlePosition).filter(v -> v != null).mapToInt(v -> v).toArray();
        return new Pair2(remain.length, Arrays.stream(remain).sum());
    }
}

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        List<Circle2> circleList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> tempCircle = new LinkedList<>();
            for (int j = 0; j < M; j++) {
                tempCircle.add(Integer.parseInt(st.nextToken()));
            }
            circleList.add(new Circle2(tempCircle));
        }

        Set<Pair2> removeSet = new HashSet<>();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = x; j <= circleList.size(); j += x) {
                Circle2 currentCircle = circleList.get(j - 1);
                // d = 0 시계, 1 반시계
                currentCircle.rotate((d == 0 ? 1 : -1) * k);
            }
            removeSet.clear();

            for (int j = 0; j < M; j++) {
                Integer[] prevCirclePosition = null;
                for (int l = 0; l < circleList.size(); l++) {
                    Circle2 tempCircle = circleList.get(l);
                    Integer[] tempCirclePosition = tempCircle.getCirclePosition();
                    if (prevCirclePosition != null) {
                        Integer prevValue = prevCirclePosition[j];
                        Integer currentValue = tempCirclePosition[j];
                        // 인접한 원상의 인접 동일값 체크
                        if (prevValue != null && currentValue != null && prevValue == currentValue) {
                            removeSet.add(new Pair2(l, j));
                            removeSet.add(new Pair2(l - 1, j));
                        }
                    }
                    int tempCirclePositionLength = tempCirclePosition.length;
                    int prevValueId = tempCirclePositionLength - 1;
                    Integer prevValue = tempCirclePosition[prevValueId];
                    for (int m = 0; m < tempCirclePositionLength; m++) {
                        Integer currentValue = tempCirclePosition[m];
                        // 동일 원상의 인접 동일값 체크
                        if (prevValue != null && currentValue != null && currentValue == prevValue) {
                            removeSet.add(new Pair2(l, m));
                            removeSet.add(new Pair2(l, prevValueId));
                        }
                        prevValueId = m;
                        prevValue = currentValue;
                    }
                    prevCirclePosition = tempCirclePosition;
                }
            }

            if (removeSet.size() > 0) {
                for (Pair2 pair : removeSet) {
                    circleList.get(pair.getKey()).getCirclePosition()[pair.getValue()] = null;
                }
            } else {
                Pair2 countWithSum = new Pair2(0, 0);
                for (int j = 0; j < circleList.size(); j++) {
                    Pair2 pair = circleList.get(j).getSumWithSize();
                    countWithSum.setKey(countWithSum.getKey() + pair.getKey());
                    countWithSum.setValue(countWithSum.getValue() + pair.getValue());
                }

                if (countWithSum.getKey() > 0) {
                    // 실수형 평균
                    double avg = Double.valueOf(countWithSum.getValue()) / Double.valueOf(countWithSum.getKey());

                    for (int j = 0; j < circleList.size(); j++) {
                        Circle2 currentCircle = circleList.get(j);
                        Integer[] currentPositionList = currentCircle.getCirclePosition();
                        for (int l = 0; l < currentPositionList.length; l++) {
                            Integer currentValue = currentPositionList[l];
                            if (currentValue != null) {
                                if (currentValue > avg) {
                                    currentPositionList[l] = currentValue - 1;
                                } else if (currentValue < avg) {
                                    currentPositionList[l] = currentValue + 1;
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