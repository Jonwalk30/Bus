package com.badlogic.bus;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class U1Service {

    public BusRoute busRoute;
    private ArrayList<Road> roads = new ArrayList<Road>();
    private ArrayList<Bus> buses = new ArrayList<Bus>();

    public Time startTime = new Time(6,0,0);
    public static Texture busImg = new Texture("bus.png");
    public static Texture busStopImg = new Texture("bus stop.png");

    public U1Service() {

        roads.add(norwoodAve());
        roads.add(bathwickRoad());
        roads.add(pultneyRoad());
        roads.add(clavertonSt());
        roads.add(ambury());
        roads.add(greenPark());
        roads.add(westmorelandRoad());
        roads.add(lowerOldfieldPark());
        roads.add(broughamHayes());
        roads.add(pinesWay());
        roads.add(greenParkBack());
        roads.add(amburyBack());
        roads.add(clavertonStBack());
        roads.add(pultneyRoadBack());
        roads.add(bathwickRoadBack());
        roads.add(norwoodAveBack());

        buses.add(new Bus(1, 100, norwoodAve(), busImg));
        buses.add(new Bus(2, 100, bathwickRoad(), busImg));
        buses.add(new Bus(3, 100, clavertonSt(), busImg));
        buses.add(new Bus(4, 100, ambury(), busImg));
        buses.add(new Bus(5, 100, westmorelandRoad(), busImg));
        buses.add(new Bus(6, 100, broughamHayes(), busImg));
        buses.add(new Bus(7, 100, greenParkBack(), busImg));
        buses.add(new Bus(8, 100, clavertonStBack(), busImg));
        buses.add(new Bus(9, 100, pultneyRoadBack(), busImg));
        buses.add(new Bus(10, 100, bathwickRoadBack(), busImg));

        busRoute = new BusRoute(
                roads,
                buses,
                startTime);

        busRoute.buses.get(0).roadPosition = 0;

    }

    private Road norwoodAve() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        DestBusStop tempBusStop1 = new DestBusStop("-", 100, 100, 100, 0, 0, busStopImg);

        tempBusStops.add(tempBusStop1);

        BusStop tempAveBusStop2 = new BusStop("-", 2, 10, 50, 100, busStopImg);

        tempBusStops.add(tempAveBusStop2);

        Road tempRoad = new Road(
                "-",
                new Location(90,50),
                new Location(90,25),
                (double) 50,
                tempBusStops,
                tempBusStop1);

        return tempRoad;
    }

    private Road norwoodAveBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(90,25),
                new Location(90,50),
                (double) 50,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 2, 2,20, 100, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 2, 2,25, 92, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 2, 5,50, 84, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 2, 15,75, 78, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(90,25),
                new Location(50,75),
                (double) 80,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 50, 5,5, 30, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 5,35, 22, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 5, 5,65, 14,busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 2, 40,95, 6, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(90,25),
                (double) 80,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 2, 20,75, 70, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(50,35),
                (double) 90,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 20, 2,5, 35, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(50,75),
                (double) 90,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonSt() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 4, 10,75, 65, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(35,35),
                (double) 90,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonStBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 15, 2,30, 42, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(50,35),
                (double) 90,
                tempBusStops);

        return tempRoad;
    }

    private Road ambury() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 50,75, 62, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(35,45),
                (double) 60,
                tempBusStops);

        return tempRoad;
    }

    private Road amburyBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(35,35),
                (double) 60,
                tempBusStops);

        return tempRoad;
    }

    private Road greenPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 5, 10,90, 55, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(25,55),
                (double) 60,
                tempBusStops);

        return tempRoad;
    }

    private Road greenParkBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 30, 2,10, 55, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 50, 10,80, 45, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(35,45),
                (double) 60,
                tempBusStops);

        return tempRoad;
    }

    private Road westmorelandRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 15,70, 52, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(20,35),
                (double) 50,
                tempBusStops);

        return tempRoad;
    }

    private Road lowerOldfieldPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 10,20, 48, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("LO", 50, 90,60, 47, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(20,35),
                new Location(10,45),
                (double) 50,
                tempBusStops);

        return tempRoad;
    }

    private Road broughamHayes() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(10,45),
                new Location(12,55),
                (double) 50,
                tempBusStops);

        return tempRoad;
    }

    private Road pinesWay() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 20, 20,10, 47, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 2,40, 46, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(12,55),
                new Location(25,55),
                (double) 50,
                tempBusStops);

        return tempRoad;
    }

}
