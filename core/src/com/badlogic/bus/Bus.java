package com.badlogic.bus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class Bus {

    public int ID;
    public int capacity;
    public ArrayList<Student> students = new ArrayList<Student>();
    public Road currentRoad;
    public double roadPosition = 0;
    public double speed = 0;
    // Converted from miles per hour
    public double baseSpeed = 0.02;
    public int secondsWaited = 0;
    public int totalSecondsWaited = 0;
    public int studentsLeftToDropOff = 0;

    public Texture image;

    public Bus(int ID, int capacity, Road startingRoad, Texture image) {
        this.ID = ID;
        this.capacity = capacity;
        this.currentRoad = startingRoad;
        this.image = image;
    }

    public Location location() {
        double x = currentRoad.start.x + ((roadPosition/100) * (currentRoad.end.x - currentRoad.start.x));
        double y = currentRoad.start.y + ((roadPosition/100) * (currentRoad.end.y - currentRoad.start.y));

        return new Location(x, y);
    }

    public int studentCnt() {
        return students.size();
    }

    public void simulateASecond(BusRoute route) {

        System.out.println(ID);

        // If I'm at a bus stop
        if (isAtBusStop()) {
            handleAtBusStop(route);
            // Else if I'm at the end of a road, skip to the next road
        } else if (roadPosition >= 99) {
            switchRoad(route);
            // Else move
        } else {
            totalSecondsWaited = 0;
            move();
        }

    }

    private void handleAtBusStop(BusRoute route) {

        // If there aren't any buses already here,
        if (!busAlreadyWaiting(route)) {
            handleNoBusesAlreadyHere();
            // Else move
        } else {
            totalSecondsWaited = 0;
            move();
        }
    }

    private void handleNoBusesAlreadyHere() {

        if ((totalSecondsWaited == 0 || studentsLeftToDropOff > 0) && studentCnt() > 0) {
            System.out.println("Dropping off");
            handleDropOff();
        } else if (studentsLeftToDropOff == 0 && !full() && atBusStop().studentCnt() > 0) {
            System.out.println("Picking up");
            handlePickUp();
        } else {
            System.out.println("Moving");
            totalSecondsWaited = 0;
            move();
        }
    }

    private void handlePickUp() {

        // If I haven't been waiting for 5 seconds
        if (secondsWaited < 5) {
            // Stay here and increment how long I've been waiting
            secondsWaited++;
            totalSecondsWaited++;
        } else {
            // Load a student on board and remove them from the bus stop
            students.add(atBusStop().waitingStudents.get(atBusStop().studentCnt() - 1));
            atBusStop().waitingStudents.remove(atBusStop().studentCnt() - 1);
            secondsWaited = 0;
            totalSecondsWaited++;
        }

    }

    private void handleDropOff() {

        if (atBusStop() instanceof DestBusStop) {
            System.out.println("At a destination bus stop");
            students.remove(students.size() - 1);
            studentsLeftToDropOff = students.size();
            totalSecondsWaited++;
        } else if (totalSecondsWaited == 0) {
            studentsLeftToDropOff = (int) (atBusStop().droppingOffRate() * studentCnt());
            System.out.println("Gonna drop off " + studentsLeftToDropOff + " students");
            totalSecondsWaited++;
        } else if (studentsLeftToDropOff > 0) {
            System.out.println("Dropping a student off");
            totalSecondsWaited++;
            students.remove(students.size() - 1);
            studentsLeftToDropOff--;
            System.out.println("Just " + studentsLeftToDropOff + " left now");
        }
    }

    public boolean busAlreadyWaiting(BusRoute route) {

        for (Bus bus : route.buses) {
            if (bus.ID != this.ID &&
                    bus.currentRoad.equals(this.currentRoad) &&
                    this.roadPosition <= bus.roadPosition + (maxMoveIncrement()*(100-currentRoad.traffic())/100)/2  &&
                    this.roadPosition > bus.roadPosition - (maxMoveIncrement()*(100-currentRoad.traffic())/100)/2) {
                if (bus.totalSecondsWaited > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAtBusStop() {
        for (BusStop stop : currentRoad.busStops) {
            if (this.roadPosition <= stop.roadPosition + (maxMoveIncrement()*(100-currentRoad.traffic())/100)/2  &&
                    this.roadPosition > stop.roadPosition - (maxMoveIncrement()*(100-currentRoad.traffic())/100)/2) {
                return true;
            }
        }
        return false;
    }

    public BusStop atBusStop() {
        for (BusStop stop : currentRoad.busStops) {
            if (this.roadPosition <= stop.roadPosition + maxMoveIncrement()/2  &&
                    this.roadPosition > stop.roadPosition - maxMoveIncrement()/2) {
                return stop;
            }
        }
        return null;
    }

    public void move() {
        secondsWaited = 0;
        totalSecondsWaited = 0;
        roadPosition = roadPosition + (maxMoveIncrement()*(100-currentRoad.traffic())/100);
    }

    public void switchRoad(BusRoute route) {

        roadPosition = 0;
        secondsWaited = 0;
        totalSecondsWaited = 0;

        for (int i = 0; i < route.roads.size(); i++) {

            if (currentRoad.equals(route.roads.get(i))) {
                if (i < route.roads.size() - 1) {
                    currentRoad = route.roads.get(i+1);
                    break;
                } else {
                    currentRoad = route.roads.get(0);
                    break;
                }
            }
        }
    }

    public double maxMoveIncrement() {
        return 100*((baseSpeed*100)/currentRoad.length());
    }

    public boolean full() {
        return (studentCnt() == capacity);
    }

    //public boolean sameRoadPositionAs(Bus bus) {}

    public Color color() {
        if (this.studentCnt() < 50) {
            return Color.GREEN;
        } else if (this.studentCnt() < 75) {
            return Color.ORANGE;
        } else {
            return Color.RED;
        }
    }

}