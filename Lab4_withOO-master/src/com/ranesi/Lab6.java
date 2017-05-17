package com.ranesi;
import java.util.*;
//
public class Lab6 {
    static Scanner strScan = new Scanner(System.in);
    static Scanner numScan = new Scanner(System.in);

    public static void problem() {
        //A program which stores times (Double) by location (String)
        ArrayList<RunningTime> rtimes = new ArrayList<>();

        label:
        while (true) {
            String cmd = getCmd();
            switch (cmd) {
                //Switch statement for 1) adding times, 2) displaying times, 3) exiting program
                case "A":
                    //Add a time
                    String name = getName();
                    double time = getTime();
                    if (!rtimes.isEmpty()) {
                        boolean found = false;
                        for (RunningTime rt : rtimes) {
                            if (rt.getName().equals(name)) {
                                rt.addTime(time);
                                found = true;
                            }
                        }
                        if (!found) {
                            rtimes.add(new RunningTime(name, time));
                        }
                    } else {
                        rtimes.add(new RunningTime(name, time));
                    }
                    break;
                case "D":
                    //Display times
                    System.out.println("FASTEST TIMES PER LOCATION");
                    for (RunningTime rt : rtimes) {
                        System.out.println(String.format("%s, %.2f", rt.getName(), rt.getFastestTime()));
                    }
                    break;
                case "Q":
                    //Quit
                    break label;
            }
        }

        strScan.close();
        numScan.close();
    }

    public static String getCmd() {
        //Main menu UI; built-in validation
        while(true) {
            System.out.println("(A)dd time, (D)isplay times, (Q)uit: ");
            String cmd = strScan.next();
            if (cmd.equals("A") || cmd.equals("D") || cmd.equals("Q")) {
                return cmd;
            } else {
                System.out.println("Unrecognized input!");
                strScan.next();
            }
        }
    }

    public static double getTime() {
        //Get/validate times
        while(true) {
            System.out.println("Enter running time: ");
            try {
                double ret = numScan.nextDouble();
                return ret;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry.");
                numScan.next(); //consume invalid token
            }
        }
    }

    public static String getName(){
        //Get name of lake
        System.out.println("Enter name of lake: ");
        return strScan.next();
    }
}
