package org.sambit.kids.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Additions implements Equations{

    /**
     * @param numberOfEquations
     * @param ceiling
     * @return
     */
    @Override
    public List<String> generateEquations(Integer numberOfEquations, Integer ceiling) {
        Random random = new Random();
        List<String> listOfEquations = new ArrayList<>();

        for (var idx = 1; idx <= numberOfEquations; idx++) {
            if(idx % 3 == 0) {
                listOfEquations.add(String.format("%d + %d = \n", random.nextInt(ceiling) + 1, random.nextInt(ceiling) + 1));
            } else {
                listOfEquations.add(String.format("%d + %d = \t\t\t", random.nextInt(ceiling) + 1, random.nextInt(ceiling) + 1));
            }
        }

        return listOfEquations;
    }
}
