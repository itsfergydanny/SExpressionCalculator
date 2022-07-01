package com.sysadamant.sexpressioncalculator.functions;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class ExponentFunction implements Function {
    @Override
    public Optional<Integer> evaluate(String[] parts) {
        if (parts.length != 3) {
            return Optional.empty();
        }

        if (!parts[0].equals("exponent")) {
            return Optional.empty();
        }

        try {
            int firstNumber = Integer.parseInt(parts[1]);
            int secondNumber = Integer.parseInt(parts[2]);

            double result = Math.pow(firstNumber, secondNumber);
            if (result > Integer.MAX_VALUE) {
                return Optional.empty();
            }
            return Optional.of((int) result);
        } catch (NumberFormatException | ArithmeticException ignore) {
            return Optional.empty();
        }
    }
}
