import com.clara.DistanceConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class MetersToFeetTest {

    @Test
    public void testMetersToFeet() {

        // Check to see if the expected result is the same
        // as the actual result from the method.
        // So we expect metersToFeet with an argument of 10 to return 30
        assertEquals(-3, DistanceConverter.metersToFeet(-1));
        assertEquals(0, DistanceConverter.metersToFeet(0));
        assertEquals(30, DistanceConverter.metersToFeet(10));
        assertEquals(150, DistanceConverter.metersToFeet(50));
        assertEquals(300, DistanceConverter.metersToFeet(100));
    }
}
