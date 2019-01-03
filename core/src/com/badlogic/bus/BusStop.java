package com.badlogic.bus;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;

public class BusStop {

    public String name;
    public double RUPopularity;
    public double DOPopularity;
    public double roadPosition;
    public ArrayList<Student> waitingStudents = new ArrayList<Student>();

    public int estMinsToDest;

    public Texture image;

    public BusStop(String name, double RUPopularity, double DOPopularity, double roadPosition, int estMinsToDest, Texture image) {
        this.name = name;
        this.RUPopularity = RUPopularity;
        this.DOPopularity = DOPopularity;
        this.roadPosition = roadPosition;
        this.estMinsToDest = estMinsToDest;
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

    public void studentRocksUp(Time t) {

        Random r = new Random();
        int n;
        n = r.nextInt(100);

        if(n < this.rockingUpRate(t)) {
            waitingStudents.add(new Student());
        }
    }

    public double rockingUpRate(Time t) {
        // f: t, c -> [0-1]

        // We need a lot of distributions for each of the lecture times
        NormalDistribution n_8 = new NormalDistribution(555, 10);
        NormalDistribution n_9 = new NormalDistribution(615, 10);
        NormalDistribution n_10 = new NormalDistribution(675, 10);
        NormalDistribution n_11 = new NormalDistribution(735, 10);
        NormalDistribution n_12 = new NormalDistribution(795, 10);
        NormalDistribution n_13 = new NormalDistribution(855, 10);
        NormalDistribution n_14 = new NormalDistribution(915, 10);
        NormalDistribution n_15 = new NormalDistribution(975, 10);
        NormalDistribution n_16 = new NormalDistribution(1035, 10);
        NormalDistribution n_17 = new NormalDistribution(1095, 10);
        NormalDistribution n_18 = new NormalDistribution(1155, 10);
        double background = 0.05;

        double estArrivalTime = t.minutesPastMidnight() + this.estMinsToDest;
        double popularity =
          (0.10 * n_8.density(estArrivalTime)) +
          (0.25 * n_9.density(estArrivalTime)) +
          (0.2 * n_10.density(estArrivalTime)) +
          (0.15 * n_11.density(estArrivalTime)) +
          (0.05 * n_12.density(estArrivalTime)) +
          (0.05 * n_13.density(estArrivalTime)) +
          (0.05 * n_14.density(estArrivalTime)) +
          (0.05 * n_15.density(estArrivalTime)) +
          (0.05 * n_16.density(estArrivalTime)) +
          (0.05 * n_17.density(estArrivalTime)) +
          (0.05 * n_18.density(estArrivalTime));

        popularity *= 100;
        popularity += background;

        if (t.second == 0 && this.name == "LO") {
          System.out.println(t.toString());
          System.out.println(popularity);
        }

        popularity *= RUPopularity/100;

        return popularity < 100 ? popularity : 100;
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
