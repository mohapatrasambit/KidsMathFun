package org.sambit.kids.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStandardNumberTable implements EquationsGenerator {
    /**
     * @param numberOfEquations
     * @param ceiling
     * @return
     */
    @Override
    public List<Equation> generateEquations(Integer numberOfEquations, Integer ceiling) {
        Random random = new Random();
        List<Equation> listOfEquations = new ArrayList<>();

        for (var idx = 1; idx <= numberOfEquations; idx++) {
            listOfEquations.add(
                    new Equation(random.nextInt(ceiling) + 1, random.nextInt(10) + 1, "X"));
        }

        return listOfEquations;
    }
}
