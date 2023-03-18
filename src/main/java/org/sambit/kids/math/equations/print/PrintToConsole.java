package org.sambit.kids.math.equations.print;

import org.sambit.kids.math.equations.Equation;

import java.util.List;

public class PrintToConsole implements PrintEquations{

    /**
     * Prints equations to console in a 3 column fashion
     *
     * @param equations
     */
    @Override
    public void print(List<Equation> equations) {
        for (var idx = 0; idx < equations.size(); idx++) {
            var equation = equations.get(idx);
            System.out.print(
                    equation.getLeft() + " " +
                    equation.getOperator() + " " +
                    equation.getRight() + " = " +
                    ((idx + 1) % 3 == 0 ? "\n" : "\t\t\t"));
        }

    }
}
