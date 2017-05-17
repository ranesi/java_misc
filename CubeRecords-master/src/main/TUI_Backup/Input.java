/**
 * Created by RH Anesi on 4/7/2017.
 */

import java.util.*;
public class Input {

    static Scanner scanner = new Scanner(System.in);

    public static String stringInput(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    public static int integerInput(String msg) {
        while(true) {
            try {
                System.out.println(msg);
                int ret = scanner.nextInt();
                return ret;
            } catch (InputMismatchException ime) {
                System.out.println("Entry must be a valid integer.");
                scanner.next();
            }
        }
    }

    public static float floatInput(String msg) {
        while(true) {
            try {
                System.out.println(msg);
                float ret = scanner.nextFloat();
                return ret;
            } catch (InputMismatchException ime) {
                System.out.println("Entry must be a valid float.");
                scanner.next(); // consume token
            }
        }
    }
}
