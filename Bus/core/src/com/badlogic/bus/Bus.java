package com.badlogic.bus;

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
    public boolean waiting = false;

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

        // If I'm at a bus stop
        if (isAtBusStop()) {

            // If there aren't any buses already here,
            if (!busAlreadyWaiting(route)) {

                // If the bus isn't full
                if (!full()) {
                    waiting = true;
                    // If there are students here
                    if (atBusStop().studentCnt() > 0) {

                        // Random chance of dropping students off
                        dropsRandomStudentOff(atBusStop());

                        System.out.println(atBusStop().waitingStudents.size());

                        // If I haven't been waiting for 5 seconds
                        if (secondsWaited < 5) {
                            // Stay here and increment how long I've been waiting
                            secondsWaited++;
                            totalSecondsWaited++;
                        } else {
                            // Load a student on board and remove them from the bus stop
                            students.add(atBusStop().waitingStudents.get(atBusStop().waitingStudents.size()-1));
                            atBusStop().waitingStudents.remove(atBusStop().waitingStudents.size()-1);
                            secondsWaited = 0;
                            totalSecondsWaited++;
                        }
                    } else {
                        move();
                    }
                // Else move
                } else {
                    // Random chance of dropping students off
                    dropsRandomStudentOff(atBusStop());
                    move();
                }
            // Else move
            } else {
                move();
            }

            // Else move
        // Else if I'm at the end of a road, skip to the next road
        } else if (roadPosition >= 99) {
            switchRoad(route);
        // Else move
        } else {
            move();
        }

    }

    public boolean busAlreadyWaiting(BusRoute route) {
        for (Bus bus : route.buses) {
            if (bus.ID != this.ID &&
                    bus.currentRoad.equals(this.currentRoad) &&
                    this.roadPosition <= bus.roadPosition + maxMoveIncrement()/2  &&
                    this.roadPosition > bus.roadPosition - maxMoveIncrement()/2) {
                if (bus.waiting) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isAtBusStop() {
        for (BusStop stop : currentRoad.busStops) {
            if (this.roadPosition <= stop.roadPosition + maxMoveIncrement()/2  &&
                    this.roadPosition > stop.roadPosition - maxMoveIncrement()/2) {
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

    public boolean isAtDestBusStop() {
        if (this.roadPosition <= currentRoad.destBusStop.roadPosition + maxMoveIncrement()/2  &&
                this.roadPosition > currentRoad.destBusStop.roadPosition - maxMoveIncrement()/2) {
            return true;
        }
        return false;
    }

    public boolean atDestBusStop() {
        if (this.roadPosition <= currentRoad.destBusStop.roadPosition + maxMoveIncrement()/2  &&
                this.roadPosition > currentRoad.destBusStop.roadPosition - maxMoveIncrement()/2) {
            return true;
        }
        return false;
    }


    public void move() {
        waiting = true;
        secondsWaited = 0;
        totalSecondsWaited = 0;
        roadPosition = roadPosition + (maxMoveIncrement()*(100-currentRoad.traffic())/100);
    }

    public void switchRoad(BusRoute route) {

        roadPosition = 0;
        waiting = true;
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

    public void dropsRandomStudentOff(BusStop stop) {
        Random r = new Random();
        int n;
        n = r.nextInt( (int) (stop.droppingOffRate()));
        if(n < 100) {
            if(studentCnt() > 0) {
                students.remove(students.size() - 1);
            }
        }
    }

    //public boolean sameRoadPositionAs(Bus bus) {}

}
