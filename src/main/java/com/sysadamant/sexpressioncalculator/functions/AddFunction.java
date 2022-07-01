package com.sysadamant.sexpressioncalculator.functions;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class AddFunction implements Function {
    @Override
    public Optional<Integer> evaluate(String[] parts) {
        if (parts.length != 3) {
            return Optional.empty();
        }

        if (!parts[0].equals("add")) {
            return Optional.empty();
        }

        try {
            int firstNumber = Integer.parseInt(parts[1]);
            int secondNumber = Integer.parseInt(parts[2]);
            return Optional.of(Math.addExact(firstNumber, secondNumber));
        } catch (NumberFormatException | ArithmeticException ignore) {
            ignore.printStackTrace();
            return Optional.empty();
        }
    }
}
