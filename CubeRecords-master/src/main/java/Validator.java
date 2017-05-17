import java.text.NumberFormat;
import java.util.InputMismatchException;

/**
 * Created by RH Anesi on 4/17/2017.
 */
public class Validator {

    public static boolean validString(String s) {
        //Determines if string x is valid (length > 0, in this case)
        if (s.length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean validDouble(String s) {
        //Determines if string is parsable to double
        try {
            Double.parseDouble(s);
            return true;
        } catch (InputMismatchException | NumberFormatException e) {
            return false;
        }
    }
}
