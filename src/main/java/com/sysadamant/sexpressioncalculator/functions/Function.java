package com.sysadamant.sexpressioncalculator.functions;

import java.util.Optional;

public interface Function {
    Optional<Integer> evaluate(String[] parts);
}
