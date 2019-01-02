package com.badlogic.bus;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class BusStop {

    public String name;
    public double RUPopularity;
    public double DOPopularity;
    public double roadPosition;
    public ArrayList<Student> waitingStudents = new ArrayList<Student>();

    public Texture image;

    public BusStop(String name, double RUPopularity, double DOPopularity, double roadPosition, Texture image) {
        this.name = name;
        this.RUPopularity = RUPopularity;
        this.DOPopularity = DOPopularity;
        this.roadPosition = roadPosition;
        this.image = image;
    }

    public Location location(Road road) {
        double x = road.start.x + ((roadPosition/100) * (road.end.x - road.start.x));
        double y = road.start.y + ((roadPosition/100) * (road.end.y - road.start.y));

        return new Location(x, y);
    }

    public int studentCnt() {
        return waitingStudents.size();
    }

    public void studentRocksUp() {

        Random r = new Random();
        int n;
        n = r.nextInt( (int) rockingUpRate());

        if(n < 1) {
            waitingStudents.add(new Student());
        }
    }

    public double rockingUpRate() {
        return 5 * RUPopularity;
    }

    public double droppingOffRate() {
        // Should be somewhat random
        // Gaussian around DOPopularity
        return DOPopularity / 100;
    }

    /**
     Get the color to be used when printing the number of people waiting at this
     stop, based on the current number of people waiting here.
     */
    public Color color() {
        if (this.studentCnt() < 5) {
            return Color.GREEN;
        } else if (this.studentCnt() < 10) {
            return Color.ORANGE;
        } else {
            return Color.RED;
        }
    }

}