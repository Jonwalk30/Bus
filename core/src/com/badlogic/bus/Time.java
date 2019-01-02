package com.badlogic.bus;

public class Time {

    public int hour;
    public int minute;
    public int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void increment() {

        if (second < 60) {
            second++;
        } else {
            second = 0;
            if (minute < 60) {
                minute++;
            } else {
                minute = 0;
                if (hour < 24) {
                    hour++;
                } else {
                    hour = 0;
                }
            }
        }
    }

    public int minutesPastMidnight() {
        return 60*hour + minute;
    }

    public String toString() {
        return this.hour+":"+this.minute+":"+this.second;
    }


}
