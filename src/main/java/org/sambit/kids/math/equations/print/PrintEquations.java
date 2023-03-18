package org.sambit.kids.math.equations.print;

import org.sambit.kids.math.equations.Equation;

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
