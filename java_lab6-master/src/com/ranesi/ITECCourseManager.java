package com.ranesi;
import java.util.ArrayList;

public class ITECCourseManager {
    ArrayList<ITECCourse> courses;

    ITECCourseManager(){
        courses = new ArrayList<>();
    }

    public String writeOpenings(){
        String output = "FREE SPACES PER CLASS\n";
        if (!courses.isEmpty()){
            for (ITECCourse c : courses){
                output += String.format("%s:\t%d\n", c.getClassName(), c.getOpenings());
            }
        } else {
            output += "No classes stored!";
        }
        return output;
    }

    public ArrayList<ITECCourse> getCourses() {
        return courses;
    }

    public void addCourse(ITECCourse c) {
        this.courses.add(c);
    }
}
