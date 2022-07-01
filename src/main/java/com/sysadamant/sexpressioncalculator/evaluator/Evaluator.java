package com.sysadamant.sexpressioncalculator.evaluator;

import com.sysadamant.sexpressioncalculator.functions.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Evaluator {
    private String[] args;

    @Getter
    private final Map<String, Function> FUNCTIONS = new HashMap<>();

    public Evaluator(String[] args) {
        this.args = args;
        FUNCTIONS.put("add", new AddFunction());
        FUNCTIONS.put("multiply", new MultiplyFunction());
        FUNCTIONS.put("subtract", new SubtractFunction());
        FUNCTIONS.put("exponent", new ExponentFunction());
    }

    public String eval() {
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
                return "Error: Invalid number specified. Number must be an Integer!";
            }
        }

        return processFurthestFunction(line);
    }

    private String processFurthestFunction(String line) {
        if (!line.contains("(")) {
            String[] argz = line.split("\\s+");
            if (argz.length == 1) {
                try {
                    return Integer.valueOf(argz[0]) + "";
                } catch (NumberFormatException ignore) {}
            }
            if (argz.length >= 3) {
                Optional<Integer> result = switch (argz[0]) {
                    case "multiply" -> FUNCTIONS.get("multiply").evaluate(argz);
                    case "add" -> FUNCTIONS.get("add").evaluate(argz);
                    case "subtract" -> FUNCTIONS.get("subtract").evaluate(argz);
                    case "exponent" -> FUNCTIONS.get("exponent").evaluate(argz);
                    default -> Optional.empty();
                };
                if (result.isPresent()) {
                    return result.get() + "";
                }
            }
            return "Error: Invalid operation";
        }

        int lastClose = line.length() - 1;
        for (int i = line.length() - 1; i >= 0; i--) {
            String c = line.charAt(i) + "";
            if (c.equals(")")) {
                lastClose = i;
                continue;
            }
            if (c.equals("(")) {
                String substring = line.substring(i, lastClose + 1);
                String[] argz = substring.replace("(", "").replace(")", "").split(" ");
                Optional<Integer> result = switch (argz[0]) {
                    case "multiply" -> FUNCTIONS.get("multiply").evaluate(argz);
                    case "add" -> FUNCTIONS.get("add").evaluate(argz);
                    case "subtract" -> FUNCTIONS.get("subtract").evaluate(argz);
                    case "exponent" -> FUNCTIONS.get("exponent").evaluate(argz);
                    default -> Optional.empty();
                };
                if (result.isEmpty()) {
                    line = line.replace(substring, "");
                    continue;
                }
                line = line.replace(substring, result.get() + "");
                return processFurthestFunction(line);
            }
        }

        return processFurthestFunction(line);
    }
}
