package com.ranesi;
import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int getPositiveIntInput(String question) {
        while (true) {
            try {
                System.out.println(question);
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                if (intInput >= 0) {
                    return intInput;
                } else {
                    System.out.println("Please enter a positive number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erroneous input");
            }
        }
    }

    public static String getStringInput(String question) {
        if (question != null) {
            System.out.println(question);
        }
        String entry = scanner.nextLine();
        return entry;
    }
}
