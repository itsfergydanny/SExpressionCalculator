package evaluator;

import com.sysadamant.sexpressioncalculator.evaluator.Evaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEvaluator {
    @Test
    public void testTwoArgumentMixedExpression() {
        String[] args = new String[]{"(multiply 2 (add (multiply 2 3) 8))"};
        assertEquals("28", new Evaluator(args).eval());
    }

    @Test
    public void testThreeArgumentMixedExpression() {
        String[] args = new String[]{"(add 1 2 3 4 (multiply 2 3 5))"};
        assertEquals("40", new Evaluator(args).eval());
    }

    @Test
    public void testComplexExpression() {
        String[] args = new String[]{"subtract 1000000 (add 10 (multiply 10 (exponent 2 (add 2 4)))"};
        assertEquals("999350", new Evaluator(args).eval());
    }
}
