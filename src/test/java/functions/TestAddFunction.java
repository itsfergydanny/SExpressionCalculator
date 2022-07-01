package functions;

import com.sysadamant.sexpressioncalculator.functions.AddFunction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddFunction {
    @Test
    public void testIntegerOverflow() {
        String[] args = new String[]{"add", "2147483647", "1"};

        AddFunction function = new AddFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testInvalidAmountOfArguments() {
        String[] args = new String[]{"add", "1"};

        AddFunction function = new AddFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testWrongTypeOfCalculation() {
        String[] args = new String[]{"exponent", "1", "1"};

        AddFunction function = new AddFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testTwoArgumentAdditionCalculation() {
        String[] args = new String[]{"add", "1", "1"};

        AddFunction function = new AddFunction();

        assertEquals(Optional.of(2), function.evaluate(args));
    }

    @Test
    public void testThreeArgumentAdditionCalculation() {
        String[] args = new String[]{"add", "1", "2", "3"};

        AddFunction function = new AddFunction();

        assertEquals(Optional.of(6), function.evaluate(args));
    }
}
