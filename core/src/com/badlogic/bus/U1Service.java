package com.badlogic.bus;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class U1Service {

    public BusRoute busRoute;
    private ArrayList<Road> roads = new ArrayList<Road>();
    private ArrayList<Bus> buses = new ArrayList<Bus>();

    public static Time startTime = new Time(0,0,0);
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
        buses.add(new Bus(2, 100, greenPark(), busImg));
        buses.add(new Bus(3, 100, clavertonStBack(), busImg));

        busRoute = new BusRoute(
                roads,
                buses,
                startTime);

        busRoute.buses.get(0).roadPosition = 0;

    }

    private Road norwoodAve() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        DestBusStop tempBusStop1 = new DestBusStop("-", 100, 0, busStopImg);

        tempBusStops.add(tempBusStop1);

        BusStop tempAveBusStop2 = new BusStop("-", 10, 50, busStopImg);

        tempBusStops.add(tempAveBusStop2);

        Road tempRoad = new Road(
                "-",
                new Location(90,50),
                new Location(90,25),
                (double) 10,
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
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 20, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 25, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 50, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(90,25),
                new Location(50,75),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 5, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 35, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 65, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 95, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(90,25),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(50,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 5, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(50,75),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonSt() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(35,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonStBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 30, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(50,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road ambury() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(35,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road amburyBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(35,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road greenPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 90, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(25,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road greenParkBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 80, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(35,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road westmorelandRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 70, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(20,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road lowerOldfieldPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 20, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 60, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(20,35),
                new Location(10,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road broughamHayes() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(10,45),
                new Location(12,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pinesWay() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 10, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 40, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(12,55),
                new Location(25,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

}
