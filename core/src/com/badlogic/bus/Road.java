package com.badlogic.bus;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;

import static java.lang.Math.pow;

public class Road {

    public String name;

    // 25 here == 0.5km

    public Location start;
    public Location end;
    public double trafficMultiplier;
    public ArrayList<BusStop> busStops;
    public DestBusStop destBusStop = null;

    public Road(String name, Location start, Location end, double trafficMultiplier, ArrayList<BusStop> busStops) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.trafficMultiplier = trafficMultiplier;
        this.busStops = busStops;
    }


    public Road(String name, Location start, Location end, double trafficMultiplier, ArrayList<BusStop> busStops, DestBusStop destBusStop) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.trafficMultiplier = trafficMultiplier;
        this.busStops = busStops;
        this.destBusStop = destBusStop;
    }

    public double length() {
        return pow(pow((end.x - start.x), 2) + pow((end.y - start.y), 2), 0.5);
    }

    public ArrayList<Location> busStopLocations() {

        ArrayList<Location> locations = new ArrayList<Location>();

        for (BusStop busStop : busStops) {
            double x = this.start.x + ((busStop.roadPosition/100) * (this.end.x - this.start.x));
            double y = this.start.y + ((busStop.roadPosition/100) * (this.end.y - this.start.y));

            locations.add(new Location(x, y));
        }

        return locations;
    }

    public double traffic(Time time) {

        // f(time 00:00:00) -> number between 0-100

        // hour (0-23) minute (0-59) -> number between 0-100

        // Convert to number between 0 and 1440

        // Double-peaked

        // 9am
        NormalDistribution n1 = new NormalDistribution(540, 60);
        // 5pm
        NormalDistribution n2 = new NormalDistribution(1020, 60);
        // background
        double n3 = 0.1;

        double traffic = 135*n1.density((double) time.minutesPastMidnight()) + 135*n2.density((double) time.minutesPastMidnight()) + n3;
//        if (time.second == 0) {
//            System.out.println("Time: " + time.toString() +  ", Traffic " + traffic);
//        }

        // At the end, multiply by multiplier

        traffic = traffic * trafficMultiplier;

        return traffic < 100 ? traffic : 100;
    }

    public boolean equals(Road road) {
        return this.start.x == road.start.x &&
                this.start.y == road.start.y &&
                this.end.x == road.end.x &&
                this.end.y == road.end.y;
    }


}
