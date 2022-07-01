package functions;

import com.sysadamant.sexpressioncalculator.functions.SubtractFunction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSubtractFunction {
    @Test
    public void testIntegerOverflow() {
        String[] args = new String[]{"subtract", "-2147483648", "1"};

        SubtractFunction function = new SubtractFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testInvalidAmountOfArguments() {
        String[] args = new String[]{"subtract", "10"};

        SubtractFunction function = new SubtractFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testWrongTypeOfCalculation() {
        String[] args = new String[]{"add", "10", "4"};

        SubtractFunction function = new SubtractFunction();

        assertEquals(Optional.empty(), function.evaluate(args));
    }

    @Test
    public void testTwoArgumentSubtractionCalculation() {
        String[] args = new String[]{"subtract", "10", "4"};

        SubtractFunction function = new SubtractFunction();

        assertEquals(Optional.of(6), function.evaluate(args));
    }

    @Test
    public void testThreeArgumentSubtractionCalculation() {
        String[] args = new String[]{"subtract", "30", "20", "1"};

        SubtractFunction function = new SubtractFunction();

        assertEquals(Optional.of(9), function.evaluate(args));
    }
}
