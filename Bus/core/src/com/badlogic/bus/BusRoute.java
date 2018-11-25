package com.badlogic.bus;

import java.util.ArrayList;

public class BusRoute {

    public ArrayList<Road> roads;
    public ArrayList<Bus> buses;
    public Time time;

    public BusRoute(ArrayList<Road> roads, ArrayList<Bus> buses, Time time) {
        this.roads = roads;
        this.buses = buses;
        this.time = time;
    }

    public void simulateASecond() {
        for (Bus bus : buses) {
            bus.simulateASecond(this);
        }
        for(Road road : roads) {
            for (BusStop stop : road.busStops) {
                stop.studentRocksUp();
            }
        }
    }
}
