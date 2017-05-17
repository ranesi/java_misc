package com.clara;

import java.util.Scanner;
import java.lang.Character;

/**
 * Created by we4954cp on 8/31/2016.
 */
public class CreditCard {

    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ask user for credit card number. store number as a String.
        System.out.println("Please enter the credit card number, digits only:");
        String ccNumber = stringScanner.nextLine();
        boolean isValid = isValidCreditCard(ccNumber);

        if (isValid) {
            System.out.println("This seems to be a valid credit card number");
        } else {
            System.out.println("This is **not** a valid credit card number.");
        }

        stringScanner.close();
    }

    public static boolean isValidCreditCard(String cc) {
        //Determines whether a card is valid using the LUHN formula
        if (cc.length() >= 13 && cc.length() <= 16 ) {
            int[] a = new int[cc.length()];
            for (int x = 0; x < cc.length(); x++) {
                a[x] = Integer.parseInt(Character.toString(cc.charAt(x)));
            }
            for (int x = 0; x < a.length; x++)
                if (x % 2 == 0) a[x] *= 2;
            String temp = "";
            for (int x = 0; x < a.length; x++)
                temp += Integer.toString(a[x]);
            int sum = 0;
            for (int x = 0; x < temp.length(); x++)
                sum += Integer.parseInt(Character.toString(temp.charAt(x)));
            if (sum % 10 == 0)
                return true;
            else
                return false;
        }
        return false;
    }
}
