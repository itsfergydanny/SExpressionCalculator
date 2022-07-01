package com.sysadamant.sexpressioncalculator.functions;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class AddFunction implements Function {
    @Override
    public Optional<Integer> evaluate(String[] parts) {
        if (parts.length < 3) {
            return Optional.empty();
        }

        if (!parts[0].equals("add")) {
            return Optional.empty();
        }

        try {
            int sum = Integer.parseInt(parts[1]);
            for (int i = 2; i < parts.length; i++) {
                int num = Integer.parseInt(parts[i]);
                sum = Math.addExact(sum, num);
            }
            return Optional.of(sum);
        } catch (NumberFormatException | ArithmeticException ignore) {
            return Optional.empty();
        }
    }
}
