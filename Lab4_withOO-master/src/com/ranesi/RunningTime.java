package com.ranesi;
import java.util.ArrayList;
import java.util.Collections;

public class RunningTime {
    private String name;
    private ArrayList<Double> times;

    RunningTime(String name){
        this.name = name;
        times = new ArrayList<>();
    }
    RunningTime(String name, double time){
        this.name = name;
        this.times = new ArrayList<>();
        this.times.add(time);
    }

    public boolean compareName(RunningTime other){
        return this.name.equals(other.name);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addTime(double time){
        this.times.add(time);
    }

    public double getFastestTime(){
        return Collections.min(this.times);
    }
}
