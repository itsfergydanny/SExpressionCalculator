package functions;

import com.sysadamant.sexpressioncalculator.functions.MultiplyFunction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultiplyFunction {
    @Test
    public void testIntegerOverflow() {
        String[] args = new String[]{"multiply", "2147483647", "2"};

        MultiplyFunction function = new MultiplyFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testInvalidAmountOfArguments() {
        String[] args = new String[]{"multiply", "2"};

        MultiplyFunction function = new MultiplyFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testWrongTypeOfCalculation() {
        String[] args = new String[]{"subtract", "2", "3"};

        MultiplyFunction function = new MultiplyFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testTwoArgumentMultiplicationCalculation() {
        String[] args = new String[]{"multiply", "2", "3"};

        MultiplyFunction function = new MultiplyFunction();

        assertEquals(Optional.of(6), function.evaluate(args));
    }

    @Test
    public void testThreeArgumentMultiplicationCalculation() {
        String[] args = new String[]{"multiply", "2", "4", "0"};

        MultiplyFunction function = new MultiplyFunction();

        assertEquals(Optional.of(0), function.evaluate(args));
    }
}
