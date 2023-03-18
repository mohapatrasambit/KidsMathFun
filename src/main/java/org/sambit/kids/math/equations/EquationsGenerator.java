package org.sambit.kids.math.equations;

import java.util.List;

public interface EquationsGenerator {
    List<Equation> generateEquations(Integer numberOfEquations, Integer ceiling);
}
