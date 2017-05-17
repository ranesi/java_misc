package com.ranesi;
import java.util.ArrayList;

public class ITECCourse {

    private String className;
    private String room;
    private int code;
    private int maxStudents;
    private ArrayList<String> studentNames;

    ITECCourse(String className, String room, int code, int maxStudents){
        this.className = className;
        this.room = room;
        this.code = code;
        this.maxStudents = maxStudents;
        this.studentNames = new ArrayList<>();
    }

    public String writeCourseInfo(){
        //output a string representing all information for a course
        String output = String.format("INFO FOR ITEC %d\n", code);
        output += String.format("%s\nROOM: %s\nMAX STUDENTS: %d\n",
                className, room, maxStudents);
        if (!studentNames.isEmpty()){
            output += "ENROLLED STUDENTS:\n";
            for (String s : studentNames)
                output += s + "\n";
        } else {
            output += "No students yet enrolled.";
        }
        return output;
    }

    public int getOpenings(){
        return this.maxStudents - this.studentNames.size();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void addStudentName(String name) {
        this.studentNames.add(name);
    }

}
