import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    static CharacterComparator nn = new OffByN(5);
    @Test
    public void testEqualChars() {
        assertTrue(nn.equalChars('a', 'f'));
        assertTrue(nn.equalChars('f', 'a'));
        assertFalse(nn.equalChars('f', 'h'));
    }
}
