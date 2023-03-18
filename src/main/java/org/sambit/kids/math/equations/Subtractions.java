package org.sambit.kids.math.equations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subtractions implements EquationsGenerator{
    /**
     * @param numberOfEquations
     * @param ceiling
     * @return
     */
    @Override
    public List<Equation> generateEquations(Integer numberOfEquations, Integer ceiling) {
        Random random = new Random();
        List<Equation> listOfEquations = new ArrayList<Equation>();

        for (var idx = 1; idx <= numberOfEquations; idx++) {
            var onePart = random.nextInt(ceiling) + 1;
            var otherPart = random.nextInt(ceiling) + 1;
            if (onePart > otherPart) {
                listOfEquations.add(
                        new Equation(onePart, otherPart, "-"));
            } else {
                listOfEquations.add(
                        new Equation(otherPart, onePart, "-"));
            }
        }

        return listOfEquations;
    }
}
