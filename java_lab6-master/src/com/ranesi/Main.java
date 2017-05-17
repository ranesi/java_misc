package com.ranesi;

public class Main {

    public static void main(String[] args) {
        //Demonstration of OO programming with
        //  1) the ITEC Course object and
        //  2) the ITEC Course Manager object
        ITECCourseManager mgmt = new ITECCourseManager();
        ITECCourse test = new ITECCourse(
                "Info Tech Concepts",
                "T3050",
                1100,
                30
        );
        test.addStudentName("Max");
        test.addStudentName("Nancy");
        test.addStudentName("Orson");
        mgmt.addCourse(test);
        for (ITECCourse c : mgmt.getCourses()){
            System.out.printf(c.writeCourseInfo());
        }
        System.out.println(mgmt.writeOpenings());
    }
}
