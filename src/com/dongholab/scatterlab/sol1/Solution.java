package com.dongholab.scatterlab.sol1;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

interface DateUtil {
    public int getPoint();
    public boolean isBadWeather();
}

class Weather implements DateUtil {

    enum WEATHER {
        SUNNY(20),
        CLOUDY_MIN(20),
        CLOUDY_MAX(17),
        RANNY(10);
        private final int value;
        WEATHER(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    enum RAIN_STATUS {
        NONE(0),
        RAIN(5),
        SNOW(14);
        private final int value;
        RAIN_STATUS(int value) { this.value = value; }
        public int getValue() { return this.value; }
    }
    private WEATHER weather;
    private RAIN_STATUS rainStatus;

    Weather(int weatherCode, int rainCode) {
        WEATHER weather;
        RAIN_STATUS rainStatus;
        switch (weatherCode) {
            case 2:
                weather = WEATHER.CLOUDY_MIN;
                break;
            case 3:
                weather = WEATHER.CLOUDY_MAX;
                break;
            case 4:
                weather = WEATHER.RANNY;
                break;
            case 1:
            default:
                weather = WEATHER.SUNNY;
                break;
        }
        switch (rainCode) {
            case 1:
                rainStatus = RAIN_STATUS.RAIN;
                break;
            case 2:
                rainStatus = RAIN_STATUS.SNOW;
                break;
            case 0:
            default:
                rainStatus = RAIN_STATUS.NONE;
                break;
        }
        this.weather = weather;
        this.rainStatus = rainStatus;
    }

    @Override
    public int getPoint() {
        return Math.max(Math.min(this.weather.getValue() + this.rainStatus.getValue(), 20), 0);
    }

    @Override
    public boolean isBadWeather() {
        if (WEATHER.RANNY == this.weather || RAIN_STATUS.RAIN == this.rainStatus) {
            return true;
        } else {
            return false;
        }
    }
}

class Temperature implements DateUtil {
    private int temp;

    Temperature(int temp) {
        this.temp = temp;
    }

    @Override
    public int getPoint() {
        return Math.max(0, 20 - Math.abs(22 - this.temp));
    }

    @Override
    public boolean isBadWeather() {
        if (this.temp >= 30 || this.temp <= 0) {
            return true;
        } else {
            return false;
        }
    }
}

class Pair implements Comparable<Pair>{
    static final int[] MOST_DAY_OF_WEEK = new int[]{0, 1, 3, 2, 5, 6, 4};
    private int point;
    private int week;

    Pair(int point, int week) {
        this.point = point;
        this.week = week;
    }

    public int getPoint() {
        return this.point;
    }

    public int getWeek() {
        return this.week;
    }

    @Override
    public int compareTo(Pair p) {
        int diff = p.point - this.point;
        if (diff == 0) {
            return MOST_DAY_OF_WEEK[p.getWeek()] - MOST_DAY_OF_WEEK[getWeek()];
        } else {
            return diff;
        }
    }
}

public class Solution {
    static final int NONE_DATA = -1;

    public int[] solution(int[][] data) {
        int[] answer = new int[2];
        AtomicReference<Pair> notRecommandValueAtomic = new AtomicReference<>(null);
        Pair reccomadPair = IntStream.range(0, data.length).mapToObj(week -> {
            var weekData = data[week];
            Weather currentWeather = new Weather(weekData[0], weekData[1]);
            Temperature currentTemperature = new Temperature(weekData[2]);
            int sumPoint = currentWeather.getPoint() + currentTemperature.getPoint();
            Pair newData = new Pair(sumPoint, week);

            if (currentTemperature.isBadWeather() || currentWeather.isBadWeather()) {
                Pair notRecommandValue = notRecommandValueAtomic.get();
                if (notRecommandValue == null || notRecommandValue.compareTo(newData) < 0) {
                    notRecommandValueAtomic.set(newData);
                }
            }
            return newData;
        }).sorted().findFirst().get();

        answer[0] = reccomadPair.getWeek();

        if (notRecommandValueAtomic.get() == null) {
            answer[1] = NONE_DATA;
        } else {
            answer[1] = notRecommandValueAtomic.get().getWeek();
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[][]{{1, 0, 11}, {3, 1, 15}, {2, 0, 16}, {4, 0, 17}, {2, 0, 15}, {2, 1, 14}, {2, 0, 12}})));
    }
}
