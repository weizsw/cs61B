import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testIsSame() {
        int a = 1;
        int b = 1;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
