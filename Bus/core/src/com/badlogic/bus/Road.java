package com.badlogic.bus;

import java.util.ArrayList;

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

    public double traffic() {
        return 0;
    }

    public boolean equals(Road road) {
        return this.start.x == road.start.x &&
                this.start.y == road.start.y &&
                this.end.x == road.end.x &&
                this.end.y == road.end.y;
    }


}

//NORWOODAVENUE,
//BATHWICKHILL,
//PULTNEYROAD,
//CLAVERTONSTREET,
//LOWERBRISTOLROAD,
//WESTMORELANDROAD,
//LOWEROLDFIELDPARK,
//BROUGHAMHAYES,
//PINESWAY,
//MIDLANDBRIDGEROAD,
//GREENPARKROAD,
//CORNSTREET,
//BROADQUAY
