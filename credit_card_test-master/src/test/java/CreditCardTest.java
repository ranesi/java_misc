import com.clara.CreditCard;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class CreditCardTest {

    @Test
    public void testIsCreditCardValid(){
        //Some valid credit card numbers
        //We can write individual assertTrue statements....
        assertTrue("4123123412341236 should be valid", CreditCard.isValidCreditCard("4123123412341236"));
        assertTrue("4000111122223339 should be valid", CreditCard.isValidCreditCard("4000111122223339"));
        assertTrue("4123456789012349 should be valid", CreditCard.isValidCreditCard("4123456789012349"));

        //Invalid - too short
        assertFalse("4445556666 should be invalid - too short", CreditCard.isValidCreditCard("4445556666"));
        assertFalse("444555666677777 should be invalid - too short", CreditCard.isValidCreditCard("444555666677777"));

        //Invalid - doesn't start with 4
        assertFalse("5123189756461236 should be invalid - doesn't start with 4", CreditCard.isValidCreditCard("5123189756461236"));

        //Invalid - check digit wrong
        // ... or create an array or list of invalid inputs and use a loop to test them all
        String[] invalidInputs = {"4567568658341240", "4658907842345226", "4904563343456566"};

        // Java's for each loop
        for (String invalid : invalidInputs) {
            assertFalse(invalid + " should be invalid - check digit is wrong", CreditCard.isValidCreditCard(invalid));
        }

    }


}
