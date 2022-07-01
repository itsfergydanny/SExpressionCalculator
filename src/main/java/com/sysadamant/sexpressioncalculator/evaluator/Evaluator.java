package com.sysadamant.sexpressioncalculator.evaluator;

import com.sysadamant.sexpressioncalculator.functions.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Evaluator {
    @Getter
    private final Map<String, Function> FUNCTIONS = new HashMap<>();

    public Evaluator() {
        FUNCTIONS.put("add", new AddFunction());
        FUNCTIONS.put("multiply", new MultiplyFunction());
        FUNCTIONS.put("subtract", new SubtractFunction());
        FUNCTIONS.put("exponent", new ExponentFunction());
    }

    public String eval(String[] args) {
        if (args.length < 1) {
            return "Error: No arguments passed. The minimum amount of arguments to pass is 1.";
        }

        String line = args[0].toLowerCase();
        args = line.split("\\s+");

        if (args.length == 1) {
            try {
                int value = Integer.parseInt(args[0]);
                return value + "";
            } catch (NumberFormatException ignore) {
                return "Error: Invalid integer specified.";
            }
        }

        return processNestedExpression(line);
    }

    private String processNestedExpression(String line) {
        if (!line.contains("(")) {
            String[] args = line.split("\\s+");
            if (args.length == 1) {
                try {
                    return Integer.valueOf(args[0]) + "";
                } catch (NumberFormatException ignore) {}
            }
            if (args.length >= 3) {
                Optional<Integer> result = getCalculationResult(args);
                if (result.isPresent()) {
                    return result.get() + "";
                }
            }
            return "Error: Invalid expression. Please make sure each opening parenthesis is closed.";
        }

        int lastClosingParenthesis = line.length() - 1;

        for (int i = line.length() - 1; i >= 0; i--) {
            String c = line.charAt(i) + "";

            if (c.equals(")")) {
                lastClosingParenthesis = i;
                continue;
            }

            if (c.equals("(")) {
                String substring = line.substring(i, lastClosingParenthesis + 1);
                String[] args = substring.replace("(", "").replace(")", "").split("\\s+");
                Optional<Integer> result = getCalculationResult(args);
                if (result.isEmpty()) {
                    return "Error: Invalid expression. Please make sure each opening parenthesis is closed.";
                }
                line = line.replace(substring, result.get() + "");
                return processNestedExpression(line);
            }
        }

        return processNestedExpression(line);
    }

    private Optional<Integer> getCalculationResult(String[] args) {
        return switch (args[0]) {
            case "multiply" -> FUNCTIONS.get("multiply").evaluate(args);
            case "add" -> FUNCTIONS.get("add").evaluate(args);
            case "subtract" -> FUNCTIONS.get("subtract").evaluate(args);
            case "exponent" -> FUNCTIONS.get("exponent").evaluate(args);
            default -> Optional.empty();
        };
    }
}
