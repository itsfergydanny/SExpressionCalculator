package com.sysadamant.sexpressioncalculator.evaluator;

import com.sysadamant.sexpressioncalculator.functions.AddFunction;
import com.sysadamant.sexpressioncalculator.functions.Function;
import com.sysadamant.sexpressioncalculator.functions.FunctionType;
import com.sysadamant.sexpressioncalculator.functions.MultiplyFunction;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Evaluator {
    private String[] args;

    @Getter
    private final Map<FunctionType, Function> FUNCTIONS = new HashMap<>();

    public Evaluator(String[] args) {
        this.args = args;
        FUNCTIONS.put(FunctionType.ADD, new AddFunction());
        FUNCTIONS.put(FunctionType.MULTIPLY, new MultiplyFunction());
    }

    public String eval() {
        if (args.length < 1) {
            return "Error: No arguments passed. The minimum amount of arguments to pass is 1.";
        }

        String line = args[0];
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
            if (argz.length == 3) {
                if (argz[0].equalsIgnoreCase("multiply")) {
                    Optional<Integer> result = FUNCTIONS.get(FunctionType.MULTIPLY).evaluate(argz);
                    if (result.isPresent()) {
                        return result.get() + "";
                    }
                } else if (argz[0].equalsIgnoreCase("add")) {
                    Optional<Integer> result = FUNCTIONS.get(FunctionType.ADD).evaluate(argz);
                    if (result.isPresent()) {
                        return result.get() + "";
                    }
                }
            }
            return "Error: Invalid operation";
        }

        for (int i = line.length() - 1; i >= 0; i--) {
            String c = line.charAt(i) + "";
            if (c.equals("(")) {
                String substring = line.substring(i);
                String[] argz = substring.replace("(", "").replace(")", "").split(" ");
                if (argz[0].equalsIgnoreCase("multiply")) {
                    Optional<Integer> result = FUNCTIONS.get(FunctionType.MULTIPLY).evaluate(argz);
                    if (result.isEmpty()) {
                        line = line.replace(substring, "");
                        continue;
                    }
                    line = line.replace(substring, result.get() + "");
                } else if (argz[0].equalsIgnoreCase("add")) {
                    Optional<Integer> result = FUNCTIONS.get(FunctionType.ADD).evaluate(argz);
                    if (result.isEmpty()) {
                        line = line.replace(substring, "");
                        continue;
                    }
                    line = line.replace(substring, result.get() + "");
                }
            }
        }

        return processFurthestFunction(line);
    }
}
