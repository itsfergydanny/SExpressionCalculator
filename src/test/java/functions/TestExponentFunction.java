package functions;

import com.sysadamant.sexpressioncalculator.functions.ExponentFunction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExponentFunction {
    @Test
    public void testIntegerOverflow() {
        String[] args = new String[]{"exponent", "100", "100"};

        ExponentFunction function = new ExponentFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testInvalidAmountOfArguments() {
        String[] args = new String[]{"exponent", "2"};

        ExponentFunction function = new ExponentFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testWrongTypeOfCalculation() {
        String[] args = new String[]{"multiply", "2", "5"};

        ExponentFunction function = new ExponentFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testTwoArgumentExponentCalculation() {
        String[] args = new String[]{"exponent", "2", "5"};

        ExponentFunction function = new ExponentFunction();

        assertEquals(Optional.of(32), function.evaluate(args));
    }

    @Test
    public void testThreeArgumentExponentCalculation() {
        String[] args = new String[]{"exponent", "2", "5", "2"};

        ExponentFunction function = new ExponentFunction();

        assertEquals(Optional.of(1024), function.evaluate(args));
    }
}
