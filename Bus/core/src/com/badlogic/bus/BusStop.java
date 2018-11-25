package com.badlogic.bus;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Date;

public class BusStop {

    public String name;
    public double popularity;
    public double roadPosition;
    public ArrayList<Student> waitingStudents = new ArrayList<Student>();

    public Texture image;

    public BusStop(String name, double popularity, double roadPosition, Texture image) {
        this.name = name;
        this.popularity = popularity;
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
        return 5 * popularity;
    }

    public double droppingOffRate() {
        return 5 * popularity;
    }

}
