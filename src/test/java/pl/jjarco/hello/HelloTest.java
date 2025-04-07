package pl.jjarco.hello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloTest {

    @Test
    void myFirstTest() {
        int a = 2;

        int b = 4;

        int result = a + b;

        assert 6 == result;
    }

    @Test
    void myFirstFailingTest() {
        int a = 2;
        int b = 4;

        int result = a - b;

        assert 6 == result;
    }

    @Test
    void myMoreExplicitAssertion() {
        assertTrue(false, "Your condition do not match");
    }
}
