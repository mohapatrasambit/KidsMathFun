package org.sambit.kids.math.print.equations;

import org.sambit.kids.math.Equation;
import org.sambit.kids.math.EquationsGenerator;

import java.util.List;

/**
 * Interface to provide print equations.
 */
public interface PrintEquations {
    /**
     * Prints equations as per the implementations
     * @param equations
     */
    void print(List<Equation> equations);
}
